package com.bkav.sdl.core.sys.model.notification;

import com.bkav.sdl.core.sys.entities.Notification;
import com.bkav.sdl.core.sys.model.PreparedStatementCommand;
import com.nvt.xpersistence.builder.MySqlQueryBuilder;
import com.nvt.xpersistence.builder.Pageable;
import com.nvt.xpersistence.builder.PagingData;
import com.nvt.xpersistence.builder.Specification;

import java.util.ArrayList;
import java.util.List;

public class DbNotifyGetListCmd extends PreparedStatementCommand {

    private int INDEX = 1;
    private int PAGE_SIZE = 3;
    private Integer user_id;

    private PagingData<List<Notification>> notifyList;
//    private  List<Notification> notifyList;
    public DbNotifyGetListCmd(String transid, String channel, Integer user_id) {
        super(transid, channel);
        this.user_id = user_id;
    }

    @Override
    protected void execute() throws Exception {
        MySqlQueryBuilder<Notification> notify = new MySqlQueryBuilder<>(conn, Notification.class);
        List<String> sort = new ArrayList<>();
        sort.add("at desc");
        Pageable pageable = new Pageable(INDEX, PAGE_SIZE);
        pageable.setSorter(sort);
        notifyList = notify.findAll(new Specification().equal("user_id", user_id), pageable);
        code = 0;
        message = "SUCCESS";
    }

    public PagingData<List<Notification>> getNotifyList() {
        return notifyList;
    }

}
