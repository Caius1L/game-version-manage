package com.handarui.game.biz.config

import com.baidu.disconf.client.common.annotations.DisconfFile
import com.baidu.disconf.client.common.annotations.DisconfFileItem
import org.springframework.stereotype.Component

@Component
@DisconfFile(filename = "redis.properties")
class RedisDisconfConfig {

    @get:DisconfFileItem(name = "spring.redis.host")
    var redisHost: String? = null

    @get:DisconfFileItem(name = "spring.redis.password")
    var redisPassword: String? = null

    @get:DisconfFileItem(name = "spring.redis.port")
    var redisPort: Int = 0

    @get:DisconfFileItem(name = "spring.redis.timeout")
    var redisTimeOut: Int = 0

    @get:DisconfFileItem(name = "spring.redis.tokenStorageNamespace")
    var tokenStorageNamespace: String? = null

    @get:DisconfFileItem(name = "spring.redis.sessionStorageNamespace")
    var sessionStorageNamespace: String ?=null

    @get:DisconfFileItem(name = "spring.redis.tokenExpiration")
    var tokenExpiration: Long = 0

}