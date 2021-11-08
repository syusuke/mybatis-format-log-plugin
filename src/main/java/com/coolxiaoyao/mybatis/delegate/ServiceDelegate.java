package com.coolxiaoyao.mybatis.delegate;

import com.alibaba.druid.sql.parser.ParserException;
import com.coolxiaoyao.mybatislog.DbType;
import com.coolxiaoyao.mybatislog.LogResolveHelper;
import com.coolxiaoyao.mybatislog.MybatisLogHelper;
import com.coolxiaoyao.mybatislog.pair.SqlParamPair;
import com.coolxiaoyao.mybatislog.type.ParamItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author kerryzhang on 2021/10/29
 */


public class ServiceDelegate {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDelegate.class);

    public static List<String> getSupportSqlType() {
        final DbType[] values = DbType.values();
        List<String> list = new ArrayList<>(values.length);

        for (DbType value : values) {
            list.add(value.name());
        }
        list.add("Default");

        return list;
    }


    /**
     * 解析日志
     *
     * @param sourceLog
     * @param type
     * @return
     * @throws ParserException
     */
    public static List<String> formatSqlLog(String sourceLog, String type) {
        DbType dbType = DbType.valueOf(type);
        List<SqlParamPair> sqlParamPairs = LogResolveHelper.resolveLog(sourceLog);
        List<String> sqls = new ArrayList<>(sqlParamPairs.size());
        for (SqlParamPair sqlParamPair : sqlParamPairs) {
            String sql = sqlParamPair.getSql();
            String param = sqlParamPair.getParam();
            logger.debug("SQL: {}. Param: {}", sqlParamPair.getSql(), param);
            List<ParamItem> paramItems = LogResolveHelper.resolveParams(param);
            try {
                String targetSql = MybatisLogHelper.formatSql(sql, paramItems, dbType.name().toLowerCase(Locale.ROOT));
                sqls.add(targetSql);
            } catch (RuntimeException e) {
                logger.error("SQL: {}. Param: {}.Cause: {}", sqlParamPair.getSql(), param, e.getMessage());
            }
        }
        return sqls;
    }

}
