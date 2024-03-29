package com.steven.cns.infra.common.resp;

import com.steven.cns.infra.common.type.RespResult;
import com.steven.cns.infra.common.utils.MdcUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author steven.cao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resp<T> implements Serializable {

    /**
     * 返回code.
     */
    private Integer code;

    /**
     * 返回msg.
     */
    private String msg;

    /**
     * 请求返回时间戳
     */
    private Long timestamp;

    /**
     * traceId
     */
    private String traceId;

    /**
     * 返回数据
     */
    private T data;

    public static <T> Resp<T> failure() {
        return failure(RespResult.FAILURE.getCode(), RespResult.FAILURE.getName());
    }

    public static <T> Resp<T> failure(Integer code, String msg) {
        return generateResp(code, msg, null);
    }

    public static <T> Resp<T> success() {
        return success(null);
    }

    public static <T> Resp<T> success(T data) {
        return success(RespResult.SUCCESS.getCode(), RespResult.SUCCESS.getName(), data);
    }

    public static <T> Resp<T> success(Integer code, String msg) {
        return success(code, msg, null);
    }


    public static <T> Resp<T> success(Integer code, String msg, T data) {
        return generateResp(code, msg, data);
    }

    private static <T> Resp<T> generateResp(Integer code, String msg, T data) {
        return Resp.<T>builder()
                .code(code)
                .msg(msg)
                .timestamp(System.currentTimeMillis())
                .traceId(MdcUtils.getTraceId())
                .data(data)
                .build();
    }
}
