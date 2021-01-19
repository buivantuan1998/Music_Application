package com.xtel.core.sys.model.album;

import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.dto.response.album.GetDetailAlbumResponse;
import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetDetailAlbumCmd extends CallableStatementCmd {
    private Integer album_id;
    private Integer customer_id;
    private AlbumResponse albumResponse;
    private List<SongResponse> songResponseList;
    private GetDetailAlbumResponse data;

    public DbGetDetailAlbumCmd(String transid, String channel, Integer album_id, Integer customer_id) {
        super(transid, channel);
        this.album_id = album_id;
        this.customer_id = customer_id;
    }

    @Override
    protected void getResult() throws Exception {
        data = new GetDetailAlbumResponse();

        albumResponse = getSingle(AlbumResponse.class);
        data.setAlbumResponse(albumResponse);

    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_ALBUM.get_detail_data", 6);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(album_id);
        setInt(customer_id);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
    }

    public GetDetailAlbumResponse getData() {
        return data;
    }
}
