package com.steven.cns.example.test.controller;

import com.steven.cns.infra.common.resp.Resp;
import com.steven.cns.infra.common.type.GenderEnum;
import com.steven.cns.log.annotation.OperationLog;
import com.steven.cns.log.annotation.ReqLog;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: steven
 * @date: 2023/2/22 23:18
 */
@Slf4j
@RestController
@ComponentScan(basePackages = {"com.steven"})
@RequestMapping("/test")
public class TestController {

    @GetMapping("/say")
    @ReqLog(printHeader = true)
    @OperationLog(appName = "test-app", module = "测试模块", description = "say方法")
    public Resp<Void> say(@Validated @RequestBody TestDo testDo) {
        log.info("testDo: {}", testDo);
        return Resp.success();
    }

    @Data
    public class TestDo {
        private String name;

        private GenderEnum gender;

        private Integer age;
    }
}
