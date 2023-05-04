package com.steven.cns.auth.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.User;

public interface UserDao extends BaseMapper<User> {

    User findByUsername(String username);
}
