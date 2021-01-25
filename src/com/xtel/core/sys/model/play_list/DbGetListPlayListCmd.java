package com.xtel.core.sys.model.play_list;

import com.xtel.core.dto.response.play_list.PlayListData;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListPlayListCmd extends CallableStatementCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;
    private String phone_number;

    private List<PlayListData> data;

    public DbGetListPlayListCmd(String transid, String channel, Integer page_index, Integer page_size,
                                String search_name, String order_by, String phone_number) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
        this.phone_number = phone_number;
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(12, PlayListData.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_PLAY_LIST.get_list_play_list", 12);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(page_index);
        setInt(page_size);
        setString(search_name);
        setString(order_by);
        setString(phone_number);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(OracleTypes.CURSOR);
    }

    public List<PlayListData> getData() {
        return data;
    }
}
