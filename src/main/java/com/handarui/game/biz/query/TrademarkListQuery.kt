package com.handarui.game.biz.query

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/18 0018 14:21
 */
class TrademarkListQuery {

    /**
     * 提请部门
     */
    var drawDepartment: String? = null

    /**
     * 商标名称
     */
    var name: String? = null

    /**
     * 类别:1-45
     */
    var category: Int? = null

    /**
     * 申请人
     */
    var applicant: String? = null

    /**
     * 状态
     */
    var status: Int? = null

    override fun toString(): String {
        return "TrademarkListQuery(drawDepartment=$drawDepartment, categoryName=$name, category=$category, applicant=$applicant, status=$status)"
    }

}