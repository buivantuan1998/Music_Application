package com.xtel.core.sys.model.play_list;

import com.xtel.core.dto.request.play_list.InsertPlayListRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbInsertPlayListCmd extends MultiCallableStatementCmd {
    private InsertPlayListRequest request;
    private Integer play_list_id;
    private String create_by;
    public DbInsertPlayListCmd(String transid, String channel, InsertPlayListRequest request, String create_by) {
        super(transid, channel);
        this.request = request;
        this.create_by = create_by;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_PLAY_LIST.insert_play_list", 7, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getPhone_number());
                setString(cst, i++, request.getPlay_list_name());
                setInt(cst, i++, request.getType());
                setString(cst, i++, create_by);
                register(cst, i++, Types.INTEGER);
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
                play_list_id = cst.getInt(7);
            }
        });
    }

    public Integer getPlay_list_id() {
        return play_list_id;
    }
}
