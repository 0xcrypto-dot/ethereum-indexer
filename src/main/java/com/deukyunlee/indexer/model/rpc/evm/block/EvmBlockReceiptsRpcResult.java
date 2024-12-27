package com.deukyunlee.indexer.model.rpc.evm.block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 26.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvmBlockReceiptsRpcResult {
    private String jsonrpc;
    private String id;
    private List<EvmBlockReceiptsRpcDetailResult> result;
}
