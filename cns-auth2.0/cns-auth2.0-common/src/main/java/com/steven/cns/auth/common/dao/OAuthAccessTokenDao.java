package com.steven.cns.auth.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.OAuthAccessToken;

public interface OAuthAccessTokenDao extends BaseMapper<OAuthAccessToken> {
    void deleteByRefreshTokenId(String refreshTokenId);
}
