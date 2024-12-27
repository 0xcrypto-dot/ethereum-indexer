package com.deukyunlee.indexer.util;

import com.deukyunlee.indexer.exception.CustomErrorType;
import com.deukyunlee.indexer.exception.ErrorTypeException;
import com.deukyunlee.indexer.model.type.evm.EvmChainType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 22.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUtil {
    private static final String EVM_ADDRESS_REGEX = "^0x[a-fA-F0-9]{40}$";
    private static final String ZERO_X = "0x";
    public static final int ERC_20_TRANSFER_ADDRESS_TOPIC_BEGIN_INDEX = 24;

    /**
     * Validates whether the given address is a valid EVM address.
     *
     * @param address the EVM address to validate
     * @throws ErrorTypeException if the address does not match the EVM address regex
     */
    public static void validateEvmAddress(String address) {
        if (!address.matches(EVM_ADDRESS_REGEX)) {
            throw new ErrorTypeException(String.format("EVM_ADDRESS_WITH_REGEX_NOT_VALID: [%s]", address), CustomErrorType.EVM_ADDRESS_WITH_REGEX_NOT_VALID);
        }
    }

    /**
     * Validates whether the given token address is valid for a specific EVM chain type.
     * The token address must match the EVM address regex unless it matches the native token symbol of the chain.
     *
     * @param evmChainType the type of the EVM chain
     * @param tokenAddress the token address to validate
     * @throws ErrorTypeException if the token address is invalid or does not match the EVM address regex
     */
    public static void validateEvmTokenAddress(EvmChainType evmChainType, String tokenAddress) {
        if (!evmChainType.getNativeTokenSymbol().equals(tokenAddress) && !tokenAddress.matches(EVM_ADDRESS_REGEX)) {
            throw new ErrorTypeException(String.format("EVM_TOKEN_ADDRESS_WITH_REGEX_INVALID: [%s]", tokenAddress), CustomErrorType.EVM_TOKEN_ADDRESS_WITH_REGEX_INVALID);
        }
    }

    /**
     * Removes zero padding from an Ethereum address or topic value,
     * but ensures the length remains correct for valid Ethereum addresses.
     *
     * @param paddedValue the padded topic value (e.g., "0x0000000000000000000000001a63b9aed91e03d77944d6f16c81346543bbbded")
     * @return the unpadded address or value (e.g., "0x1a63b9aed91e03d77944d6f16c81346543bbbded")
     */
    public static String removeZeroPadding(String paddedValue, int beginIndex) {
        if (paddedValue == null || !paddedValue.startsWith(ZERO_X)) {
            log.error("Invalid padded value: {}", paddedValue);
            throw new ErrorTypeException("WRONG_TOPIC_VALUE", CustomErrorType.WRONG_TOPIC_VALUE);
        }

        String withoutPrefix = paddedValue.substring(2);

        return ZERO_X + withoutPrefix.substring(beginIndex);
    }
}
