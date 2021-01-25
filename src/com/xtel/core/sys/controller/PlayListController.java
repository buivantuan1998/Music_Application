package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.album.InsertAlbumRequest;
import com.xtel.core.sys.service.album.GetListDataCmd;
import com.xtel.core.sys.service.album.InsertAlbumCmd;
import com.xtel.core.sys.service.play_list.GetDetailPlayListCmd;
import com.xtel.core.sys.service.play_list.GetListPlayListCmd;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("play_list")
public class PlayListController extends BaseController {
    @GET
    @Path("list")
    public Response getList(@QueryParam("page_index") Integer page_index,
                            @QueryParam("page_size") Integer page_size,
                            @QueryParam("search_name") String search_name,
                            @QueryParam("order_by") String order_by,
                            @QueryParam("phone_number") String phone_number) {
        GetListPlayListCmd cmd = new GetListPlayListCmd(httpServletRequest, page_index, page_size, search_name, order_by, phone_number);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("detail")
    public Response getList(@QueryParam("play_list_id") Integer play_list_id,
                            @QueryParam("phone_number") String phone_number) {
        GetDetailPlayListCmd cmd = new GetDetailPlayListCmd(httpServletRequest, play_list_id, phone_number);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("insert")
    public Response insertAlbum(String body) {
        InsertAlbumCmd cmd = new InsertAlbumCmd(httpServletRequest, body, InsertAlbumRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }
}
