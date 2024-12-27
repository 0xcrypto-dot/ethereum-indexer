package com.deukyunlee.indexer.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 22.
 */
public enum CustomErrorType implements ErrorType {
    // DATE 3000~3099
    DATE_IS_AFTER_TODAY(3000, HttpStatus.BAD_REQUEST),

    // ADDRESS 3100~3199
    EVM_ADDRESS_WITH_REGEX_NOT_VALID(3100, HttpStatus.BAD_REQUEST),
    EVM_TOKEN_ADDRESS_WITH_REGEX_INVALID(3101, HttpStatus.BAD_REQUEST),

    // RPC 3200~3299
    WRONG_RPC_METHOD_TYPE(3200, HttpStatus.INTERNAL_SERVER_ERROR),
    RPC_CALL_FAILED(3201, HttpStatus.INTERNAL_SERVER_ERROR),

    // NUMBER 3300~3399
    CONVERT_HEX_TO_BIG_DECIMAL_ERROR(3300, HttpStatus.INTERNAL_SERVER_ERROR),
    NULL_HEX_STRING_NOT_ALLOWED(3301, HttpStatus.INTERNAL_SERVER_ERROR),
    NULL_BALANCE_NOT_ALLOWED(3302, HttpStatus.INTERNAL_SERVER_ERROR),
    BLANK_HEX_STRING_NOT_ALLOWED(3303, HttpStatus.INTERNAL_SERVER_ERROR),

    // TOKEN 3400~3499
    TOKEN_NOT_EXISTS_ON_CHAIN(3400, HttpStatus.BAD_REQUEST),

    // BLOCK 3500~3599
    DAILY_LAST_BLOCK_NOT_FOUND(3500, HttpStatus.BAD_REQUEST),
    FIRST_BLOCK_NOT_FOUND(3501, HttpStatus.INTERNAL_SERVER_ERROR),
    LAST_BLOCK_NOT_FOUND(3502, HttpStatus.INTERNAL_SERVER_ERROR),
    BLOCK_NOT_FOUND_FOR_DATE(3503, HttpStatus.INTERNAL_SERVER_ERROR),

    // TRANSACTION 3600~3699

    // LOG 3700~3799
    WRONG_TOPIC_VALUE(3700, HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final int code;
    private final HttpStatus httpStatus;


    CustomErrorType(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
