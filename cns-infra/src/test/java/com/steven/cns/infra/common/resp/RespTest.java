package com.steven.cns.infra.common.resp;

import com.google.gson.Gson;
import com.steven.cns.infra.common.type.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class RespTest {

    @Test
    public void failure() {
        Resp<?> resp = Resp.failure();
        log.info("resp:{}", new Gson().toJson(resp));
        Assert.assertEquals(RespResult.FAILURE.getCode(), resp.getCode());
    }

    @Test
    public void testFailure() {
        Resp<?> resp = Resp.failure(RespResult.FAILURE.getCode(), RespResult.FAILURE.getValue());
        Assert.assertEquals(RespResult.FAILURE.getCode(), resp.getCode());
    }

    @Test
    public void success() {
        Resp<?> resp = Resp.success();
        Assert.assertEquals(RespResult.SUCCESS.getCode(), resp.getCode());
    }

    @Test
    public void testSuccess() {
        Resp<?> resp = Resp.success("hello");
        Assert.assertEquals(RespResult.SUCCESS.getCode(), resp.getCode());
    }

    @Test
    public void testSuccess1() {
        Resp<Void> resp = Resp.success(RespResult.SUCCESS.getCode(), RespResult.SUCCESS.getValue());
        Assert.assertEquals(RespResult.SUCCESS.getCode(), resp.getCode());
    }

    @Test
    public void testSuccess2() {
        Resp<String> resp = Resp.success(RespResult.SUCCESS.getCode(), RespResult.SUCCESS.getValue(), "hello");
        Assert.assertEquals(RespResult.SUCCESS.getCode(), resp.getCode());
    }
}