package com.steven.cns.example.test.handler;

import com.steven.cns.infra.utils.GsonUtils;
import com.steven.cns.log.OperationLogHandler;
import com.steven.cns.log.model.OperationLogModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: steven
 * @date: 2023/2/24 21:03
 */
@Slf4j
@Component
public class ExampleOperatorLogHandler implements OperationLogHandler {

    @Override
    public void saveOperationLog(OperationLogModel operationLogModel) {
        log.info("调用子类方法写");
        log.info("log:{}", GsonUtils.toJsonAllowNull(operationLogModel));
    }
}
