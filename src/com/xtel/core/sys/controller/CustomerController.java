package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.album.InsertAlbumRequest;
import com.xtel.core.sys.service.album.GetListDataCmd;
import com.xtel.core.sys.service.album.InsertAlbumCmd;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("customer")
public class CustomerController extends BaseController{
    @GET
    @Path("register")
    public Response getList() {
        GetListDataCmd cmd = new GetListDataCmd(httpServletRequest);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("login")
    public Response insertAlbum(String body) {
        InsertAlbumCmd cmd = new InsertAlbumCmd(httpServletRequest, body, InsertAlbumRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }
}
