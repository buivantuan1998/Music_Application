package com.xtel.core.sys.controller;

import com.xtel.core.dto.request.customer.DeleteCustomerRequest;
import com.xtel.core.dto.request.customer.LoginAccountRequest;
import com.xtel.core.dto.request.customer.RegisterAccountRequest;
import com.xtel.core.dto.request.customer.UpdateAccountRequest;
import com.xtel.core.sys.service.customer.*;

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

    @GET
    @Path("list")
    public Response getListAccount(@QueryParam("page_index") Integer page_index,
                                   @QueryParam("page_size") Integer page_size,
                                   @QueryParam("search_name") String search_name,
                                   @QueryParam("order_by") String order_by) {
        GetListCustomerCmd cmd = new GetListCustomerCmd(httpServletRequest, page_index, page_size, search_name, order_by);
        cmd.execute();
        return cmd.getResponse();
    }

    @GET
    @Path("delete")
    public Response deleteAccount(String body) {
        DeleteCustomerCmd cmd = new DeleteCustomerCmd(httpServletRequest, body, DeleteCustomerRequest.class);
        cmd.execute();
        return cmd.getResponse();
    }
}
