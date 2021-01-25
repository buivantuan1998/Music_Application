package com.xtel.core.dto.response.play_list;

import com.xtel.core.dto.response.song.SongResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class DataPlayList {
    private PlayListData playListData;
    private List<SongResponse> songResponse;
}
