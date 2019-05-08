package com.handarui.game.biz.query

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 17:02
 */
class PatentListQuery {

    /**
     * 专利名称
     */
    var name: String? = null

    /**
     * 提请部门
     */
    var drawDepartment: String? = null

    /**
     * 类型： 0 发明、1 实用新型、2 外观
     */
    var type: Int? = null

    /**
     * 申请人
     */
    var applicant: String? = null

    /**
     * 本年费缴纳情况
     */
    var currentYearPayment: Boolean? = null

    override fun toString(): String {
        return "PatentListQuery(categoryName=$name, drawDepartment=$drawDepartment, type=$type, applicant=$applicant, currentYearPayment=$currentYearPayment)"
    }

}