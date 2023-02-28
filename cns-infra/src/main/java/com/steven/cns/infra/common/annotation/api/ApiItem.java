package com.steven.cns.infra.common.annotation.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author steven.cao
 */
@Data
@NoArgsConstructor
public class ApiItem implements Comparable<ApiItem> {
    private int high = 1;

    private int mid = 0;

    private int low = 0;

    public static final ApiItem API_ITEM_DEFAULT = ApiConverter.convert(ApiVersionConstant.DEFAULT_VERSION);


    @Override
    public int compareTo(@NotNull ApiItem apiItem) {
        if (this.getHigh() > apiItem.getHigh()) {
            return 1;
        } else if (this.getHigh() < apiItem.getHigh()) {
            return -1;
        }

        if (this.getMid() > apiItem.getMid()) {
            return 1;
        } else if (this.getMid() < apiItem.getMid()) {
            return -1;
        }

        if (this.getLow() > apiItem.getLow()) {
            return 1;
        } else if (this.getLow() < apiItem.getLow()) {
            return -1;
        }
        return 0;
    }
}
