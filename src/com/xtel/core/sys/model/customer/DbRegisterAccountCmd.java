package com.xtel.core.sys.model.customer;

import com.tbv.utils.textbase.StringUtils;
import com.xtel.core.dto.request.customer.RegisterAccountRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbRegisterAccountCmd extends MultiCallableStatementCmd {
    private RegisterAccountRequest request;
    private Integer customer_id;
    public DbRegisterAccountCmd(String transid, String channel, RegisterAccountRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CUSTOMER.customer_register", 8, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getCustomer_name());
                setString(cst, i++, request.getFull_name());
                setString(cst, i++, request.getEmail());
                setString(cst, i++, StringUtils.encodeMD5(request.getPassword()));
                setString(cst, i++, request.getPhone_number());
                register(cst, i++, Types.INTEGER);
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
                customer_id = cst.getInt(8);
            }
        });
    }

    public Integer getCustomer_id() {
        return customer_id;
    }
}
