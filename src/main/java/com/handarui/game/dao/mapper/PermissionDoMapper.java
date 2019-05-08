package com.handarui.game.dao.mapper;

import com.handarui.game.dao.domain.PermissionDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDoMapper extends MyMapper<PermissionDo> {

    List<PermissionDo> getRolePermission(@Param("roleIds") List<Long> roleIds);

    List<PermissionDo> getPermissionList();

    void deleteBatch(@Param("ids") List<Long> ids);

    List<PermissionDo> getPermissionByRoleId(@Param("roleId") long roleId);
}