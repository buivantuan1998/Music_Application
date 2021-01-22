package com.xtel.core.sys.model;

import com.nvt.xpersistence.processor.ColumnProcessor;
import com.tbv.utils.db.cmd.DbPagingCmd;
import com.xtel.core.sys.model.Procedure;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    @Author: TruongNV
 */
public abstract class CallableStatementCmd extends DbPagingCmd {

    public CallableStatementCmd(String transid, String channel) {
        super(transid, channel);
    }

    protected void getOutputResult() throws Exception {
        code = cst.getInt(1);
        message = cst.getString(2);
        if (code != 0) return;
        getResult();
    }

    protected void register(int type) throws Exception {
        cst.registerOutParameter(idx++, type);
    }

    protected <T> List<T> getList(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        if (rs != null) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            Field[] fields = clazz.getDeclaredFields();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i + 1).trim();
                    Field field = Arrays.stream(fields)
                            .filter(f -> ColumnProcessor.getColumnName(f) != null && ColumnProcessor.getColumnName(f).toUpperCase().equals(columnName.toUpperCase()))
                            .findFirst().orElse(null);

                    if(field != null){
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        final Class<?> fieldType = field.getType();
                        Object value = null;
                        if (Integer.class.equals(fieldType)) {
                            value = rs.getInt(columnName);
                        } else if (Long.class.equals(fieldType)) {
                            value = rs.getLong(columnName);
                        } else if (Float.class.equals(fieldType)) {
                            value = rs.getFloat(columnName);
                        } else if (String.class.equals(fieldType)) {
                            value = rs.getString(columnName);
                        } else if (Double.class.equals(fieldType)) {
                            value = rs.getDouble(columnName);
                        } else if (Timestamp.class.equals(fieldType)) {
                            value = rs.getTimestamp(columnName);
                        } else {
                            value = rs.getObject(columnName);
                        }
                        field.set(t, value);
                        field.setAccessible(isAccessible);
                    }
                }
                list.add(t);
            }

        }

        return list;
    }

    protected <T> List<T> getList(int index, Class<T> clazz) throws Exception {
        try (ResultSet rs = (ResultSet) cst.getObject(index)) {
            return getList(rs, clazz);
        }
    }

    protected <T> List<T> getList(Class<T> clazz) throws Exception {
        try (ResultSet rs = cst.getResultSet()) {
            return getList(rs, clazz);
        }
    }

    protected <T> T getSingle(int index, Class<T> clazz) throws Exception {
        List<T> list = getList(index,clazz);
        if(list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    protected <T> T getSingle(Class<T> clazz) throws Exception {
        List<T> list = getList(clazz);
        if(list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    protected abstract void getResult() throws Exception;

    protected void setProc(Procedure proc){
        super.setProc(proc.getName(), proc.getNumberParam());
    }

    protected void setFloat(Float v) throws SQLException{
        if(v == null){
            cst.setNull(idx++, Types.FLOAT);
        }
        else{
            cst.setFloat(idx++, v);
        }
        logger.debug(String.format("transid: %s, idx: %s, obj: %s",transid, idx, v));
    }

    protected boolean hasMoreResultSet() throws SQLException {
        return cst.getMoreResults();
    }
}
