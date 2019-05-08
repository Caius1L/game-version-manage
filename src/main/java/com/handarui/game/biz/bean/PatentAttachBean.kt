package com.handarui.game.biz.bean

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 15:54
 */

import org.hibernate.validator.constraints.NotEmpty

class PatentAttachBean {

    var id: Long? = null

    /**
     * oss key
     */
    @NotEmpty
    var file: String? = null

    /**
     * 原文件名
     */
    @NotEmpty
    var name: String? = null

    /**
     * 状态附件类型： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    var status:Int?=null
}