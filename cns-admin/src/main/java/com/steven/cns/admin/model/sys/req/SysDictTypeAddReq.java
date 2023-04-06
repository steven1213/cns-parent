package com.steven.cns.admin.model.sys.req;

import com.steven.cns.infra.common.type.YesNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author steven.cao
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictTypeAddReq implements Serializable {

    @NotBlank(message = "类型名称不能为空")
    @Length(min = 4, max = 20, message = "类型名称为4-20个字符")
    private String dictTypeName;

    private String dictType;

    @Builder.Default
    private Short status = YesNoEnum.YES.getCode().shortValue();

    @Length(max = 20, message = "类型名称为4-20个字符")
    private String remark;
}
