package com.handarui.game.biz.bean


/**
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 16:21
 */

class PatentListBean {
    var id: Long? = null

    /**
     * 专利名称
     */
    var name: String? = null

    /**
     * 编号
     */
    var number: String? = null

    /**
     * 类型： 0 发明、1 实用新型、2 外观
     */
    var type: Int? = null

    /**
     * （导出）类型
     */
    var typeExport: String? = null

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
     * （导出）状态
     */
    var statusExport: String? = null

    /**
     * 发明设计人
     */
    var inventionDesigner: String? = null

    /**
     * 申请人
     */
    var applicant: String? = null

    /**
     * 代理单位
     */
    var agentUnit: String? = null

    /**
     * 提请人
     */
    var drawer: String? = null

    /**
     * 提请部门
     */
    var drawDepartment: String? = null

    /**
     * 本年费缴纳情况 0，未缴  1，已缴
     */
    var currentYearPayment: Boolean? = null

    /**
     * (导出)本年费缴纳情况
     */
    var currentYearPaymentExport: String? = null

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
     * 技术特征
     */
    var technicalFeature: String? = null

    /**
     * 奖金发放
     */
    var bonusPayment: String? = null

    override fun toString(): String {
        return "PatentListBean(id=$id, name=$name, number=$number, type=$type, typeExport=$typeExport, applyNumber=$applyNumber, applyTime=$applyTime, status=$status, statusExport=$statusExport, inventionDesigner=$inventionDesigner, applicant=$applicant, agentUnit=$agentUnit, drawer=$drawer, drawDepartment=$drawDepartment, currentYearPayment=$currentYearPayment, currentYearPaymentExport=$currentYearPaymentExport, currentAnnualFeeYear=$currentAnnualFeeYear, nextYearAnnualFeePaymentTime=$nextYearAnnualFeePaymentTime, fundingReceipt=$fundingReceipt, technicalFeature=$technicalFeature, bonusPayment=$bonusPayment)"
    }

}