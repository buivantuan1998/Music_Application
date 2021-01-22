package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.song.*;
import com.xtel.core.sys.service.song.*;

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
                            @QueryParam("order_by") String order_by,
                            @QueryParam("play_list_id") Integer play_list_id,
                            @QueryParam("customer_id") Integer customer_id,
                            @QueryParam("album_id") Integer album_id) {
        GetListSongCmd cmd = new GetListSongCmd(httpServletRequest, page_index, page_size, search_name, order_by, play_list_id, customer_id, album_id);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("list_data")
    public Response getListData() {
        GetListDataCmd cmd = new GetListDataCmd(httpServletRequest);
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

    @POST
    @Path("update")
    public Response updateSong(String body) {
        UpdateSongCmd cmd = new UpdateSongCmd(httpServletRequest, body, UpdateSongRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("get_song_default")
    public Response getSongDefault(@QueryParam("customer_id") Integer customer_id) {
        GetSongDefaultCmd cmd = new GetSongDefaultCmd(httpServletRequest, customer_id);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("set_default")
    public Response setDefaultSong(String body) {
        SetDefaultSongCmd cmd = new SetDefaultSongCmd(httpServletRequest, body, SetDefaultSongRequest.class);
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

    @POST
    @Path("delete_song_in_play_list")
    public Response deleteSongInPlayList(String body) {
        DeleteSongInPlayListCmd cmd = new DeleteSongInPlayListCmd(httpServletRequest, body, DeleteSongInPlayListRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("delete_song_in_album")
    public Response deleteSongInAlbum(String body) {
        DeleteSongInAlbumCmd cmd = new DeleteSongInAlbumCmd(httpServletRequest, body, DeleteSongInAlbumRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("insert_song_in_play_list")
    public Response insertSongInPlayList(String body) {
        InsertSongInPlayListCmd cmd = new InsertSongInPlayListCmd(httpServletRequest, body, InsertSongInPlayListRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("insert_song_in_album")
    public Response insertSongInAlbum(String body) {
        InsertSongInAlbumCmd cmd = new InsertSongInAlbumCmd(httpServletRequest, body, InsertSongInAlbumRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

}
