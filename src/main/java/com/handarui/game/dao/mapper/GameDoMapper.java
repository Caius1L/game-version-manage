package com.handarui.game.dao.mapper;

import com.handarui.game.biz.bean.BaseGameBean;
import com.handarui.game.biz.query.GameQuery;
import com.handarui.game.dao.domain.GameDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDoMapper extends MyMapper<GameDo> {

    List<BaseGameBean> selectBaseGameList(@Param("data") GameQuery data, @Param("department") String department);

    BaseGameBean selectBaseGameById(@Param("id") long id);

    void batchDeleteGame(@Param("ids") List<Long> ids);

    void updateNullValue(@Param("data") GameDo gameDo);
}