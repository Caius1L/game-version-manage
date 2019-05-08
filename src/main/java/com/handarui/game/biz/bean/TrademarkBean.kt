package com.handarui.game.biz.bean


/**
 * @Author: xiebaobiao
 * @Date: 2019/2/18 0018 14:02
 */
class TrademarkBean {
    var id: Long? = null

    /**
     * 商标名称
     */
    var name: String? = null

    /**
     * 类别 ：1-45
     */
    var category: Int? = null

    /**
     * 0文字1 图案 2复合
     */
    var type: Int? = null

    /**
     * 受理/注册号
     */
    var acceptRegistrationNumber: String? = null

    /**
     * 申请日
     */
    var applyTime: Long? = null

    /**
     * 状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     */
    var status: Int? = null

    /**
     * 注册日
     */
    var registrationTime: Long? = null

    /**
     * 有效期
     */
    var validityPeriod: Long? = null

    /**
     * 申请人
     */
    var applicant: String? = null

    /**
     * 商品/服务列表
     */
    var productServiceList: String? = null

    /**
     * 提请部门
     */
    var drawDepartment: String? = null

    var agency: String? = null

    /**
     * 到期提醒（时间）
     */
    var expirationReminderTime: Long? = null

    /**
     * 资助领取情况
     */
    var fundingReceipt: String? = null

    /**
     * 备注
     */
    var note: String? = null

    /**
     * 图样附件
     */
    var drawingMaterials: MutableList<TrademarkAttachBean>? = mutableListOf()

    /**
     * 受理号/注册号附件
     */
    var acceptRegistrationNumberMaterial: TrademarkAttachBean? = null

    /**
     * 状态
     */
    var statusMaterials: MutableList<TrademarkAttachBean>? = mutableListOf()

    override fun toString(): String {
        return "TrademarkBean(id=$id, categoryName=$name, category=$category, type=$type, acceptRegistrationNumber=$acceptRegistrationNumber, applyTime=$applyTime, status=$status, registrationTime=$registrationTime, validityPeriod=$validityPeriod, applicant=$applicant, productServiceList=$productServiceList, drawDepartment=$drawDepartment, agency=$agency, expirationReminderTime=$expirationReminderTime, fundingReceipt=$fundingReceipt, note=$note, drawingMaterials=$drawingMaterials, acceptRegistrationNumberMaterial=$acceptRegistrationNumberMaterial, statusMaterials=$statusMaterials)"
    }

}