package com.steven.cns.infra.common.annotation.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author steven.cao
 */
@Slf4j
public class ApiConverter {


    private static final String VERSION_SEPARATOR = ".";

    private static final Integer VERSION_SPLIT_LENGTH = 3;

    public static ApiItem convert(String apiVersion) {
        ApiItem apiItem = new ApiItem();
        if (StringUtils.isBlank(apiVersion)) {
            return apiItem;
        }
        String[] cells = apiVersion.split(VERSION_SEPARATOR);
        if (cells.length != VERSION_SPLIT_LENGTH) {
            log.warn("API版本格式不对:{},正确格式应该为:{}", apiVersion, ApiVersionConstant.DEFAULT_VERSION);
            return apiItem;
        }
        apiItem.setHigh(Integer.parseInt(cells[0]));
        apiItem.setMid(Integer.parseInt(cells[1]));
        apiItem.setLow(Integer.parseInt(cells[2]));
        return apiItem;
    }
}
