package com.steven.im.common.codec;

import com.steven.im.common.type.CnsIMMsgType;
import lombok.Data;

/**
 * @author dr.panda
 */
@Data
public class Invocation {

    private CnsIMMsgType msgType;

    private String msgContent;
}
