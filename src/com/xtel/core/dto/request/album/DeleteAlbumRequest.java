package com.xtel.core.dto.request.album;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteAlbumRequest {
    private String phone_number;
    private Integer album_id;
}
