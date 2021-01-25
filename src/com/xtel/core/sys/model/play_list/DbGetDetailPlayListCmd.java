package com.xtel.core.sys.model.play_list;

import com.xtel.core.dto.response.play_list.DataPlayList;
import com.xtel.core.dto.response.play_list.PlayListData;
import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetDetailPlayListCmd extends CallableStatementCmd {
    private Integer play_list_id;
    private String phone_number;

    private DataPlayList data;

    public DbGetDetailPlayListCmd(String transid, String channel, Integer play_list_id, String phone_number) {
        super(transid, channel);
        this.play_list_id = play_list_id;
        this.phone_number = phone_number;
    }

    @Override
    protected void getResult() throws Exception {
        data = new DataPlayList();

        PlayListData playListData = getSingle(5, PlayListData.class);
        data.setPlayListData(playListData);

        List<SongResponse> songResponseList = getList(6, SongResponse.class);
        data.setSongResponse(songResponseList);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_PLAY_LIST.get_detail_play_list", 6);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(play_list_id);
        setString(phone_number);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
    }

    public DataPlayList getData() {
        return data;
    }
}
