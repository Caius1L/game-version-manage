package com.handarui.game.biz.bean

enum class CopyrightCategoryEnum(val code: Int, val categoryName: String) {

    //0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
    SOFTWARE(0, "软件"),
    ANIMATION(1, "动画"),
    NOVEL(2, "小说"),
    PATTERN(3, "图案"),
    OTHER(4, "其他"),
    DEFAULT(99, "默认");

    companion object {
        fun getCategoryByName(name: String): CopyrightCategoryEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.categoryName == name
            }
            return match ?: DEFAULT
        }

        fun getCategoryByCode(code: Int): CopyrightCategoryEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }
}