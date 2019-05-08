package com.handarui.game.biz.service.impl

import com.github.pagehelper.PageHelper
import com.handarui.auth.service.PasswordEncodeService
import com.handarui.game.biz.bean.RoleBean
import com.handarui.game.biz.bean.SysUserBean
import com.handarui.game.biz.query.SysUserQuery
import com.handarui.game.biz.service.AuthService
import com.handarui.game.biz.service.SysUserService
import com.handarui.game.dao.domain.UserDo
import com.handarui.game.dao.mapper.RoleDoMapper
import com.handarui.game.dao.mapper.UserDoMapper
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SysUserServiceImpl : SysUserService {

    @Autowired
    private lateinit var userDoMapper: UserDoMapper

    @Autowired
    private lateinit var roleDoMapper: RoleDoMapper

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var passwordEncodeService: PasswordEncodeService

    override fun getUserList(query: PagerQuery<SysUserQuery>): ListBean<SysUserBean> {
        val page = PageHelper.startPage<SysUserBean>(query.current, query.offset)
        val list = userDoMapper.getUserList(query.data)
        return ListBean(page.total, list)
    }

    @Transactional
    override fun addUser(sysUserBean: SysUserBean) {
        val userDo = UserDo().apply {
            this.login = sysUserBean.login
            this.name = sysUserBean.name
            this.password = passwordEncodeService.encode(sysUserBean.password)
            this.department = sysUserBean.department
            this.phone = sysUserBean.phone
            this.email = sysUserBean.email
        }
        userDoMapper.insertSelective(userDo)
        if (sysUserBean.roles != null && sysUserBean.roles!!.isNotEmpty()) {
            userDoMapper.insertUserRoleBatch(userDo.id, sysUserBean.roles)
        }
    }

    @Transactional
    override fun updateUser(sysUserBean: SysUserBean) {
        val userDo = UserDo().apply {
            this.id = sysUserBean.id
            this.login = sysUserBean.login
            this.name = sysUserBean.name
            this.department = sysUserBean.department
            this.phone = sysUserBean.phone
            this.email = sysUserBean.email
        }
        userDoMapper.updateByPrimaryKeySelective(userDo)
        userDoMapper.deleteUserRole(sysUserBean.id)
        if (sysUserBean.roles != null && sysUserBean.roles!!.isNotEmpty()) {
            userDoMapper.insertUserRoleBatch(sysUserBean.id, sysUserBean.roles)
        }
        authService.clearAuthenticationInfo()
    }

    override fun getUserById(id: Long): SysUserBean {
        val userDo = userDoMapper.selectByPrimaryKey(id)
        val roles = roleDoMapper.getUserRole(id)
        return SysUserBean().apply {
            this.id = userDo.id
            this.login = userDo.login
            this.name = userDo.name
            this.department = userDo.department
            this.phone = userDo.phone
            this.email = userDo.email
            this.roles = roles.map {
                RoleBean().apply {
                    this.id = it.id
                    this.name = it.name
                }
            }
        }
    }

    override fun deleteBatch(ids: List<Long>) {
        userDoMapper.deleteBatch(ids)
    }
}