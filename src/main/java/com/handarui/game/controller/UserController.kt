package com.handarui.game.controller

import com.handarui.auth.base.model.ClassicalUserVo
import com.handarui.game.biz.query.UpdatePasswordQuery
import com.handarui.game.biz.service.AuthService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UserController {

    @Autowired
    private lateinit var authService: AuthService

    @RequestMapping("getUserPermission")
    fun getUserPermission(@RequestBody requestBean: RequestBean<Void>, classicalUserVo: ClassicalUserVo?): ResponseBean<List<String>> {
        val permissions = classicalUserVo?.permissions?.map {
            it.name
        }
        return ResponseUtil.success(permissions)
    }

    @RequestMapping("getUserInfo")
    fun getUserInfo(@RequestBody requestBean: RequestBean<Void>,classicalUserVo: ClassicalUserVo?): ResponseBean<ClassicalUserVo>{
        return ResponseUtil.success(classicalUserVo)
    }

    @PostMapping("updatePassword")
    fun updatePassword(@Valid @RequestBody requestBean: RequestBean<UpdatePasswordQuery>):ResponseBean<Void>{
        authService.updatePassword(requestBean.param)
        return ResponseUtil.success()
    }

}