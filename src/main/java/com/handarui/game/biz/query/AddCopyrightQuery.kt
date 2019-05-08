package com.handarui.game.biz.query

import com.handarui.game.biz.bean.AttachBean
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 著作权
 */
class AddCopyrightQuery {

    /**
     * 著作权名称
     */
    @NotBlank
    var name: String? = null

    /**
     * 编号
     */
    @NotBlank
    var number: String? = null

    /**
     * 类型: 0,作品著作权 1,软件著作权
     */
    @NotNull
    var type: Int? = null

    /**
     * 申请人
     */
    @NotBlank
    var applicant: String? = null

    /**
     * 提请人
     */
    @NotBlank
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
    @NotNull
    var category: Int? = null

    /**
     * 状态(提请、申请中、已完成、其他，其他可自行填写内容）
     */
    @NotNull
    var status: String? = null

    /**
     * 可选 1“是” 0 “否”
     */
    @NotNull
    var changed: Int? = null

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

    /**
     * 申请材料
     */
    var petitionMaterials: MutableList<AttachBean>? = mutableListOf()

    /**
     * 登记号材料
     */
    var registerNumberMaterials: MutableList<AttachBean>? = mutableListOf()

    /**
     * 款项及发票材料
     */
    var paymentInvoiceMaterial: AttachBean? = null

    override fun toString(): String {
        return "AddCopyrightQuery(name=$name, number=$number, type=$type, applicant=$applicant, drawer=$drawer, drawDepartment=$drawDepartment, note=$note, category=$category, status=$status, changed=$changed, registerNumber=$registerNumber, finishedTime=$finishedTime, registerTime=$registerTime, paymentInvoice=$paymentInvoice, author=$author, petitionMaterials=$petitionMaterials, registerNumberMaterials=$registerNumberMaterials, paymentInvoiceMaterial=$paymentInvoiceMaterial)"
    }

}