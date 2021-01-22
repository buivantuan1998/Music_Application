package com.xtel.core.dto.request.album;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteAlbumRequest {
    private Integer customer_id;
    private Integer album_id;
}
