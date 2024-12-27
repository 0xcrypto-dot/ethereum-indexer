package com.deukyunlee.indexer.model.account.response;

import com.deukyunlee.indexer.model.account.dto.AccountTokenTransferDto;
import com.deukyunlee.indexer.util.NumberUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 22.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTokenV1Response {
    @JsonProperty(required = true, value = "balance")
    private BigDecimal balance;

    @JsonProperty(required = true, value = "transfer_history")
    private List<AccountTokenTransferHistoryV1Response> transferHistories;

    public AccountTokenV1Response(long decimal, BigDecimal balance, List<AccountTokenTransferDto> transferHistoryDtos) {
        this.balance = (BigDecimal.ZERO.compareTo(balance) == 0) ?
                balance :
                NumberUtil.getBalanceDividedByDecimal(balance, decimal)
                        .setScale(4, RoundingMode.DOWN);

        this.transferHistories = transferHistoryDtos.stream()
                .map(transferHistoryDto -> new AccountTokenTransferHistoryV1Response(decimal, transferHistoryDto))
                .sorted(Comparator.comparing(AccountTokenTransferHistoryV1Response::getTimestamp))
                .toList();
    }
}
