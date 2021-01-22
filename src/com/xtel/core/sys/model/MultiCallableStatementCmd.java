package com.xtel.core.sys.model;

import com.nvt.xpersistence.processor.ColumnProcessor;
import com.tbv.utils.db.cmd.DbCommand;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MultiCallableStatementCmd extends DbCommand {
    protected CallableStatement cst;
    protected Connection conn;

    public MultiCallableStatementCmd(String transid, String channel) {
        super(transid, channel);
    }

    public void invoke(Connection conn) throws Exception {
        try {
            this.conn = conn;
            this.conn.setAutoCommit(false);
            long begin = System.currentTimeMillis();
            execute();
            logger.debug(String.format("transid:%s, duration time : %s ms", transid, System.currentTimeMillis() - begin));
            this.conn.commit();
        } catch (Exception var9) {
            this.setCode(999);
            this.setMessage(var9.getMessage());
            if(message.equals("URL_SEO_IS_EXISTED")) this.setCode(1026);
            this.logger.error(String.format("[%s] transid: %s, channel: %s", Thread.currentThread().getName(), this.transid, this.channel), var9);
            if (this.conn != null) {
                try {
                    this.conn.rollback();
                } catch (SQLException var8) {
                    this.logger.error(String.format("[%s] transid: %s, channel: %s", Thread.currentThread().getName(), this.transid, this.channel), var8);
                }
            }

            throw var9;
        } finally {
            if (this.cst != null) {
                this.closeItem(this.cst);
            }
        }
    }

    protected abstract void execute() throws Exception;

    protected String getProcedureCallQuery(String procedureName, int numberPr) {
        StringBuilder prSb = new StringBuilder();
        for (int i = 0; i < numberPr; i++) {
            prSb.append("?,");
        }
        String pr = prSb.toString().substring(0, prSb.toString().length()-1);
        return String.format("{CALL %s(%s)}", procedureName, pr);
    }

    protected boolean executeProcedure(String procName, int numberPr, MultiCallableStatementCmd.Procedure procedure) throws Exception{
        CallableStatement cst = null;
        try{
            String sql = getProcedureCallQuery(procName, numberPr);
            logger.debug(String.format("transid: %s, =====> START %s <=====", transid, sql.toUpperCase()));
            cst = conn.prepareCall(sql);
            procedure.setProcedure(cst);
            cst.execute();
            this.setCode(cst.getInt(1));
            this.setMessage(cst.getString(2));
            if(code != 0){
                rollback();
                return false;
            }
            procedure.getOutput(cst);
            logger.debug(String.format("transid: %s, =====> END %s <=====", transid, sql.toUpperCase()));
        }
        finally {
            closeItem(cst);
        }
        return true;
    }

    protected boolean executeBatchProcedure(String procName, int numberPr, MultiCallableStatementCmd.ExecuteBatch executeBatch) throws Exception{
        CallableStatement cst = null;
        try{
            String sql = getProcedureCallQuery(procName, numberPr);
            logger.debug(String.format("transid: %s, =====> START %s <=====", transid, sql.toUpperCase()));
            cst = conn.prepareCall(sql);
            executeBatch.setParameters(cst);
            int[] arrResult = cst.executeBatch();
            for(int i = 0; i < arrResult.length; ++i) {
                if (arrResult[i] >= 0) {
                    this.logger.debug(String.format("[%s] transid: %s, channel: %s, Batch at index: %s SUCCESS, execute result value: %s", Thread.currentThread().getId(), this.transid, this.channel, i, arrResult[i]));
                } else if (arrResult[i] == -2) {
                    this.logger.debug(String.format("[%s] transid: %s, channel: %s, Batch at index: %s SUCCESS_NO_INFO, execute result value: %s", Thread.currentThread().getId(), this.transid, this.channel, i, arrResult[i]));
                } else if (arrResult[i] == -3) {
                    this.logger.debug(String.format("[%s] transid: %s, channel: %s, Batch at index: %s EXECUTE_FAILED, execute result value: %s", Thread.currentThread().getId(), this.transid, this.channel, i, arrResult[i]));
                    this.setCode(999);
                    this.setMessage(String.format("% EXECUTE_FAILED", arrResult[i]));
                    rollback();
                    return false;
                }
            }
            logger.debug(String.format("transid: %s, =====> END %s <=====", transid, sql.toUpperCase()));
        }
        finally {
            closeItem(cst);
        }
        return true;
    }

    public interface Procedure{
        void setProcedure(CallableStatement cst) throws SQLException;
        void getOutput(CallableStatement cst) throws Exception;
    }

    protected void setInt(CallableStatement cst, int idx,Integer v) throws SQLException{
        if(v == null){
            cst.setNull(idx, 4);
        }
        else{
            cst.setInt(idx, v);
        }
        logger.debug(String.format("transid: %s, IN[%s] = %s", transid, idx, v));
    }

    protected void setString(CallableStatement cst, int idx, String v) throws SQLException{
        if(v == null){
            cst.setNull(idx, 12);
        }
        else{
            cst.setString(idx, v);
        }
        logger.debug(String.format("transid: %s, IN[%s] = %s", transid, idx, v));
    }

    protected void setFloat(CallableStatement cst, int idx, Float v) throws SQLException{
        if(v == null){
            cst.setNull(idx, 6);
        }
        else{
            cst.setFloat(idx, v);
        }
        logger.debug(String.format("transid: %s, IN[%s] = %s", transid, idx, v));
    }

    protected void setDouble(CallableStatement cst, int idx, Double v) throws SQLException{
        if(v == null){
            cst.setNull(idx, 8);
        }
        else{
            cst.setDouble(idx, v);
        }
        logger.debug(String.format("transid: %s, IN[%s] = %s", transid, idx, v));
    }

    protected void setTimestamp(CallableStatement cst, int idx, Timestamp v) throws SQLException{
        if(v == null){
            cst.setNull(idx, Types.TIMESTAMP);
        }
        else{
            cst.setTimestamp(idx, v);
        }
        logger.debug(String.format("transid: %s, IN[%s] = %s", transid, idx, v));
    }

    protected void setDate(CallableStatement cst, int idx, Date v) throws SQLException{
        if(v == null){
            cst.setNull(idx, Types.DATE);
        }
        else{
            cst.setDate(idx, v);
        }
        logger.debug(String.format("transid: %s, IN[%s] = %s", transid, idx, v));
    }

    protected void register(CallableStatement cst, int idx, int type) throws SQLException{
        cst.registerOutParameter(idx, type);
        logger.debug(String.format("transid: %s, OUT[%s] = %s", transid, idx, type));
    }

    protected void rollback() throws SQLException {
        this.conn.rollback();
        logger.debug(String.format("transid: %s, Transaction rollback SUCCESS !!!",transid));
    }

    protected interface ExecuteBatch{
        void setParameters(CallableStatement cst) throws Exception;
    }

    protected void addBatch(CallableStatement cst, int index) throws SQLException{
        cst.addBatch();
        logger.debug(String.format("transid: %s, Add batch[%s] success !", transid, index));
    }

    protected <T> List<T> getList(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        if (rs != null) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i + 1).trim();
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

    protected <T> T  getSingle(int index, Class<T> clazz) throws Exception {
        List<T> list = getList(index, clazz);
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    protected <T> T getSingle(Class<T> clazz) throws Exception {
        List<T> list = getList(clazz);
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    protected <T> T  getSingle(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = getList(rs, clazz);
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    protected boolean hasMoreResultSet() throws SQLException {
        return cst.getMoreResults();
    }
}

