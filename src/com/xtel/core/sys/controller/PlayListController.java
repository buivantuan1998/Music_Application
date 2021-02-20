package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.play_list.DeletePlayListRequest;
import com.xtel.core.dto.request.play_list.InsertPlayListRequest;
import com.xtel.core.dto.request.play_list.UpdatePlayListRequest;
import com.xtel.core.sys.service.play_list.*;

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
    public Response getDetail(@QueryParam("play_list_id") Integer play_list_id,
                              @QueryParam("phone_number") String phone_number) {
        GetDetailPlayListCmd cmd = new GetDetailPlayListCmd(httpServletRequest, play_list_id, phone_number);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("insert")
    public Response insertPlayList(String body) {
        InsertPlayListCmd cmd = new InsertPlayListCmd(httpServletRequest, body, InsertPlayListRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("update")
    public Response updatePlayList(String body) {
        UpdatePlayListCmd cmd = new UpdatePlayListCmd(httpServletRequest, body, UpdatePlayListRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("delete")
    public Response deletePlayList(String body) {
        DeletePlayListCmd cmd = new DeletePlayListCmd(httpServletRequest, body, DeletePlayListRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("song_random")
    public Response randomSongInPlayList(@QueryParam("phone_number") String phone_number,
                                         @QueryParam("play_list_id") Integer play_list_id) {
        GetRandomSongInPlayListCmd cmd = new GetRandomSongInPlayListCmd(httpServletRequest, phone_number, play_list_id);
        cmd.execute();
        return cmd.getResponse();
    }
}
