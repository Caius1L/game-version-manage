package com.handarui.game.biz.service.impl

import com.handarui.game.biz.bean.PermissionBean
import com.handarui.game.biz.bean.RoleBean
import com.handarui.game.biz.service.AuthService
import com.handarui.game.biz.service.RoleService
import com.handarui.game.dao.domain.RoleDo
import com.handarui.game.dao.mapper.PermissionDoMapper
import com.handarui.game.dao.mapper.RoleDoMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleServiceImpl: RoleService {

    @Autowired
    private lateinit var roleDoMapper: RoleDoMapper

    @Autowired
    private lateinit var permissionDoMapper: PermissionDoMapper

    @Autowired
    private lateinit var authService: AuthService

    override fun getRoleList(): List<RoleBean> {
        val roles = roleDoMapper.getRoleList()
        return roles.map {
            RoleBean().apply {
                this.id = it.id
                this.name = it.name
            }
        }
    }

    @Transactional
    override fun addRole(roleBean: RoleBean) {
        val roleDo = RoleDo().apply {
            this.name = roleBean.name
        }
        roleDoMapper.insertSelective(roleDo)
        if (roleBean.permissions!=null && roleBean.permissions!!.isNotEmpty()){
            roleDoMapper.insertRolePermissionBatch(roleDo.id,roleBean.permissions)
        }
    }

    override fun getRoleById(id: Long): RoleBean {
        val roleDo = roleDoMapper.selectByPrimaryKey(id)
        val permissions = permissionDoMapper.getPermissionByRoleId(id)
        return RoleBean().apply {
            this.id = roleDo.id
            this.name = roleDo.name
            this.permissions = permissions.map {
                PermissionBean().apply {
                    this.id = it.id
                    this.name = it.name
                    this.url = it.url
                }
            }
        }
    }

    @Transactional
    override fun updateRole(roleBean: RoleBean) {
        val roleDo = RoleDo().apply {
            this.id = roleBean.id
            this.name = roleBean.name
        }
        roleDoMapper.updateByPrimaryKeySelective(roleDo)
        roleDoMapper.deleteRolePermission(roleBean.id)
        if (roleBean.permissions!=null && roleBean.permissions!!.isNotEmpty()){
            roleDoMapper.insertRolePermissionBatch(roleBean.id,roleBean.permissions)
        }
        authService.clearAuthenticationInfo()
    }

    override fun deleteBatch(ids: List<Long>) {
        roleDoMapper.deleteBatch(ids)
    }
}