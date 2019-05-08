package com.handarui.game.biz.config

import com.baidu.disconf.client.common.annotations.DisconfFile
import com.baidu.disconf.client.common.annotations.DisconfFileItem
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope("singleton")
@DisconfFile(filename = "oss.properties")
class OSSConfig {

    @get:DisconfFileItem(name = "OSS.AccessKeyID")
    var accessKeyId: String? = null

    @get:DisconfFileItem(name = "OSS.AccessKeySecret")
    var accessKeySecret: String? = null

    @get:DisconfFileItem(name = "OSS.Bucket")
    var bucket: String? = null

    @get:DisconfFileItem(name = "OSS.Endpoint")
    var endpoint: String? = null

    @get:DisconfFileItem(name = "OSS.Resource")
    var resource: String? = null

    @get:DisconfFileItem(name = "OSS.RoleArn")
    var roleArn: String? = null

    @get:DisconfFileItem(name = "OSS.SessionName")
    var sessionName: String? = null

    @get:DisconfFileItem(name = "OSS.ReadTokenExpireTime")
    var readTokenExpireTime: Long? = null

    @get:DisconfFileItem(name = "OSS.WriteTokenExpireTime")
    var writeTokenExpireTime: Long? = null

}
