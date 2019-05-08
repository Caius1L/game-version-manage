package com.handarui.game.dao.mapper;

import com.handarui.game.biz.bean.PatentListBean;
import com.handarui.game.biz.query.PatentListQuery;
import com.handarui.game.dao.domain.PatentInfoDO;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatentInfoDOMapper extends MyMapper<PatentInfoDO> {
    /**
     * 获取专利列表
     *
     * @param data
     * @return
     */
    List<PatentListBean> getPatentList(@Param("data") PatentListQuery data,@Param("department")String department);
}