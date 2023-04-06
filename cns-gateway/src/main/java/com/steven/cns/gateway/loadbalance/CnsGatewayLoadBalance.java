package com.steven.cns.gateway.loadbalance;

import lombok.Data;

/**
 * @author steven.cao
 */
@Data
public class CnsGatewayLoadBalance {
    // gateway api load balance config
    // implement load balance strategy

    GatewayLoadBalanceStrategy gatewayLoadBalanceStrategy;

    public CnsGatewayLoadBalance(GatewayLoadBalanceStrategy gatewayLoadBalanceStrategy) {
        this.gatewayLoadBalanceStrategy = gatewayLoadBalanceStrategy;
    }

    public void loadBalance() {
        // load balance
        gatewayLoadBalanceStrategy.loadBalance();
    }



}
