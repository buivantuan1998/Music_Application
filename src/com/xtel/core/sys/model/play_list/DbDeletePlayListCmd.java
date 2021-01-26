package com.xtel.core.sys.model.play_list;

import com.xtel.core.dto.request.play_list.DeletePlayListRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbDeletePlayListCmd extends MultiCallableStatementCmd {
    private DeletePlayListRequest request;

    public DbDeletePlayListCmd(String transid, String channel, DeletePlayListRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_PLAY_LIST.delete_play_list", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getPhone_number());
                setInt(cst, i++, request.getPlay_list_id());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
