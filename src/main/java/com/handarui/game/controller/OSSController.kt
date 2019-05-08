package com.handarui.game.controller

import com.handarui.auth.model.AppUserVo
import com.handarui.game.biz.bean.OSSConfigBean
import com.handarui.game.biz.bean.STSTokenBean
import com.handarui.game.biz.service.STSService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sts")
class OSSController {

    @Autowired
    private lateinit var stsService: STSService

    /**
     * 获取OSS配置
     */
    @RequestMapping("getOssConfig", method = arrayOf(RequestMethod.POST))
    fun getOSSConfig(@RequestBody query: RequestBean<Void>): ResponseBean<OSSConfigBean> {
        return ResponseUtil.success(stsService.getOSSConfig())
    }

    /**
     * 获取STS临时权限(只读)
     */
    @RequestMapping("getSTSReadAccessKey", method = arrayOf(RequestMethod.POST))
    fun getSTSReadAccessKey(@RequestBody query: RequestBean<Void>): ResponseBean<STSTokenBean> {
        return ResponseUtil.success(stsService.getSTSReadAccessKey())
    }

    /**
     * 获取STS临时权限（只写）
     */
    @RequestMapping("getSTSWriteAccessKey", method = arrayOf(RequestMethod.POST))
    fun getSTSWriteAccessKey(@RequestBody query: RequestBean<Void>): ResponseBean<STSTokenBean> {
        return ResponseUtil.success(stsService.getSTSWriteAccessKey())
    }
}