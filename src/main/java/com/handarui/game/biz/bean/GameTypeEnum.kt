package com.handarui.game.biz.bean

enum class GameTypeEnum(val code: Int, val type: String) {

    IMPORT(0, "进口游戏"),
    INSIDE(1, "内部游戏"),
    OUTSIDE(2, "外部游戏"),
    DEFAULT(99, "默认");

    companion object {
        fun getByTypeName(type: String): GameTypeEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.type == type
            }
            return match ?: DEFAULT
        }

        fun getByTypeCode(code: Int): GameTypeEnum{
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }

}