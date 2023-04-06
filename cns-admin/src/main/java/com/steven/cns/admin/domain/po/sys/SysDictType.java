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
public class SysDictType extends PO<SysDictType> implements Serializable {

    private String dictName;

    private String dictType;

    private Short status;

    private String remark;
}
