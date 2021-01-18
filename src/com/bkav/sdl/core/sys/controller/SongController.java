package com.bkav.sdl.core.sys.controller;

import com.bkav.sdl.core.dto.request.song.DeleteSongRequest;
import com.bkav.sdl.core.dto.request.song.InsertSongRequest;
import com.bkav.sdl.core.sys.service.song.DeleteSongCmd;
import com.bkav.sdl.core.sys.service.song.GetDetailSongCmd;
import com.bkav.sdl.core.sys.service.song.GetListSongCmd;
import com.bkav.sdl.core.sys.service.song.InsertSongCmd;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("song")
public class SongController extends BaseController{
    @GET
    @Path("list")
    public Response getList(@QueryParam("page_index") Integer page_index,
                            @QueryParam("page_size") Integer page_size,
                            @QueryParam("search_name") String search_name,
                            @QueryParam("order_by") String order_by) {
        GetListSongCmd cmd = new GetListSongCmd(httpServletRequest, page_index, page_size, search_name, order_by);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("delete")
    public Response deleteSong(String body) {
        DeleteSongCmd cmd = new DeleteSongCmd(httpServletRequest, body, DeleteSongRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("detail")
    public Response getDetail(@QueryParam("song_id") Integer song_id) {
        GetDetailSongCmd cmd = new GetDetailSongCmd(httpServletRequest, song_id);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("insert")
    public Response insertSong(String body) {
        InsertSongCmd cmd = new InsertSongCmd(httpServletRequest, body, InsertSongRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }
}
