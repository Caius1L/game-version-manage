package com.handarui.game.controller

import com.handarui.game.biz.bean.RoleBean
import com.handarui.game.biz.service.RoleService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("role")
class RoleController {

    @Autowired
    private lateinit var roleService: RoleService

    @PostMapping("getRoleList")
    fun getRoleList(@RequestBody requestBean: RequestBean<Void>): ResponseBean<List<RoleBean>> {
        val listBean = roleService.getRoleList()
        return ResponseUtil.success(listBean)
    }

    @PostMapping("addRole")
    fun addRole(@RequestBody requestBean: RequestBean<RoleBean>): ResponseBean<Void> {
        roleService.addRole(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("updateRole")
    fun updateRole(@RequestBody requestBean: RequestBean<RoleBean>): ResponseBean<Void> {
        roleService.updateRole(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("getRoleById")
    fun getRoleById(@RequestBody requestBean: RequestBean<Long>): ResponseBean<RoleBean> {
        return ResponseUtil.success(roleService.getRoleById(requestBean.param))
    }

    @PostMapping("deleteBatch")
    fun deleteBatch(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        roleService.deleteBatch(requestBean.param)
        return ResponseUtil.success()
    }

}