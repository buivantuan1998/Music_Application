package com.xtel.core.dto.request.album;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateAlbumRequest extends InsertAlbumRequest{
    private Integer album_id;
}
