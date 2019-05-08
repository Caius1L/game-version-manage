package com.handarui.game.dao.mapper;

import com.handarui.game.biz.bean.PermissionBean;
import com.handarui.game.dao.domain.RoleDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDoMapper extends MyMapper<RoleDo> {

    List<RoleDo> getUserRole(long userId);

    List<RoleDo> getRoleList();

    void deleteBatch(@Param("ids") List<Long> ids);

    void deleteRolePermission(@Param("roleId") Long roleId);

    void insertRolePermissionBatch(@Param("roleId") Long roleId, @Param("permissions") List<PermissionBean> permissions);
}