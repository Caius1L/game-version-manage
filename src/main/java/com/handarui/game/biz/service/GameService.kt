package com.handarui.game.biz.service

import com.handarui.game.biz.bean.BaseGameBean
import com.handarui.game.biz.bean.GameBean
import com.handarui.game.biz.query.GameQuery
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import java.io.OutputStream

interface GameService {

    /**
     * 查询部门提请的游戏列表
     */
    fun getGameList(query: PagerQuery<GameQuery>, department: String? = null): ListBean<BaseGameBean>

    /**
     * 查询游戏详情
     */
    fun getGameDetail(gameId: Long): GameBean

    /**
     * 首页添加游戏
     */
    fun addBaseGame(baseGameBean: BaseGameBean)

    /**
     * 添加游戏详情
     */
    fun addGameDetail(gameBean: GameBean)

    /**
     * 根据id获取首页游戏详情
     */
    fun getBaseGameById(id: Long): BaseGameBean

    /**
     * 修改游戏基本信息
     */
    fun updateBaseGame(baseGameBean: BaseGameBean)

    /**
     * 批量删除游戏
     */
    fun batchDeleteGame(ids: List<Long>)

    /**
     * 批量添加游戏
     */
    fun addGameBatch(games: List<GameBean>)

    /**
     * 导出游戏
     */
    fun exportGame(query: GameQuery?, department: String? = null, outputStream: OutputStream)

}