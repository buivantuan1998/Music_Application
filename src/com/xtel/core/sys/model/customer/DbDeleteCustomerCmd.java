package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.request.customer.DeleteCustomerRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbDeleteCustomerCmd extends MultiCallableStatementCmd {
    private DeleteCustomerRequest request;

    public DbDeleteCustomerCmd(String transid, String channel, DeleteCustomerRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CUSTOMER.delete_data", 3, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 0;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getCustomer_id());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
