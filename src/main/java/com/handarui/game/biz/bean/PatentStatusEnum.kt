package com.handarui.game.biz.bean

/**
 * 状态：
 * 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、
 * 6 驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
 */
enum class PatentStatusEnum(val code: Int, val type: String) {

    WRITING_BOOK(0, "撰写交底书"),
    SUBMIT_APPLY(1, "提交申请"),
    ACCEPT(2, "受理"),
    ACTUAL_REVIEW(3 ,"实审"),
    REGISTRATION(4 ,"办登"),
    AUTHORIZATION(5 ,"授权"),
    REJECT(6 ,"驳回"),
    REHEAR(7 ,"复审"),
    OBJECTION(8 ,"异议"),
    INVALID(9 ,"无效"),
    PRELIMINARY_REVIEW(10 ,"初步审查"),
    AUTHORIZATION_NOTICE(11 ,"授权公告"),
    ADMINISTRATIVE_RECONSIDERATION(12 ,"行政复议"),
    DEFAULT(99, "默认");

    companion object {
        fun getByStatusName(status: String): PatentStatusEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.type == status
            }
            return match ?: DEFAULT
        }

        fun getByStatusCode(code: Int): PatentStatusEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }

}