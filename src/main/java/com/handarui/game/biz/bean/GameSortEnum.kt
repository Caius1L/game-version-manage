package com.handarui.game.biz.bean

enum class GameSortEnum(val code: Int, val sort: String) {

    //0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
    REMOVE(0, "消除类"),
    RUN(1, "跑酷类"),
    FLIGHT(2, "飞行类"),
    CHESS(3, "棋牌类"),
    RIDDLE(4, "解谜类"),
    SPORT(5, "体育类"),
    MUSIC_DANCE(6, "音乐舞蹈类"),
    FISH(7, "捕鱼类"),
    PAINT(8, "涂色类"),
    EJECTION(9, "弹射类"),
    PLACE(10, "放置类"),
    DIG(11, "挖掘类"),
    SIMPLE_BUILD(12, "简单搭建类"),
    NOT_SIMPLE_GAME(13, "非简易游戏"),
    DEFAULT(99, "默认");

    companion object {
        fun getSortByName(name: String): GameSortEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.sort == name
            }
            return match ?: DEFAULT
        }

        fun getSortByCode(code: Int): GameSortEnum {
            val enums = values()
            val match = enums.firstOrNull {
                it.code == code
            }
            return match ?: DEFAULT
        }
    }
}