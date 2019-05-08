package com.handarui.game.biz.bean

/**
 * Created by ThinkPad on 2016/9/14.
 */

import java.io.Serializable

/**
 * STSToken
 *
 * @author ChenY
 * @create 2016-09-14 17:35
 */
class STSTokenBean : Serializable {

    /**
     * 获取STS的结果
     */
    var status: String? = null

    /**
     * AccessKeyId
     */
    var accessKeyId: String? = null

    /**
     * AccessKeySecret
     */
    var accessKeySecret: String? = null

    /**
     * 临时授权token
     */
    var securityToken: String? = null

    /**
     * token创建时间
     */
    var expiration: String? = null

    /**
     * 文件名
     */
    var objectName: String? = null
}
