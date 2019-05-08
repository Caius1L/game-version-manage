package com.handarui.game.biz.shiro.realm

import com.handarui.auth.shiro.realm.ClassicalAccountRealm
import org.apache.shiro.SecurityUtils
import org.springframework.stereotype.Service

@Service
class ClearAbleAccountUserRealm : ClassicalAccountRealm() {

    /**
     * 清空权限信息
     */
    fun clearAuthorizationInfo(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().principals)
    }

    /**
     * 清空登录信息
     */
    fun clearAuthenticationInfo(){
        this.clearCachedAuthenticationInfo(SecurityUtils.getSubject().principals)
    }

    /**
     * 清空所有
     */
    fun clearCache(){
        clearAuthorizationInfo()
        clearAuthenticationInfo()
    }
}