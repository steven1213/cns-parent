package com.steven.cns.auth.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.cns.auth.common.entity.ClientDetails;
import org.apache.ibatis.annotations.Param;

/**
 * @author steven.cao
 */
public interface ClientDetailsMapper extends BaseMapper<ClientDetails> {

    ClientDetails findByClientId(@Param("clientId") String clientId);

}
