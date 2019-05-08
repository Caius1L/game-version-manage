package com.handarui.game.biz.query

import com.handarui.game.biz.bean.PatentAttachBean
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 11:44
 */
class ModifyProductPatentQuery {
    @NotNull
    var id: Long? = null

    /**
     * 专利名称
     */
    @NotBlank
    var name: String? = null

    /**
     * 编号
     */
    @NotBlank
    var number: String? = null

    /**
     * 类型： 0 发明、1 实用新型、2 外观
     */
    @NotNull
    var type: Int? = null

    /**
     * 申请号
     */
    var applyNumber: String? = null

    /**
     * 申请日
     */
    var applyTime: Long? = null

    /**
     * 状态： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    var status: Int? = null

    /**
     * 发明设计人
     */
    var inventionDesigner: String? = null

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
    @NotBlank
    var drawDepartment: String? = null

    /**
     * 本年费缴纳情况 0，未缴  1，已缴
     */
    var currentYearPayment: Boolean? = null

    /**
     * 当前年费年度
     */
    var currentAnnualFeeYear: String? = null

    /**
     * 下一年度年费缴纳时间
     */
    var nextYearAnnualFeePaymentTime: Long? = null

    /**
     * 资助领取情况
     */
    var fundingReceipt: String? = null

    /**
     * 备注
     */
    var note: String? = null

    /**
     * 技术特征
     */
    @NotBlank
    var technicalFeature: String? = null

    /**
     * 公告日
     */
    var announcementDay: Long? = null

    /**
     * 公告号
     */
    var announcementNumber: String? = null

    /**
     * 奖金发放
     */
    var bonusPayment: String? = null


    /**
     * 状态附件
     */
    var statusMaterials: MutableList<PatentAttachBean>? = mutableListOf()

    /**
     * 本年费缴纳情况附件
     */
    var currentYearPaymentMaterials: MutableList<PatentAttachBean>? = mutableListOf()

    /**
     * 备注附件
     */
    var noteMaterials: MutableList<PatentAttachBean>? = mutableListOf()

    override fun toString(): String {
        return "ModifyProductPatentQuery(id=$id, categoryName=$name, number=$number, type=$type, applyNumber=$applyNumber, applyTime=$applyTime, status=$status, inventionDesigner=$inventionDesigner, applicant=$applicant, drawer=$drawer, drawDepartment=$drawDepartment, currentYearPayment=$currentYearPayment, currentAnnualFeeYear=$currentAnnualFeeYear, nextYearAnnualFeePaymentTime=$nextYearAnnualFeePaymentTime, fundingReceipt=$fundingReceipt, note=$note, technicalFeature=$technicalFeature, announcementDay=$announcementDay, announcementNumber=$announcementNumber, bonusPayment=$bonusPayment, statusMaterials=$statusMaterials, currentYearPaymentMaterials=$currentYearPaymentMaterials, noteMaterials=$noteMaterials)"
    }

}