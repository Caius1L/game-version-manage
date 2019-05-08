package com.handarui.game.biz.bean

import org.hibernate.validator.constraints.NotEmpty

class SysUserBean {

    var id: Long? = null

    @NotEmpty
    var name: String? = null

    @NotEmpty
    var login: String? = null

    var password: String? = null

    var department: String? = null

    var phone: String? = null

    var email: String? = null

    var roles: List<RoleBean>? = null

}