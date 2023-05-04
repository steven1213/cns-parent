package com.steven.cns.auth.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.OAuthRefreshToken;
import org.apache.ibatis.annotations.Param;

public interface OAuthRefreshTokenMapper extends BaseMapper<OAuthRefreshToken> {

    OAuthRefreshToken findByTokenId(@Param("tokenId") String tokenId);

    void deleteByTokenId(@Param("tokenId") String tokenId);

}
