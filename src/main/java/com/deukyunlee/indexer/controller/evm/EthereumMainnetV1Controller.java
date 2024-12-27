package com.deukyunlee.indexer.controller.evm;

import com.deukyunlee.indexer.facade.evm.EvmAccountTokenV1FacadeService;
import com.deukyunlee.indexer.model.account.response.AccountTokenV1Response;
import com.deukyunlee.indexer.model.type.evm.EvmChainType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by dufqkd1004@naver.com on 2024. 12. 22.
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Ethereum Mainnet V1", description = "이더리움 메인넷관련 API V1")
@RequestMapping("/v1/ethereum/mainnet")
@Validated
public class EthereumMainnetV1Controller {
    private final EvmAccountTokenV1FacadeService evmAccountTokenV1FacadeService;

    @GetMapping("/accounts/{account}/tokens/{token_address}/transactions/{date}")
    public AccountTokenV1Response getAccountTokenData(
            @PathVariable(value = "account") @Pattern(regexp = "^0x[a-fA-F0-9]{40}$", message = "Invalid Ethereum account address") String account,
            @PathVariable(value = "token_address") @Pattern(regexp = "^0x[a-fA-F0-9]{40}$|^ETH$", message = "Invalid token address. Must be ETH or a valid address") String tokenAddress,
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyyMMdd") @PastOrPresent(message = "Date must be in the past or present") LocalDate date
    ) {
        return evmAccountTokenV1FacadeService.getAccountTokenData(EvmChainType.ETHEREUM_MAINNET, account, tokenAddress, date);
    }
}
