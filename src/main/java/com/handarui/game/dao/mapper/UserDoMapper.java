package com.handarui.game.dao.mapper;

import com.handarui.game.biz.bean.RoleBean;
import com.handarui.game.biz.bean.SysUserBean;
import com.handarui.game.biz.query.SysUserQuery;
import com.handarui.game.dao.domain.UserDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDoMapper extends MyMapper<UserDo> {
    UserDo getByLogin(@Param("login") String login);

    List<SysUserBean> getUserList(@Param("query") SysUserQuery query);

    void deleteBatch(@Param("ids") List<Long> ids);

    void deleteUserRole(@Param("userId") Long userId);

    void insertUserRoleBatch(@Param("userId") Long userId, @Param("roles") List<RoleBean> roles);
}