package com.steven.cns.auth.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.OAuthRefreshToken;

public interface OAuthRefreshTokenDao extends BaseMapper<OAuthRefreshToken> {
    void deleteByTokenId(String tokenId);

}
