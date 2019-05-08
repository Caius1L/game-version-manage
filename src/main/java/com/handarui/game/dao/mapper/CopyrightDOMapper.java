package com.handarui.game.dao.mapper;

import com.handarui.game.biz.bean.CopyrightListBean;
import com.handarui.game.biz.query.CopyrightListQuery;
import com.handarui.game.dao.domain.CopyrightInfoDO;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CopyrightDOMapper extends MyMapper<CopyrightInfoDO> {

    /**
     * 获取著作权列表
     *
     * @param data
     * @return
     */
    List<CopyrightListBean> selectCopyrightList(@Param("data") CopyrightListQuery data,@Param("departmentName")String departmentName);
}