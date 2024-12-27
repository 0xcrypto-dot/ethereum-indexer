package com.deukyunlee.indexer.model.rpc.evm.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by dufqkd1004@naver.com on 2024. 12. 26.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvmBlockReceiptsRpcDetailLogResult {
    @JsonProperty(value = "address")
    private String contractAddress;

    @JsonProperty(value = "topics")
    private List<String> topics;

    @JsonProperty(value = "data")
    private String data;

    @JsonProperty(value = "blockNumber")
    private String blockNumber;

    @JsonProperty(value = "transactionHash")
    private String transactionHash;

    @JsonProperty(value = "transactionIndex")
    private String transactionIndex;

    @JsonProperty(value = "blockHash")
    private String blockHash;

    @JsonProperty(value = "logIndex")
    private String logIndex;

    @JsonProperty(value = "removed")
    private boolean removed;
}
