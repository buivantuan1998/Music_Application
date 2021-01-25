package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.response.customer.CustomerResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListCustomerCmd extends CallableStatementCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;
    private List<CustomerResponse> data;
    public DbGetListCustomerCmd(String transid, String channel, Integer page_index, Integer page_size, String search_name, String order_by) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(11, CustomerResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CUSTOMER.get_list_data", 11);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(page_index);
        setInt(page_size);
        setString(search_name);
        setString(order_by);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(OracleTypes.CURSOR);
    }

    public List<CustomerResponse> getData() {
        return data;
    }
}
