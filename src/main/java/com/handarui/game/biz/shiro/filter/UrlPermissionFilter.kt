package com.handarui.game.biz.shiro.filter

import com.handarui.auth.base.model.BasicPermissionVo
import com.handarui.auth.base.model.ClassicalUserVo
import com.handarui.auth.model.UserContext
import com.handarui.auth.service.TokenReader
import org.apache.shiro.util.AntPathMatcher
import org.apache.shiro.web.filter.AccessControlFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UrlPermissionFilter : AccessControlFilter() {

    @Autowired
    private lateinit var tokenReader: TokenReader

    override fun preHandle(servletRequest: ServletRequest, servletResponse: ServletResponse): Boolean {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse
        return if (request.method == RequestMethod.OPTIONS.name) {
            response.status = HttpStatus.OK.value()
            false
        } else {
            super.preHandle(request, response)
        }
    }

    override fun isAccessAllowed(servletRequest: ServletRequest, servletResponse: ServletResponse, any: Any?): Boolean {
        val token = (servletRequest as HttpServletRequest).getHeader("X-Auth-Token")
        val authenticated = tokenReader.checkToken(token)
        return if (!authenticated) {
            (servletResponse as HttpServletResponse).status = HttpStatus.UNAUTHORIZED.value()
            false
        } else {
            val userContext = tokenReader.getUserDetail(token) as UserContext<*>
            if (userContext.userVo !is ClassicalUserVo) {
                false
            } else {
                val userVo = userContext.userVo as ClassicalUserVo
                val requestURI = servletRequest.requestURI
                this.checkUrlPatternPermission(requestURI, userVo.permissions)
            }
        }
    }

    private fun checkUrlPatternPermission(requestURI: String, permissions: List<BasicPermissionVo>?): Boolean {
        if (permissions == null || permissions.isEmpty()) {
            return false
        }
        permissions.forEach {
            if (pathsMatch(it.name, requestURI)) {
                return true
            }
        }
        return false
    }

    override fun onAccessDenied(servletRequest: ServletRequest, servletResponse: ServletResponse): Boolean {
        val response = servletResponse as HttpServletResponse
        if (response.status == HttpStatus.UNAUTHORIZED.value()) {
            response.writer.write("Please login first")
        } else {
            response.status = HttpStatus.FORBIDDEN.value()
            response.writer.write("无权限")
        }
        return false
    }
}