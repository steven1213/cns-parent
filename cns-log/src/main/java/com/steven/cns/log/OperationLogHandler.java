package com.steven.cns.log;

import com.steven.cns.log.model.OperationLogModel;

/**
 * @author dr.panda
 */
public interface OperationLogHandler {

    /**
     * save operation log
     *
     * @param operationLogModel operationLogModel
     */
    default void saveOperationLog(OperationLogModel operationLogModel) {
        // nothing to do
    }
}
