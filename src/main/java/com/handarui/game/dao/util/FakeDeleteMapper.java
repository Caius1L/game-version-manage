package com.handarui.game.dao.util;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface FakeDeleteMapper<T> {

    /**
     * 通过主键删除
     * @param key
     * @return
     */
    @DeleteProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int fakeDeleteByPrimaryKey(Object key);

    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    @UpdateProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int fakeDeleteByExample(Object example);

}
