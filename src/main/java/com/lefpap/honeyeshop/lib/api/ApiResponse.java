package com.lefpap.honeyeshop.lib.api;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;
    private final ApiMetadata metadata;

    private ApiResponse(boolean success, T data, String message, ApiMetadata metadata) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.metadata = metadata;
    }

    public static <T> ApiResponse<T> success(T data, String message, ApiMetadata metadata) {
        return new ApiResponse<T>(true, data, message, metadata);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return success(data, message, null);
    }

    public static <T> ApiResponse<T> success(T data, ApiMetadata metadata) {
        return success(data, null, metadata);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(data, null, null);
    }

    public static <T> ApiResponse<T> error(String message, ApiMetadata metadata) {
        return new ApiResponse<T>(false, null, message, metadata);
    }

    public static <T> ApiResponse<T> error(String message) {
        return error(message, null);
    }
}
