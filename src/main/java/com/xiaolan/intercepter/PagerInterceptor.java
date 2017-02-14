package com.xiaolan.intercepter;

import com.xiaolan.po.Pager;
import com.xiaolan.staticconfig.StaticConfig;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * Created by itsxu on 2016/8/26.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagerInterceptor implements Interceptor {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public Object intercept(Invocation invocation) throws Throwable {
        log.info("开始代理...");
        StatementHandler sh = (StatementHandler) invocation.getTarget();
        MetaObject mo = MetaObject.forObject(sh, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement ms = (MappedStatement) mo.getValue("delegate.mappedStatement");
        String queryMethod = ms.getId();
        log.info("抽出的方法是" + queryMethod);
        if (queryMethod.endsWith("Inpages")) {
            log.info("方法符合规则，开始增强");
            BoundSql bs = sh.getBoundSql();
            String sql = bs.getSql();
            log.info("抽出sql语句：" + sql);
            String countSql = "select count(0) from (" + sql + ")";
            Connection con = (Connection) invocation.getArgs()[0];
            PreparedStatement pst = con.prepareStatement(countSql);
            ParameterHandler ph = (ParameterHandler) mo.getValue("delegate.parameterHandler");
            ph.setParameters(pst);
            ResultSet rs = pst.executeQuery();
            int _TotalRows = 0;
            while (rs.next()) {
                _TotalRows = rs.getInt(1);
            }
            Map<?, ?> parameter = (Map<?, ?>) bs.getParameterObject();
            Pager pager = (Pager) parameter.get("pager");
            pager.setTotalRows(_TotalRows);
            pager.setTotalPages(pager.getTotalRows(), pager.getRows());
            if (pager.getCurPage() > pager.getTotalPages()) {
                pager.setCurPage(pager.getTotalPages());
            }
            pager.setHeader(pager.getCurPage(), pager.getRows());
            if (pager.getRows() == -1) {
                pager.setTotalPages(1);
                pager.setHeader(1);
                pager.setFooter(_TotalRows);
            } else {
                pager.setFooter(pager.getHeader());
            }
            String sqlPro = sql + " limit " + pager.getHeader() + " , " + StaticConfig.page_size;
            mo.setValue("delegate.boundSql.sql", sqlPro);
        } else {
            log.info("抽出的方法不符合规则，放行...");
        }
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        System.out.println("拦截到数据库访问，尝试代理...");
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {
        //可以通过全局变量来用属性值
        String databaseType = properties.getProperty("databaseType");
        System.out.println("读取到属性值：" + databaseType);
    }
}