package com.handarui.game.biz.bean

enum class ProcessEnum(val code: Int, val process: String) {

    APPLY(0, "软著申请"),
    FINISH(1, "软著完成"),
    APPROVAL(2, "合同批复"),
    AUDITING(3, "内部审包"),
    SUBMIT(4, "提交省局"),
    ASK_FOR_FILE(5, "请示文件"),
    ACCEPT(6, "总局受理"),
    REPLY(7, "答复意见"),
    WRITING(8, "出文"),
    RETURN(9, "退回"),
    DEFAULT(99, "默认");

    companion object {
        fun getByProcessName(process: String): ProcessEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.process == process
            }
            return match ?: DEFAULT
        }

        fun getByProcessCode(code: Int): ProcessEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }
}