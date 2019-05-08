package com.handarui.game.biz.service.impl

import com.handarui.auth.base.model.BasicPermissionVo
import com.handarui.auth.base.model.BasicRoleVo
import com.handarui.auth.base.model.ClassicalUserVo
import com.handarui.auth.model.UserContext
import com.handarui.auth.service.ClassicalAccountUserService
import com.handarui.game.dao.mapper.PermissionDoMapper
import com.handarui.game.dao.mapper.RoleDoMapper
import com.handarui.game.dao.mapper.UserDoMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountUserService : ClassicalAccountUserService {

    @Autowired
    private lateinit var userDoMapper: UserDoMapper

    @Autowired
    private lateinit var roleDoMapper: RoleDoMapper

    @Autowired
    private lateinit var permissionDoMapper: PermissionDoMapper

    override fun getRoles(userVo: ClassicalUserVo): List<BasicRoleVo> {
        val userRoleDos = roleDoMapper.getUserRole(userVo.id)
        return userRoleDos.map {
            BasicRoleVo().apply {
                this.id = it.id
                this.name = it.name
            }
        }
    }

    override fun getPermissions(roles: List<BasicRoleVo>): List<BasicPermissionVo> {
        val roleIds = roles.map {
            it.id
        }
        val permissionDos = permissionDoMapper.getRolePermission(roleIds)
        return permissionDos.map {
            BasicPermissionVo().apply {
                this.id = it.id
                this.name = it.url
            }
        }
    }

    override fun getUserDetails(login: String): UserContext<ClassicalUserVo>? {
        val userDo = userDoMapper.getByLogin(login) ?: return null
        val userVo = ClassicalUserVo().apply {
            this.id = userDo.id
            this.username = userDo.login
            this.isAccountNonLocked = true
            this.password = userDo.password
        }
        val roles = getRoles(userVo)
        val permissions = getPermissions(roles)
        userVo.roles = roles
        userVo.permissions = permissions
        return UserContext(userVo)
    }
}