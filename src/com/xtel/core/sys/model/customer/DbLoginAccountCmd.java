package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.request.customer.LoginAccountRequest;
import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.dto.response.customer.CustomerResponse;
import com.xtel.core.dto.response.customer.DataAccount;
import com.xtel.core.dto.response.play_list.PlayListResponse;
import com.xtel.core.dto.response.schedule.ScheduleResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbLoginAccountCmd extends CallableStatementCmd {
    private String full_name;
    private String password;

    private DataAccount data;
    private CustomerResponse customerResponse;
    private AlbumResponse albumResponse;
    private ScheduleResponse scheduleResponse;
    private List<PlayListResponse> playListResponse;
    public DbLoginAccountCmd(String transid, String channel, String full_name, String password) {
        super(transid, channel);
        this.full_name = full_name;
        this.password = password;
    }

    @Override
    protected void getResult() throws Exception {
        data = new DataAccount();

        customerResponse = getSingle(5, CustomerResponse.class);
        data.setCustomerResponse(customerResponse);

        albumResponse = getSingle(6, AlbumResponse.class);
        data.setAlbumResponse(albumResponse);

        scheduleResponse = getSingle(7, ScheduleResponse.class);
        data.setScheduleResponse(scheduleResponse);

        if (cst.getMoreResults()){
            playListResponse = getList(8, PlayListResponse.class);
            data.setPlayListResponseList(playListResponse);
        }
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CUSTOMER.customer_login", 8);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(full_name);
        setString(password);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
    }

    public DataAccount getData() {
        return data;
    }
}
