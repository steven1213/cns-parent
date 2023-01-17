package com.steven.cns.log.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dr.panda
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationLogModel implements Serializable {

    private Long id;

    private String appName;

    private String module;

    private String description;

    private String requestError;

    private String method;

    private String simpleResult;

    private String requestUrl;

    private Long crtTime;

    private Long uptTime;
}
