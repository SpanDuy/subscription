package org.webrise.subscription.dto;

public record ResponseDto<T>(
        T data,
        String message,
        ResponseStatus status
) {
    // Static factory method for creating successful response (with data only)
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(data, null, ResponseStatus.SUCCESS);
    }

    // Static method for creating successful response with message
    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<>(data, message, ResponseStatus.SUCCESS);
    }

    // Static method for creating error response
    public static <T> ResponseDto<T> error(String message) {
        return new ResponseDto<>(null, message, ResponseStatus.ERROR);
    }

    // Static method for creating warning response
    public static <T> ResponseDto<T> warning(T data, String message) {
        return new ResponseDto<>(data, message, ResponseStatus.WARNING);
    }
    
    // Compact constructor for setting default value
    // if status is null
    public ResponseDto {
        if (status == null) {
            status = ResponseStatus.SUCCESS;
        }
    }
}