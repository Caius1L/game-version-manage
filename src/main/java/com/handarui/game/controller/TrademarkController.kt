package com.handarui.game.controller

import com.handarui.game.biz.bean.TrademarkBean
import com.handarui.game.biz.bean.TrademarkListBean
import com.handarui.game.biz.query.AddTrademarkQuery
import com.handarui.game.biz.query.ModifyTrademarkQuery
import com.handarui.game.biz.query.TrademarkListQuery
import com.handarui.game.biz.service.TrademarkService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import com.zhexinit.ov.common.query.SortPagerQuery
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 商标Controller
 * @Author: xiebaobiao
 * @Date: 2019/2/18 0018 11:15
 */
@RestController
@RequestMapping("trademark")
class TrademarkController {
    @Autowired
    private lateinit var trademarkService: TrademarkService

    /**
     * 添加商标
     */
    @PostMapping("addTrademark")
    fun addTrademark(@RequestBody requestBean: RequestBean<AddTrademarkQuery>): ResponseBean<Void> {
        trademarkService.addTrademark(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 删除商标
     */
    @PostMapping("batchDeleteTrademark")
    fun batchDeleteTrademark(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        trademarkService.batchDeleteTrademark(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 修改商标
     */
    @PostMapping("modifyTrademark")
    fun modifyTrademark(@RequestBody requestBean: RequestBean<ModifyTrademarkQuery>): ResponseBean<Void> {
        trademarkService.modifyTrademark(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 通过id获取商标信息
     */
    @PostMapping("getTrademarkById")
    fun getTrademarkById(@RequestBody requestBean: RequestBean<Long>): ResponseBean<TrademarkBean> {
        return ResponseUtil.success(trademarkService.getTrademarkById(requestBean.param))
    }

    /**
     * 获取商标列表
     */
    @PostMapping("getTrademarkList")
    fun getTrademarkList(@RequestBody requestBean: RequestBean<PagerQuery<TrademarkListQuery>>): ResponseBean<ListBean<TrademarkListBean>?>? {
        return ResponseUtil.success(trademarkService.getTrademarkList(requestBean.param))
    }
}