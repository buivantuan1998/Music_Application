package com.bkav.sdl.core.sys.model.user;

import com.bkav.sdl.core.sys.entities.CustomerAccount;
import com.bkav.sdl.core.sys.model.PreparedStatementCommand;
import com.nvt.xpersistence.builder.MySqlQueryBuilder;
import com.nvt.xpersistence.builder.Specification;

import java.util.List;

public class DbUserGetListCmd extends PreparedStatementCommand {
    private Integer id;
    private CustomerAccount customerAccount;

    public DbUserGetListCmd(String transid, String channel, Integer id) {
        super(transid, channel);
        this.id = id;
    }

    @Override
    protected void execute() throws Exception {
        MySqlQueryBuilder<CustomerAccount> user = new MySqlQueryBuilder<>(conn, CustomerAccount.class);
        customerAccount = user.findFirst(new Specification().equal("id", id));
        code = 0;
        message = "SUCCESS";
    }

    public CustomerAccount getUserList() {
        return customerAccount;
    }
}
