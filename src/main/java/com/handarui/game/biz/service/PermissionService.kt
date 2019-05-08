package com.handarui.game.biz.service

import com.handarui.game.biz.bean.PermissionBean

interface PermissionService {

    fun getPermissionList(): List<PermissionBean>

    fun addPermission(permissionBean: PermissionBean)

    fun getPermissionById(id: Long): PermissionBean

    fun updatePermission(permissionBean: PermissionBean)

    fun deleteBatch(ids: List<Long>)
}