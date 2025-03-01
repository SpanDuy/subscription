package org.webrise.subscription.dto;

public record ResponseDto<T>(
        T data,
        String message,
        ResponseStatus status
) {
    // Статический фабричный метод для создания успешного ответа (только с данными)
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(data, null, ResponseStatus.SUCCESS);
    }

    // Статический метод для создания успешного ответа с сообщением
    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<>(data, message, ResponseStatus.SUCCESS);
    }

    // Статический метод для создания ответа с ошибкой
    public static <T> ResponseDto<T> error(String message) {
        return new ResponseDto<>(null, message, ResponseStatus.ERROR);
    }

    // Статический метод для создания ответа с предупреждением
    public static <T> ResponseDto<T> warning(T data, String message) {
        return new ResponseDto<>(data, message, ResponseStatus.WARNING);
    }
    
    // Компактный конструктор для установки значения по умолчанию, 
    // если status равен null
    public ResponseDto {
        if (status == null) {
            status = ResponseStatus.SUCCESS;
        }
    }
}