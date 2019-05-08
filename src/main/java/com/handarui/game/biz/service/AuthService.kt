package com.handarui.game.biz.service

import com.handarui.game.biz.query.UpdatePasswordQuery

interface AuthService {

    fun clearAuthorizationInfo()

    fun clearAuthenticationInfo()

    fun clearCache()

    fun updatePassword(query: UpdatePasswordQuery)
}