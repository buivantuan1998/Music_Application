package com.bkav.sdl.core.sys.controller.user;

import com.bkav.sdl.core.sys.controller.BaseController;
import com.bkav.sdl.core.sys.service.user.UserGetDetailCmd;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("cms/user")
public class UserController extends BaseController {
    @GET
    @Path("detail/{id}")
    public Response getDetailUser(@PathParam("id") Integer id){
        UserGetDetailCmd cmd = new UserGetDetailCmd(httpServletRequest, id);
        cmd.execute();
        return cmd.getResponse();
    }
}
