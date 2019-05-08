package com.handarui.game.biz.config

import com.baidu.disconf.client.common.annotations.DisconfFile
import com.baidu.disconf.client.common.annotations.DisconfFileItem
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope("singleton")
@DisconfFile(filename = "mysql.properties")
class MysqlConfig {

    @get:DisconfFileItem(name = "spring.datasource.url")
    var dataSourceUrl: String? = null

    @get:DisconfFileItem(name = "spring.datasource.driver-class-name")
    var dataSourceDriverClassName: String? = null

    @get:DisconfFileItem(name = "spring.datasource.username")
    var dataSourceUsername: String? = null

    @get:DisconfFileItem(name = "spring.datasource.password")
    var dataSourcePassword: String? = null

    @get:DisconfFileItem(name = "spring.datasource.maxPoolSize")
    var maxPoolSize: Int? = null

}
