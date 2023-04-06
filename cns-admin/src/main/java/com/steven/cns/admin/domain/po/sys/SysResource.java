package com.steven.cns.admin.domain.po.sys;

import com.steven.cns.admin.infra.type.SysResourceTypeEnum;
import com.steven.cns.infra.common.po.PO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author steven.cao
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SysResource extends PO<SysResource> implements Serializable {

    /**
     * @see SysResourceTypeEnum
     */
    private Short resourceType;

    /**
     * 名称
     */
    private String resourceName;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * parentPath.
     */
    private String parentPath;
}
