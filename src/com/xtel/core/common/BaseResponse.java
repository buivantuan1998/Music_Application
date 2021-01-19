package com.xtel.core.common;

import com.xtel.core.config.service.CoreConfig;
import com.tbv.utils.datetime.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String request_id;
    private String at;
    private Error error;
    private Object data;

    public BaseResponse(String request_id, Date at, Error error, Object data) {
        this.request_id = request_id;
        this.error = error;
        this.data = data;
        this.at = DateTimeUtil.formatDate(at, CoreConfig.DATE_TIME_FORMAT);
    }

    public BaseResponse(String request_id, Date at, Error error) {
        this.request_id = request_id;
        this.error = error;
        this.at = DateTimeUtil.formatDate(at, CoreConfig.DATE_TIME_FORMAT);
    }

    public BaseResponse(String request_id, Date at, int code, String message){
        this.request_id = request_id;
        this.at = DateTimeUtil.formatDate(at, CoreConfig.DATE_TIME_FORMAT);
        this.error = new Error(code, message);
    }

    public BaseResponse(String request_id, Date at, int code, String message, Object data){
        this.request_id = request_id;
        this.at = DateTimeUtil.formatDate(at, CoreConfig.DATE_TIME_FORMAT);
        this.error = new Error(code, message);
        this.data = data;
    }
}
