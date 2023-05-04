package com.steven.cns.auth.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author steven.cao
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户查询用户
     *
     * @param username 用户名
     * @return User
     */
    User findByUsername(@Param("username") String username);
}
