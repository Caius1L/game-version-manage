package com.handarui.game.biz.service.impl

import com.handarui.auth.service.PasswordEncodeService
import com.handarui.auth.service.TokenManager
import com.handarui.game.biz.bean.ResultEnum
import com.handarui.game.biz.query.UpdatePasswordQuery
import com.handarui.game.biz.service.AuthService
import com.handarui.game.biz.shiro.realm.ClearAbleAccountUserRealm
import com.handarui.game.dao.mapper.UserDoMapper
import com.zhexinit.ov.common.exception.CommonException
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired


@Service
class AuthServiceImpl: AuthService {

    @Autowired
    private lateinit var clearAbleAccountUserRealm: ClearAbleAccountUserRealm

    @Autowired
    private lateinit var userDoMapper: UserDoMapper

    @Autowired
    private lateinit var passwordEncodeService: PasswordEncodeService

    @Autowired
    private lateinit var tokenManager: TokenManager

    /**
     * 清空认证信息
     */
    override fun clearAuthorizationInfo() {
        clearAbleAccountUserRealm.clearAuthorizationInfo()
    }

    /**
     * 清空权限信息
     */
    override fun clearAuthenticationInfo() {
        clearAbleAccountUserRealm.clearAuthenticationInfo()
    }

    /**
     * 清空所有
     */
    override fun clearCache() {
        clearAbleAccountUserRealm.clearCache()
    }

    override fun updatePassword(query: UpdatePasswordQuery) {
        val userDo = userDoMapper.selectByPrimaryKey(query.id)
        val match = passwordEncodeService.match(query.oldPassword,userDo.password)
        if (match){
            if (query.oldPassword == query.newPassword){
                throw CommonException(ResultEnum.passwordEqual)
            }
            val encodePassword = passwordEncodeService.encode(query.newPassword)
            userDo.password = encodePassword
            userDoMapper.updateByPrimaryKeySelective(userDo)
            val tokens = tokenManager.getTokensByUserId(query.id)
            tokenManager.removeTokens(tokens)
        }else throw CommonException(ResultEnum.oldPasswordError)
    }
}