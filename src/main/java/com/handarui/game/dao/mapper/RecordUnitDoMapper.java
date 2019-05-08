package com.handarui.game.dao.mapper;

import com.handarui.game.dao.domain.RecordUnitDo;
import com.handarui.game.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordUnitDoMapper extends MyMapper<RecordUnitDo> {

    List<RecordUnitDo> selectGameRecordUnit(@Param("id") Long id);
}