package com.handarui.game.biz.query

import javax.validation.constraints.NotNull

class UpdatePasswordQuery {

    @NotNull
    var id: Long? = null

    @NotNull
    var oldPassword: String? = null

    @NotNull
    var newPassword: String? = null

}