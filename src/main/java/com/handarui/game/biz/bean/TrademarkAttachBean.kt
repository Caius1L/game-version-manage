package com.handarui.game.biz.bean

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 15:54
 */

import org.hibernate.validator.constraints.NotEmpty

class TrademarkAttachBean {

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
     * 状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     */
    var status:Int?=null

    override fun toString(): String {
        return "TrademarkAttachBean(id=$id, file=$file, categoryName=$name, status=$status)"
    }

}