package com.handarui.game.biz.query

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/15 0015 15:51
 */
class CopyrightListQuery {

    /**
     * 提请部门
     */
    var drawDepartment: String? = null

    /**
     * 名称
     */
    var name: String? = null

    /**
     * 申请人
     */
    var applicant: String? = null

    /**
     * 分类: 0 软件、1动画、2小说、3图案、4其他
     */
    var category: Int? = null

    /**
     * 类型: 0,作品著作权 1,软件著作权
     */
    var type: Int? = null

    /**
     * 获取状态(提请、申请中、已完成、其他，其他可自行填写内容）
     */
    var status: String? = null

    override fun toString(): String {
        return "CopyrightListQuery(drawDepartment=$drawDepartment, name=$name, applicant=$applicant, category=$category, type=$type, status=$status)"
    }

}