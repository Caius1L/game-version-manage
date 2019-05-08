package com.handarui.game.biz.service

import com.handarui.game.biz.bean.SysUserBean
import com.handarui.game.biz.query.SysUserQuery
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery

interface SysUserService {

    fun getUserList(query: PagerQuery<SysUserQuery>): ListBean<SysUserBean>

    fun addUser(sysUserBean: SysUserBean)

    fun updateUser(sysUserBean: SysUserBean)

    fun getUserById(id: Long):SysUserBean

    fun deleteBatch(ids: List<Long>)

}