package com.handarui.game.biz.service

import com.handarui.game.biz.bean.RoleBean
import com.zhexinit.ov.common.query.ListBean

interface RoleService {

    fun getRoleList(): List<RoleBean>

    fun addRole(roleBean: RoleBean)

    fun getRoleById(id: Long): RoleBean

    fun updateRole(roleBean: RoleBean)

    fun deleteBatch(ids: List<Long>)

}