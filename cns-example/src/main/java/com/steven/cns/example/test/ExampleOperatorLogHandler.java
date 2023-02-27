package com.steven.cns.example.test;

import com.steven.cns.log.OperationLogHandler;
import com.steven.cns.log.model.OperationLogModel;

/**
 * @author: steven
 * @date: 2023/2/24 21:03
 */
public class ExampleOperatorLogHandler extends OperationLogHandler {

    @Override
    public void saveOperationLog(OperationLogModel operationLogModel) {
        System.out.println("调用子类方法写");
    }
}
