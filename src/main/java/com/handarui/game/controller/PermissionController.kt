package com.handarui.game.controller

import com.handarui.game.biz.bean.PermissionBean
import com.handarui.game.biz.service.PermissionService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("permission")
class PermissionController {

    @Autowired
    private lateinit var permissionService: PermissionService

    @PostMapping("getPermissionList")
    fun getPermissionList():ResponseBean<List<PermissionBean>>{
        return ResponseUtil.success(permissionService.getPermissionList())
    }

    @PostMapping("addPermission")
    fun addPermission(@RequestBody requestBean: RequestBean<PermissionBean>): ResponseBean<Void> {
        permissionService.addPermission(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("updatePermission")
    fun updatePermission(@RequestBody requestBean: RequestBean<PermissionBean>): ResponseBean<Void> {
        permissionService.updatePermission(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("getPermissionById")
    fun getPermissionById(@RequestBody requestBean: RequestBean<Long>): ResponseBean<PermissionBean> {
        return ResponseUtil.success(permissionService.getPermissionById(requestBean.param))
    }

    @PostMapping("deleteBatch")
    fun deleteBatch(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        permissionService.deleteBatch(requestBean.param)
        return ResponseUtil.success()
    }
}