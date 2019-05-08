package com.handarui.game.dao.mapper;

import com.handarui.game.dao.domain.VersionMaterialDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionMaterialDoMapper extends MyMapper<VersionMaterialDo> {

    List<VersionMaterialDo> getByGameId(@Param("gameId") Long gameId);
}