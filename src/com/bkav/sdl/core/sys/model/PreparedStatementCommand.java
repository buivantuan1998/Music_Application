package com.bkav.sdl.core.sys.model;

import com.nvt.xpersistence.processor.ColumnProcessor;
import com.tbv.utils.db.cmd.DbCommand;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PreparedStatementCommand extends DbCommand {
    protected PreparedStatement ps;
    protected Connection conn;

    public PreparedStatementCommand(String transid, String channel) {
        super(transid, channel);
    }

    public void invoke(Connection conn) throws Exception {
        try {
            this.conn = conn;
            this.conn.setAutoCommit(false);
            execute();
            this.conn.commit();
        } catch (Exception var9) {
            this.setCode(999);
            this.setMessage(var9.getMessage());
            this.logger.error(String.format("[%s] transid: %s, channel: %s", Thread.currentThread().getName(), this.transid, this.channel), var9);
            if (this.conn != null) {
                try {
                    this.conn.rollback();
                } catch (SQLException var8) {
                    this.logger.error(String.format("[%s] transid: %s, channel: %s", Thread.currentThread().getName(), this.transid, this.channel), var8);
                }
            }
            var9.printStackTrace();
            throw var9;
        } finally {
            if (this.ps != null) {
                this.closeItem(this.ps);
            }

        }
    }


    protected abstract void execute() throws Exception;

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

}
