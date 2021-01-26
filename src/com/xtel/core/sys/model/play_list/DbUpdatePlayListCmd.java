package com.xtel.core.sys.model.play_list;

import com.xtel.core.dto.request.play_list.UpdatePlayListRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbUpdatePlayListCmd extends MultiCallableStatementCmd {
    private UpdatePlayListRequest request;
    private String update_by;

    public DbUpdatePlayListCmd(String transid, String channel, UpdatePlayListRequest request, String update_by) {
        super(transid, channel);
        this.request = request;
        this.update_by = update_by;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_PLAY_LIST.update_play_list", 7, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getPlay_list_id());
                setString(cst, i++, request.getPhone_number());
                setString(cst, i++, request.getPlay_list_name());
                setInt(cst, i++, request.getType());
                setString(cst, i++, update_by);
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
