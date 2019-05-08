package com.handarui.game.biz.service

import com.handarui.game.biz.bean.TrademarkBean
import com.handarui.game.biz.bean.TrademarkListBean
import com.handarui.game.biz.query.AddTrademarkQuery
import com.handarui.game.biz.query.ModifyTrademarkQuery
import com.handarui.game.biz.query.TrademarkListQuery
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery

/**
 * 商标Service
 * @Author: xiebaobiao
 * @Date: 2019/2/18 0018 11:51
 */
interface TrademarkService {
    /**
     * 添加商标
     */
    fun addTrademark(addTrademarkQuery: AddTrademarkQuery?)

    /**
     * 批量删除商标
     */
    fun batchDeleteTrademark(ids: List<Long>?)

    /**
     * 修改商标
     */
    fun modifyTrademark(modifyTrademarkQuery: ModifyTrademarkQuery?)

    /**
     * 通过id获取商标
     */
    fun getTrademarkById(id: Long): TrademarkBean?

    /**
     * 获取商标列表
     */
    fun getTrademarkList(trademarkListQuery: PagerQuery<TrademarkListQuery>): ListBean<TrademarkListBean>?

}