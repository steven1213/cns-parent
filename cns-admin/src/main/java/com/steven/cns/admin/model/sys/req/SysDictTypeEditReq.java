package com.steven.cns.admin.model.sys.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author steven.cao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDictTypeEditReq extends SysDictTypeAddReq implements Serializable {

    @NotBlank(message = "字典类型ID不能为空")
    @JsonProperty(value = "字典ID", required = true)
    private Long id;
}
