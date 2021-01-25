package com.xtel.core.sys.model.customer;

import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.dto.response.customer.ConfigScheduleResponse;
import com.xtel.core.dto.response.customer.CustomerResponse;
import com.xtel.core.dto.response.customer.DataAccount;
import com.xtel.core.dto.response.play_list.PlayListResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbLoginAccountCmd extends CallableStatementCmd {
    private String phone_number;
    private String password;

    private DataAccount data;

    public DbLoginAccountCmd(String transid, String channel, String phone_number, String password) {
        super(transid, channel);
        this.phone_number = phone_number;
        this.password = password;
    }

    @Override
    protected void getResult() throws Exception {
        data = new DataAccount();

        CustomerResponse customerResponse = getSingle(5, CustomerResponse.class);
        data.setCustomerResponse(customerResponse);

        AlbumResponse albumResponse = getSingle(6, AlbumResponse.class);
        data.setAlbumResponse(albumResponse);

        List<ConfigScheduleResponse> configScheduleResponses = getList(7, ConfigScheduleResponse.class);
        data.setScheduleResponse(configScheduleResponses);

        List<PlayListResponse> playListResponses = getList(8, PlayListResponse.class);
        data.setPlayListResponseList(playListResponses);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CUSTOMER.customer_login", 8);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(phone_number);
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
