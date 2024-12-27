package com.deukyunlee.indexer.model.account.request;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 27.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTokenV1Request {
    @NotBlank(message = "Account is mandatory")
    @JsonProperty(required = true)
    private String account;

    @NotBlank(message = "Token address is mandatory")
    @JsonProperty(required = true)
    private String tokenAddress;

    @NotNull(message = "Date is mandatory")
    @JsonProperty(required = true)
    private Instant date;
}