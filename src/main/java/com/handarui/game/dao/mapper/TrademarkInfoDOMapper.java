package com.handarui.game.dao.mapper;

import com.handarui.game.biz.bean.TrademarkListBean;
import com.handarui.game.biz.query.TrademarkListQuery;
import com.handarui.game.dao.domain.TrademarkInfoDO;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrademarkInfoDOMapper extends MyMapper<TrademarkInfoDO> {
    /**
     * 获取商标列表
     *
     * @param data
     * @return
     */
    List<TrademarkListBean> getTrademarkList(@Param("data") TrademarkListQuery data);
}