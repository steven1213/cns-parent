package com.steven.cns.admin.domain.po.sys;

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
public class SysRole extends PO<SysRole> implements Serializable {

    /**
     * roleCode.
     */
    private String roleCode;

    /**
     * roleName.
     */
    private String roleName;

    /**
     * parentId;
     */
    private Long parentId;

    /**
     * parentPath;
     */
    private String parentPath;
}
