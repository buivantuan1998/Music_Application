package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.request.customer.UpdateAccountRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbUpdateCustommerCmd extends MultiCallableStatementCmd {
    private UpdateAccountRequest request;

    public DbUpdateCustommerCmd(String transid, String channel, UpdateAccountRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CUSTOMER.update_data", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getCustomer_id());
                setString(cst, i++, request.getCustomer_name());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
