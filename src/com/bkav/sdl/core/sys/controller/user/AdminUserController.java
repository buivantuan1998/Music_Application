package com.bkav.sdl.core.sys.controller.user;

import com.bkav.sdl.core.dto.request.user.AdminUserInsertRequest;
import com.bkav.sdl.core.sys.controller.BaseController;
import com.bkav.sdl.core.sys.service.user.AdminUserInsertCmd;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("cms/admin")
public class AdminUserController extends BaseController {
    @GET
    @Path("list")
    public Response getList(@QueryParam("keyword") String keyword,
                            @QueryParam("page_index") int page_index,
                            @QueryParam("page_size") int page_size) {
        //todo get list admin user
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("detail")
    public Response getDetail(@QueryParam("user_id") int user_id) {
        //todo get detail admin user
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("config/notification")
    public Response getConfigNotification(@QueryParam("user_id") int user_id) {
        //todo get config notification ...
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("role")
    public Response getRole(@QueryParam("user_id") int user_id){
        //todo get role ...
        throw new UnsupportedOperationException();
    }

    @Path("insert")
    @POST
    public Response insert(String body){
        //todo insert new admin user
        AdminUserInsertCmd cmd = new AdminUserInsertCmd(httpServletRequest, body, AdminUserInsertRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @Path("update")
    @POST
    public Response update(String body){
        //todo update admin user
        throw  new UnsupportedOperationException();
    }

    @Path("delete")
    @POST
    public Response delete(String body){
        //todo delete admin user
        throw  new UnsupportedOperationException();
    }
}
