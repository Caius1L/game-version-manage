package com.handarui.game.biz.bean

/**
 * 0 发明、1 实用新型、2 外观
 */
enum class PatentTypeEnum(val code: Int, val type: String) {

    INVENT(0, "发明"),
    UTILITY_MODEL(1, "实用新型"),
    APPEARANCE(2, "外观"),
    DEFAULT(99, "默认");

    companion object {
        fun getByTypeName(type: String): PatentTypeEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.type == type
            }
            return match ?: DEFAULT
        }

        fun getByTypeCode(code: Int): PatentTypeEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }

}