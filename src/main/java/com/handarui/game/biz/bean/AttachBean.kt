package com.handarui.game.biz.bean

import org.hibernate.validator.constraints.NotEmpty

class AttachBean {

    var id: Long? = null

    /**
     * oss key
     */
    @NotEmpty
    var file: String? = null

    /**
     * 原文件名
     */
    @NotEmpty
    var name: String? = null
}