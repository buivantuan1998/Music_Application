package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.album.DeleteAlbumRequest;
import com.xtel.core.dto.request.album.InsertAlbumRequest;
import com.xtel.core.dto.request.album.UpdateAlbumRequest;
import com.xtel.core.sys.service.album.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("album")
public class AlbumController extends BaseController{
    @GET
    @Path("list")
    public Response getListAlbum(@QueryParam("page_index") Integer page_index,
                                 @QueryParam("page_size") Integer page_size,
                                 @QueryParam("search_name") String search_name,
                                 @QueryParam("order_by") String order_by) {
        GetListAlbumCmd cmd = new GetListAlbumCmd(httpServletRequest, page_index, page_size, search_name, order_by);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("detail")
    public Response getDetailAlbum(@QueryParam("album_id") Integer album_id,
                                   @QueryParam("phone_number") String phone_number) {
        GetDetailAlbumCmd cmd = new GetDetailAlbumCmd(httpServletRequest, album_id, phone_number);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("list_album")
    public Response getList() {
        GetListDataCmd cmd = new GetListDataCmd(httpServletRequest);
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

    @POST
    @Path("update")
    public Response updateAlbum(String body) {
        UpdateAlbumCmd cmd = new UpdateAlbumCmd(httpServletRequest, body, UpdateAlbumRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("delete")
    public Response deleteAlbum(String body) {
        DeleteAlbumCmd cmd = new DeleteAlbumCmd(httpServletRequest, body, DeleteAlbumRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }
}
