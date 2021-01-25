package com.xtel.core.dto.response.album;

import com.xtel.core.dto.response.song.SongResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class GetDetailAlbumResponse {
    private AlbumResponse albumResponse;
    private List<GetListSongInAlbum> songResponseList;
}
