package com.xtel.core.dto.request.album;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsertAlbumRequest {
    private Integer customer_id;
    private String create_by;
    private String album_name;
}
