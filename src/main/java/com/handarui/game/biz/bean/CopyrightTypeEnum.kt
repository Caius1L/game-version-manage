package com.handarui.game.biz.bean

enum class CopyrightTypeEnum(val code: Int, val type: String) {

    WORKS(0, "作品著作权"),
    SOFTWARE(1, "软件著作权"),
    DEFAULT(99, "默认");

    companion object {
        fun getByTypeName(type: String): CopyrightTypeEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.type == type
            }
            return match ?: DEFAULT
        }

        fun getByTypeCode(code: Int): CopyrightTypeEnum{
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }

}