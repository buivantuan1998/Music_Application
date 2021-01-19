package com.xtel.core.sys.model;

import com.nvt.xpersistence.processor.ColumnProcessor;
import com.tbv.utils.db.cmd.DbPagingCmd;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
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

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1).trim();
                    Field[] fields = clazz.getDeclaredFields();
                    Field field = null;
                    for(Field f : fields){
                        String fieldName = ColumnProcessor.getColumnName(f);
                        if(fieldName != null){
                            if(fieldName.equals(columnName)){
                                field = f;
                            }
                        }
                    }

                    if(field != null){
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        field.set(t, field.getType().cast(rs.getObject(columnName)));
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
}
