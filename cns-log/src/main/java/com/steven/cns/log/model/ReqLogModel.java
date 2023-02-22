package com.steven.cns.log.model;

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
public class ReqLogModel implements Serializable {

    private String requestError;

    private String method;

    private String requestParams;

    private String requestResult;

    private String requestUrl;
}
