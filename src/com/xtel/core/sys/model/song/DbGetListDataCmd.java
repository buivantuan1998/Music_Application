package com.xtel.core.sys.model.song;

import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListDataCmd extends CallableStatementCmd {
    private List<SongResponse> data;
    public DbGetListDataCmd(String transid, String channel) {
        super(transid, channel);
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(3, SongResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.get_list_data", 3);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        register(OracleTypes.CURSOR);
    }

    public List<SongResponse> getData() {
        return data;
    }
}
