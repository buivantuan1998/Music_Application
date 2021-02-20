package com.xtel.core.sys.model.play_list;

import com.xtel.core.dto.response.play_list.RandomSongInPlayList;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;

public class DbGetRandomSongInPlayListCmd extends CallableStatementCmd {
    private String phone_number;
    private Integer play_list_id;
    private RandomSongInPlayList data;

    public DbGetRandomSongInPlayListCmd(String transid, String channel, String phone_number, Integer play_list_id) {
        super(transid, channel);
        this.phone_number = phone_number;
        this.play_list_id = play_list_id;
    }

    @Override
    protected void getResult() throws Exception {
        data = getSingle(5, RandomSongInPlayList.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_PLAY_LIST.play_random_play_list", 5);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(phone_number);
        setInt(play_list_id);
        register(OracleTypes.CURSOR);
    }

    public RandomSongInPlayList getData() {
        return data;
    }
}
