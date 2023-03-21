package com.steven.cns.log;

import com.steven.cns.log.model.OperationLogModel;

/**
 * @author steven.cao
 */
public interface OperationLogHandler {

    /**
     * save operation log
     *
     * @param operationLogModel operationLogModel
     */
    default void saveOperationLog(OperationLogModel operationLogModel) {
        // do nothing
    }
}
