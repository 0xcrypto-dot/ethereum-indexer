package com.deukyunlee.indexer.facade.evm;

import com.deukyunlee.indexer.model.account.dto.AccountTokenTransferDto;
import com.deukyunlee.indexer.model.account.request.AccountTokenV1Request;
import com.deukyunlee.indexer.model.account.response.AccountTokenV1Response;
import com.deukyunlee.indexer.model.type.evm.EvmChainType;
import com.deukyunlee.indexer.model.type.evm.EvmTokenType;
import com.deukyunlee.indexer.service.chain.evm.EvmChainV1Service;
import com.deukyunlee.indexer.service.chain.evm.factory.EvmChainV1StrategyFactory;
import com.deukyunlee.indexer.service.chain.evm.strategy.EvmChainV1Strategy;
import com.deukyunlee.indexer.service.token.evm.EvmTokenV1Service;
import com.deukyunlee.indexer.service.token.evm.factory.EvmTokenV1StrategyFactory;
import com.deukyunlee.indexer.service.token.evm.strategy.EvmTokenV1Strategy;
import com.deukyunlee.indexer.util.AddressUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 22.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EvmAccountTokenV1FacadeService {
    private final EvmTokenV1StrategyFactory evmTokenV1StrategyFactory;
    private final EvmTokenV1Service evmTokenV1Service;
    private final EvmChainV1StrategyFactory evmChainV1StrategyFactory;
    private final EvmChainV1Service evmChainV1Service;

    // TODO: Implement Redis caching with the following key structure: [evmChainType, account, tokenAddress, date].
//       - Cache expiry: 1 hour
//       - Purpose: Avoid redundant calls to the service and improve response time for identical requests.
    public AccountTokenV1Response getAccountTokenData(EvmChainType evmChainType, AccountTokenV1Request request) {
        // Validate inputs
        String account = request.getAccount();
        String tokenAddress = request.getTokenAddress();
        Instant date = request.getDate();

        AddressUtil.validateEvmAddress(account);
        AddressUtil.validateEvmTokenAddress(evmChainType, tokenAddress);

        EvmChainV1Strategy evmChainV1Strategy = evmChainV1StrategyFactory.getStrategy(evmChainType);

        long blockNumber = evmChainV1Service.getDailyLastBlockNumber(evmChainV1Strategy, date);

        EvmTokenType evmTokenType = EvmTokenType.getTokenAddress(evmChainType, tokenAddress);

        // Create a strategy object using the factory pattern based on the token type (e.g., ERC20, NATIVE_TOKEN)
        EvmTokenV1Strategy evmTokenV1Strategy = evmTokenV1StrategyFactory.getStrategy(evmTokenType);

        evmTokenV1Service.checkContractDeployed(evmTokenV1Strategy, evmChainType, tokenAddress, blockNumber);

        BigDecimal balance = evmTokenV1Service.getBalance(evmTokenV1Strategy, evmChainType, account, tokenAddress, blockNumber);

        long decimal = evmTokenV1Service.getDecimals(evmTokenV1Strategy, evmChainType, tokenAddress, blockNumber);

        List<AccountTokenTransferDto> accountTokenTransfers = EvmTokenType.NATIVE == evmTokenType ?
                evmChainV1Service.getTransactions(evmChainV1Strategy, date, account) :
                evmChainV1Service.getErc20Transfers(evmChainV1Strategy, date, account, tokenAddress);

        return new

                AccountTokenV1Response(decimal, balance, accountTokenTransfers);
    }
}