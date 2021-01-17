package com.bkav.sdl.core.sys.model;

import com.tbv.utils.db.cmd.DbCommand;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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

            throw var9;
        } finally {
            if (this.cst != null) {
                this.closeItem(this.cst);
            }
        }
    }

    protected abstract void execute() throws Exception;
}
