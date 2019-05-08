package com.handarui.game.biz.service

import com.handarui.game.biz.bean.OSSConfigBean
import com.handarui.game.biz.bean.STSTokenBean

interface STSService {

    /**
     * 获取OSS配置
     */
    fun getOSSConfig(): OSSConfigBean

    /**
     * 获取STS临时权限(只读)
     */
    fun getSTSReadAccessKey(): STSTokenBean

    /**
     * 获取STS临时权限（写）
     */
    fun getSTSWriteAccessKey(): STSTokenBean

}