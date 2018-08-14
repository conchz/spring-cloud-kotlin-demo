package com.github.lavenderx.demo.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;
    @JsonProperty("msg")
    private String message;
    @JsonProperty("req_id")
    private Long requestId;
    @JsonProperty("data")
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse ok() {
        return new BaseResponse(1, "ok");
    }
}
