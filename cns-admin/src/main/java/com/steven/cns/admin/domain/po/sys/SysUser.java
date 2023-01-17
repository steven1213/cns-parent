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
public class SysUser extends PO<SysUser> implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态
     *
     * @see com.steven.cns.admin.infra.type.UserStatusEnum
     */
    private Short status;
}
