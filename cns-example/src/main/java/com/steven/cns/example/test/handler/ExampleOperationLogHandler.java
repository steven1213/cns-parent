package com.steven.cns.example.test.handler;

import com.steven.cns.log.OperationLogHandler;
import com.steven.cns.log.model.OperationLogModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: steven
 * @date: 2023/2/22 23:27
 */
@Slf4j
public class ExampleOperationLogHandler extends OperationLogHandler {

    @Override
    public void saveOperationLog(OperationLogModel operationLogModel) {
        // nothing to do
        log.info("operation log nothing to do ");
    }
}
