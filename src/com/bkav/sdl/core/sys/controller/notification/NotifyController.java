package com.bkav.sdl.core.sys.controller.notification;

import com.bkav.sdl.core.sys.controller.BaseController;
import com.bkav.sdl.core.sys.service.notification.NotifyGetListCmd;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cms/notify")
public class NotifyController extends BaseController {

    @GET
    @Path("list/{user_id}")
    public Response getListNotify(@PathParam("user_id") Integer user_id){
        NotifyGetListCmd cmd = new NotifyGetListCmd(httpServletRequest, user_id);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("update")
    public Response updateNotifySetting(){
        return null;
    }
}
