package com.xtel.core.sys.model.album;

import com.xtel.core.dto.request.album.UpdateAlbumRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbUpdateAlbumCmd extends MultiCallableStatementCmd {
    private UpdateAlbumRequest request;
    private String update_by;

    public DbUpdateAlbumCmd(String transid, String channel, UpdateAlbumRequest request, String update_by) {
        super(transid, channel);
        this.request = request;
        this.update_by = update_by;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_ALBUM.update_data", 6, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getAlbum_id());
                setString(cst, i++, request.getAlbum_name());
                setInt(cst, i++, request.getCustomer_id());
                setString(cst, i++, update_by);
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message  = cst.getString(2);
            }
        });
    }
}
