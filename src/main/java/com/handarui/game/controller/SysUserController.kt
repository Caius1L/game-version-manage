package com.handarui.game.controller

import com.handarui.game.biz.bean.SysUserBean
import com.handarui.game.biz.query.SysUserQuery
import com.handarui.game.biz.service.SysUserService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * 用户管理
 */
@RestController
@RequestMapping(value = ["userManager"])
class SysUserController {

    @Autowired
    private lateinit var sysUserService: SysUserService

    @PostMapping("getUserList")
    fun getUserList(@RequestBody requestBean: RequestBean<PagerQuery<SysUserQuery>>): ResponseBean<ListBean<SysUserBean>> {
        val userList = sysUserService.getUserList(requestBean.param)
        return ResponseUtil.success(userList)
    }

    @PostMapping("addUser")
    fun addUser(@Valid @RequestBody requestBean: RequestBean<SysUserBean>): ResponseBean<Void> {
        sysUserService.addUser(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("updateUser")
    fun updateUser(@Valid @RequestBody requestBean: RequestBean<SysUserBean>): ResponseBean<Void> {
        sysUserService.updateUser(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("getUserById")
    fun getUserById(@RequestBody requestBean: RequestBean<Long>): ResponseBean<SysUserBean> {
        return ResponseUtil.success(sysUserService.getUserById(requestBean.param))
    }

    @PostMapping("deleteBatch")
    fun deleteBatch(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        sysUserService.deleteBatch(requestBean.param)
        return ResponseUtil.success()
    }

}