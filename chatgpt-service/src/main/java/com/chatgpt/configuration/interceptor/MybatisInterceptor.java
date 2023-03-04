package com.chatgpt.configuration.interceptor;

import com.chatgpt.constant.Constant;
import com.chatgpt.context.UserContext;
import com.chatgpt.domain.BaseDomain;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * mybatis拦截器
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getArgs().length == Constant.ONE){
            return invocation.proceed();
        }
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[Constant.ZERO];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[Constant.ONE];

        if(parameter instanceof BaseDomain) {
            BaseDomain baseDomain = (BaseDomain) parameter;
            if(sqlCommandType.equals(SqlCommandType.INSERT) || sqlCommandType.equals(SqlCommandType.UPDATE)) {
                baseDomain.setCreateBy(UserContext.getUser());
                baseDomain.setUpdateBy(UserContext.getUser());
            }
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
