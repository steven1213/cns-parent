package com.steven.im.common.message;

import com.steven.im.common.type.CnsIMMsgType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author steven.cao
 */
@Getter
@Setter
@SuperBuilder
public abstract class CnsMsg {

    private CnsIMMsgType msgType;
}
