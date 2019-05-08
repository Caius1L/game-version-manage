package com.handarui.game.biz.bean

class GameBean : BaseGameBean() {

    /**
     * 出版单位
     */
    var publishUnit: String? = null

    /**
     * 存档编号
     */
    var archiveNumber: String? = null

    /**
     * 提请人
     */
    var submitUserName: String? = null

    /**
     * 提请人邮箱
     */
    var submitUserEmail: String? = null

    /**
     * 提请人手机号
     */
    var submitUserPhone: String? = null

    /**
     * 出版费
     */
    var publishFee: String? = null

    /**
     * 合同
     */
    var contract: MutableList<AttachBean>? = null

    /**
     * 批文时间
     */
    var approvalTime: Long? = null

    /**
     * 受理时间
     */
    var acceptTime: Long? = null

    /**
     * 发票
     */
    var invoice: MutableList<AttachBean>? = null


    /**
     * 备注
     */
    var note: String? = null

    /**
     * 备案单位
     */
    var recordUnit: List<RecordUnitBean>? = null


    /**
     * 版号材料
     */
    var versionMaterial: List<VersionMaterialBean>? = null

    /**
     * 备注附件
     */
    var noteAttach: MutableList<AttachBean>? = null

    /**
     * 游戏包
     */
    var gamePackage: MutableList<AttachBean>? = null

}