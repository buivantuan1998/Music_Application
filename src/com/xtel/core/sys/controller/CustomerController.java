package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.customer.LoginAccountRequest;
import com.xtel.core.dto.request.customer.RegisterAccountRequest;
import com.xtel.core.dto.request.customer.UpdateAccountRequest;
import com.xtel.core.sys.service.customer.GetDetailCustomerCmd;
import com.xtel.core.sys.service.customer.LoginAccountCmd;
import com.xtel.core.sys.service.customer.RegisterAccountCmd;
import com.xtel.core.sys.service.customer.UpdateCustommerCmd;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("customer")
public class CustomerController extends BaseController{
    @POST
    @Path("register")
    public Response registerAccount(String body) {
        RegisterAccountCmd cmd = new RegisterAccountCmd(httpServletRequest, body, RegisterAccountRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("login")
    public Response loginAccount(String body) {
        LoginAccountCmd cmd = new LoginAccountCmd(httpServletRequest, body, LoginAccountRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("detail")
    public Response detailAccount(@QueryParam("customer_id") Integer customer_id) {
        GetDetailCustomerCmd cmd = new GetDetailCustomerCmd(httpServletRequest, customer_id);
        cmd.execute();
        return cmd.getResponse();
    }

    @POST
    @Path("update")
    public Response updateCustomer(String body) {
        UpdateCustommerCmd cmd = new UpdateCustommerCmd(httpServletRequest, body, UpdateAccountRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }


}
