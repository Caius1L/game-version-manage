package com.handarui.game.dao.util;

import com.zhexinit.ov.common.util.PropertyUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class UpdateInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement stmt = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        if (stmt == null) {
            return invocation.proceed();
        }

        if (stmt.getSqlCommandType().equals(SqlCommandType.INSERT)) {
            if (param != null) {
                if (PropertyUtil.getPropertyDescriptor(param.getClass(), "createdAt") != null) {
                    PropertyUtil.setProperty(param, "createdAt", System.currentTimeMillis() / 1000);
                }

                if (PropertyUtil.getPropertyDescriptor(param.getClass(), "updatedAt") != null) {
                    PropertyUtil.setProperty(param, "updatedAt", System.currentTimeMillis() / 1000);
                }
            }
        }

        if (stmt.getSqlCommandType().equals(SqlCommandType.UPDATE)) {
            if (param != null) {
                if (PropertyUtil.getPropertyDescriptor(param.getClass(), "updatedAt") != null) {
                    PropertyUtil.setProperty(param, "updatedAt", System.currentTimeMillis() / 1000);
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
