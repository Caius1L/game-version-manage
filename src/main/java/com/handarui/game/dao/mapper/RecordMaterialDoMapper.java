package com.handarui.game.dao.mapper;

import com.handarui.game.dao.domain.RecordMaterialDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMaterialDoMapper extends MyMapper<RecordMaterialDo> {

    List<RecordMaterialDo> selectByGameId(@Param("gameId") Long gameId);

}