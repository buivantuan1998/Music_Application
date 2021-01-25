package com.xtel.core.sys.model.album;

import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.dto.response.album.GetDetailAlbumResponse;
import com.xtel.core.dto.response.album.GetListSongInAlbum;
import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetDetailAlbumCmd extends CallableStatementCmd {
    private Integer album_id;
    private String phone_number;
    private GetDetailAlbumResponse data;

    public DbGetDetailAlbumCmd(String transid, String channel, Integer album_id, String phone_number) {
        super(transid, channel);
        this.album_id = album_id;
        this.phone_number = phone_number;
    }

    @Override
    protected void getResult() throws Exception {
        data = new GetDetailAlbumResponse();

        AlbumResponse albumResponse = getSingle(5, AlbumResponse.class);
        data.setAlbumResponse(albumResponse);

        List<GetListSongInAlbum> getListSongInAlbums = getList(6, GetListSongInAlbum.class);
        data.setSongResponseList(getListSongInAlbums);

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
        setString(phone_number);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
    }

    public GetDetailAlbumResponse getData() {
        return data;
    }
}
