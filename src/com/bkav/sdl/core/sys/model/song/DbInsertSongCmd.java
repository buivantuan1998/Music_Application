package com.bkav.sdl.core.sys.model.song;

import com.bkav.sdl.core.sys.model.CallableStatementCmd;

import java.sql.Types;

public class DbInsertSongCmd extends CallableStatementCmd {
    private String song_name;
    private String url;
    private Double time;
    private String singer_name;
    private String musician_name;
    private Integer category_id;
    private String create_by;
    private Integer song_id;

    public DbInsertSongCmd(String transid, String channel, String song_name, String url, Double time, String singer_name,
                           String musician_name, Integer category_id, String create_by) {
        super(transid, channel);
        this.song_name = song_name;
        this.url = url;
        this.time = time;
        this.singer_name = singer_name;
        this.musician_name = musician_name;
        this.category_id = category_id;
        this.create_by = create_by;
    }

    @Override
    protected void getResult() throws Exception {
        song_id = cst.getInt(10);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.insert_song", 10);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(song_name);
        setString(url);
        setDouble(time);
        setString(singer_name);
        setString(musician_name);
        setInt(category_id);
        setString(create_by);
        register(Types.INTEGER);
    }

    public Integer getSong_id() {
        return song_id;
    }
}
