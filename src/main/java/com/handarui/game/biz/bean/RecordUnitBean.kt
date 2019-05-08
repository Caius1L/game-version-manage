package com.handarui.game.biz.bean

/**
 * 游戏备案单位
 */
class RecordUnitBean {

    var id: Long? = null

    var unit: String? = null

    var status: Int? = null//备案状态0:提交1:受理2:完成

    var selfReport: MutableList<AttachBean>? = null

    var userProtocol: MutableList<AttachBean>? = null

    var authorization: MutableList<AttachBean>? = null

}