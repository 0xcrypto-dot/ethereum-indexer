package com.deukyunlee.indexer.service.token.evm;

import com.deukyunlee.indexer.model.type.evm.EvmChainType;
import com.deukyunlee.indexer.service.token.evm.strategy.EvmTokenV1Strategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 23.
 */
@Service
public class EvmTokenV1Service {
    public void checkContractDeployed(EvmTokenV1Strategy strategy, EvmChainType evmChainType, String tokenAddress, long blockNumber) {
        strategy.checkContractDeployed(evmChainType, tokenAddress, blockNumber);
    }

    public BigDecimal getBalance(EvmTokenV1Strategy strategy, EvmChainType evmChainType, String address, String tokenAddress, long blockNumber) {
        return strategy.getBalance(evmChainType, address, tokenAddress, blockNumber);
    }

    public long getDecimals(EvmTokenV1Strategy strategy, EvmChainType evmChainType, String tokenAddress, long blockNumber) {
        return strategy.getDecimals(evmChainType, tokenAddress, blockNumber);
    }
}
