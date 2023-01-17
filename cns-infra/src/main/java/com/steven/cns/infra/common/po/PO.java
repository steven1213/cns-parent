package com.steven.cns.infra.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author steven.cao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PO<T> implements Serializable {

    /**
     * id.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT", locale = "zh_CN")
    private Date crtTime;


    /**
     * 记录修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT", locale = "zh_CN")
    private Date uptTime;


    /**
     * 删除标识 0-未删除 1-删除
     */
    private Short del;


}
