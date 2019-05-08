package com.handarui.game.biz.service.impl

import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.http.MethodType
import com.aliyuncs.http.ProtocolType
import com.aliyuncs.profile.DefaultProfile
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse
import com.handarui.game.biz.bean.OSSConfigBean
import com.handarui.game.biz.bean.STSTokenBean
import com.handarui.game.biz.config.OSSConfig
import com.handarui.game.biz.service.STSService
import com.zhexinit.ov.common.util.Constant
import com.zhexinit.ov.common.util.OSSRegionUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.regex.Pattern


@Service
class STSServiceImpl : STSService {

    companion object {
        private const val STS_API_VERSION = "2015-04-01"
        private val logger = LoggerFactory.getLogger(STSServiceImpl::class.java)
    }

    @Autowired
    private lateinit var ossConfig: OSSConfig

    override fun getOSSConfig(): OSSConfigBean {
        return OSSConfigBean().apply {
            endpoint = ossConfig.endpoint
            bucket = ossConfig.bucket
        }
    }

    override fun getSTSReadAccessKey(): STSTokenBean {
        DefaultProfile.addEndpoint("ap-southeast-1", "ap-southeast-1", "Sts", "sts.aliyuncs.com")
        // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
        val region = OSSRegionUtil.getRegion(ossConfig.endpoint)
        val profile = DefaultProfile.getProfile(region, ossConfig.accessKeyId, ossConfig.accessKeySecret)
        val client = DefaultAcsClient(profile)
        val policy = getPolicy(Constant.OSS_GET_ALL, ossConfig.resource + "*")
        val request = createAssumeRequest(policy, ossConfig.readTokenExpireTime)
        // 发起请求，并得到response
        val response = client.getAcsResponse(request)
        return createToken(response, null)
    }

    override fun getSTSWriteAccessKey(): STSTokenBean {
        DefaultProfile.addEndpoint("ap-southeast-1", "ap-southeast-1", "Sts", "sts.aliyuncs.com")
        // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
        val region = OSSRegionUtil.getRegion(ossConfig.endpoint);
        val profile = DefaultProfile.getProfile(region, ossConfig.accessKeyId, ossConfig.accessKeySecret)
        val client = DefaultAcsClient(profile)
        // 用户只能写入到特定的路径
        val objectName = getUserWritableObjectName(ossConfig.resource!!)
        val policy = getPolicy(Constant.OSS_PUT_OBJECT, ossConfig.resource!! + "*")
        val request = createAssumeRequest(policy, ossConfig.writeTokenExpireTime)
        // 发起请求，并得到response
        val response = client.getAcsResponse(request)
        return createToken(response, objectName)
    }

    /**
     * 获取授权规则
     */
    fun getPolicy(action: String, resource: String): String {
        var policy = " {\"Version\": \"1\",\"Statement\": [{\"Effect\": \"Allow\","
        policy = "$policy\"Action\": [\"$action"
        policy = "$policy\"],\"Resource\": [\"$resource"
        policy = "$policy\"],\"Condition\": {}}]} "
        logger.info("创建OSS访问权限: {}", policy)
        return policy
    }

    /**
     * 根据资源路径获取用户可访问的文件路径
     */
    fun getUserWritableObjectName(resource: String): String {
        val pattern = "^acs:oss:\\*:\\*:[a-zA-Z0-1\\-]+/(.+)$"
        val r = Pattern.compile(pattern)
        val m = r.matcher(resource)
        if (m.find()) {
            return m.group(1);
        } else {
            throw IllegalStateException("Resource format error")
        }
    }

    /**
     * 创建一个 AssumeRoleRequest 并设置请求参数
     */
    fun createAssumeRequest(policy: String, durationSeconds: Long?): AssumeRoleRequest {
        val request = AssumeRoleRequest()
        request.version = STS_API_VERSION
        request.method = MethodType.POST
        request.protocol = ProtocolType.HTTPS
        request.roleArn = ossConfig.roleArn
        request.roleSessionName = ossConfig.sessionName
        request.policy = policy
        request.durationSeconds = durationSeconds
        return request
    }

    /**
     * 创建StsTokenBean
     */
    fun createToken(response: AssumeRoleResponse, objectName: String?): STSTokenBean {
        return STSTokenBean().apply {
            status = "200"
            accessKeyId = response.credentials.accessKeyId
            accessKeySecret = response.credentials.accessKeySecret
            securityToken = response.credentials.securityToken
            expiration = response.credentials.expiration
            this.objectName = objectName
        }
    }
}