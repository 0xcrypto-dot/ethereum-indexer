package com.deukyunlee.indexer.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 22.
 */
public interface ErrorType {
    int getCode();
    HttpStatus getHttpStatus();
}

