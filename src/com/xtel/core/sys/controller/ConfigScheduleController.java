package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.config_schedule.DeleteConfigScheduleRequest;
import com.xtel.core.dto.request.config_schedule.InsertConfigScheduleRequest;
import com.xtel.core.dto.request.config_schedule.UpdateConfigScheduleRequest;
import com.xtel.core.dto.request.config_schedule.UpdateStatusConfigScheduleRequest;
import com.xtel.core.sys.service.config_schedule.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("config_schedule")
public class ConfigScheduleController extends BaseController{
    @POST
    @Path("insert")
    public Response insertConfigSchedule(String body) {
        InsertConfigScheduleCmd cmd = new InsertConfigScheduleCmd(httpServletRequest, body, InsertConfigScheduleRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("update")
    public Response updateConfigSchedule(String body) {
        UpdateConfigScheduleCmd cmd = new UpdateConfigScheduleCmd(httpServletRequest, body, UpdateConfigScheduleRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("update/status")
    public Response updateStatusConfigSchedule(String body) {
        UpdateStatusConfigScheduleCmd cmd = new UpdateStatusConfigScheduleCmd(httpServletRequest, body, UpdateStatusConfigScheduleRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("list")
    public Response getListConfigSchedule(@QueryParam("customer_id") Integer customer_id,
                                          @QueryParam("phone_number") String phone_number) {
        GetListConfigScheduleCmd cmd = new GetListConfigScheduleCmd(httpServletRequest, customer_id, phone_number);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("detail")
    public Response getDetailConfigSchedule(@QueryParam("customer_id") Integer customer_id,
                                            @QueryParam("phone_number") String phone_number,
                                            @QueryParam("config_schedule_id") Integer config_schedule_id) {
        GetDetailConfigScheduleCmd cmd = new GetDetailConfigScheduleCmd(httpServletRequest, customer_id, phone_number, config_schedule_id);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("delete")
    public Response deleteConfigSchedule(String body) {
        DeleteConfigScheduleCmd cmd = new DeleteConfigScheduleCmd(httpServletRequest, body, DeleteConfigScheduleRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }



}
