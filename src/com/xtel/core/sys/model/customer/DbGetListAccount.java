package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.response.customer.CustomerResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListAccount extends CallableStatementCmd {
    private List<CustomerResponse> customerResponseList;

    public DbGetListAccount(String transid, String channel) {
        super(transid, channel);
    }

    @Override
    protected void getResult() throws Exception {
        customerResponseList = getList(3, CustomerResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CUSTOMER.get_data", 3);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        register(OracleTypes.CURSOR);
    }

    public List<CustomerResponse> getCustomerResponseList() {
        return customerResponseList;
    }
}
