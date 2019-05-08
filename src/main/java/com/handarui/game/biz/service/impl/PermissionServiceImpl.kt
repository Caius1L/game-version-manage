package com.handarui.game.biz.service.impl

import com.handarui.game.biz.bean.PermissionBean
import com.handarui.game.biz.service.AuthService
import com.handarui.game.biz.service.PermissionService
import com.handarui.game.dao.domain.PermissionDo
import com.handarui.game.dao.mapper.PermissionDoMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl : PermissionService {

    @Autowired
    private lateinit var permissionDoMapper: PermissionDoMapper

    @Autowired
    private lateinit var authService: AuthService

    override fun getPermissionList(): List<PermissionBean> {
        val permissions = permissionDoMapper.getPermissionList();
        return permissions.map {
            PermissionBean().apply {
                this.id = it.id
                this.name = it.name
                this.url = it.url
            }
        }
    }

    override fun addPermission(permissionBean: PermissionBean) {
        val permissionDo = PermissionDo().apply {
            this.name = permissionBean.name
            this.url = permissionBean.url
        }
        permissionDoMapper.insertSelective(permissionDo)
    }

    override fun getPermissionById(id: Long): PermissionBean {
        val permissionDo = permissionDoMapper.selectByPrimaryKey(id)
        return PermissionBean().apply {
            this.id = permissionDo.id
            this.name = permissionDo.name
            this.url = permissionDo.url
        }
    }

    override fun updatePermission(permissionBean: PermissionBean) {
        val permissionDo = permissionDoMapper.selectByPrimaryKey(permissionBean.id)
        permissionDo.apply {
            this.name = permissionBean.name
            this.url = permissionBean.url
        }
        permissionDoMapper.updateByPrimaryKey(permissionDo)
        authService.clearAuthenticationInfo()
    }

    override fun deleteBatch(ids: List<Long>) {
        permissionDoMapper.deleteBatch(ids)
    }
}