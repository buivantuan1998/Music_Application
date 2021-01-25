package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.response.customer.ConfigScheduleResponse;
import com.xtel.core.dto.response.customer.CustomerResponse;
import com.xtel.core.dto.response.customer.GetDetailCustomer;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetDetailCustomerCmd extends CallableStatementCmd {
    private Integer customer_id;
    private GetDetailCustomer data;

    public DbGetDetailCustomerCmd(String transid, String channel, Integer customer_id) {
        super(transid, channel);
        this.customer_id = customer_id;
    }

    @Override
    protected void getResult() throws Exception {
        data = new GetDetailCustomer();

        CustomerResponse customerResponse = getSingle(4, CustomerResponse.class);
        data.setCustomerResponse(customerResponse);

        List<ConfigScheduleResponse> configScheduleResponseList = getList(5, ConfigScheduleResponse.class);
        data.setConfigScheduleResponseList(configScheduleResponseList);

    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CUSTOMER.get_detail_data", 5);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(customer_id);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
    }

    public GetDetailCustomer getData() {
        return data;
    }
}
