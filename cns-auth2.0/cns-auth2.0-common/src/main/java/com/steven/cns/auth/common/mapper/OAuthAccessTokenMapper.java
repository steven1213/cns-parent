package com.steven.cns.auth.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.OAuthAccessToken;
import org.apache.ibatis.annotations.Param;

/**
 * @author steven.cao
 */
public interface OAuthAccessTokenMapper extends BaseMapper<OAuthAccessToken> {

    OAuthAccessToken findByTokenId(@Param("tokenId") String tokenId);

    void deleteByTokenId(@Param("tokenId") String tokenId);

    OAuthAccessToken findByRefreshTokenId(@Param("refreshTokenId") String refreshTokenId);

    void deleteByRefreshTokenId(@Param("refreshTokenId") String refreshTokenId);

}
