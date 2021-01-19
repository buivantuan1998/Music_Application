package com.xtel.core.sys.model.album;

import com.xtel.core.dto.response.album.GetDataAlbum;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListDataCmd extends CallableStatementCmd {
    private List<GetDataAlbum> data;
    public DbGetListDataCmd(String transid, String channel) {
        super(transid, channel);
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(GetDataAlbum.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_ALBUM.get_data", 3);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        register(OracleTypes.CURSOR);
    }

    public List<GetDataAlbum> getData() {
        return data;
    }
}
