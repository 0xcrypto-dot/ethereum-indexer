package com.deukyunlee.indexer.spec;

import com.deukyunlee.indexer.model.type.evm.EvmChainType;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 25.
 */
public interface RpcSpec {
    <T> T callPost(EvmChainType evmChainType, String jsonInput, int maxRetryCount, Class<T> classes);
}
