package com.handarui.game.biz.bean

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/15 0015 16:18
 */
class CopyrightListBean {
    var id: Long? = null

    /**
     * 著作权名称
     */
    var name: String? = null

    /**
     * 编号
     */
    var number: String? = null

    /**
     * 类型: 0,作品著作权 1,软件著作权
     */
    var type: Int? = null

    /**
     * 类型导出
     */
    var typeExport: String? = null

    /**
     * 申请人
     */
    var applicant: String? = null

    /**
     * 提请人
     */
    var drawer: String? = null

    /**
     * 提请部门
     */
    var drawDepartment: String? = null

    /**
     * 备注
     */
    var note: String? = null

    /**
     * 分类 0 软件、1动画、2小说、3图案、4其他
     */
    var category: Int? = null

    /**
     * 分类导出
     */
    var categoryExport: String? = null

    /**
     * 状态(提请、申请中、已完成、其他，其他可自行填写内容）
     */
    var status: String? = null

    /**
     * 0：未变更 1：变更
     */
    var changed: Boolean? = null
    /**
     * 0：未变更 1：变更
     */
    var changedExport: String? = null

    /**
     * 登记号
     */
    var registerNumber: String? = null

    /**
     * 作品完成时间
     */
    var finishedTime: Long? = null

    /**
     * 登记时间
     */
    var registerTime: Long? = null

    /**
     * 款项及发票
     */
    var paymentInvoice: String? = null

    /**
     * 作者
     */
    var author: String? = null

    override fun toString(): String {
        return "CopyrightListBean(id=$id, name=$name, number=$number, type=$type, typeExport=$typeExport, applicant=$applicant, drawer=$drawer, drawDepartment=$drawDepartment, note=$note, category=$category, categoryExport=$categoryExport, status=$status, changed=$changed, changedExport=$changedExport, registerNumber=$registerNumber, finishedTime=$finishedTime, registerTime=$registerTime, paymentInvoice=$paymentInvoice, author=$author)"
    }

}