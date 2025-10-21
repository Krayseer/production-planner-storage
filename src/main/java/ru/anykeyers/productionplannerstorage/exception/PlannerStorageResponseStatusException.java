package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

/**
 * Базовая ошибка приложения<p/>
 * Можно передавать как сообщение, так и шаблон с параметрами (используется {@link MessageFormat})
 */
public class PlannerStorageResponseStatusException extends ResponseStatusException {

    public PlannerStorageResponseStatusException(HttpStatus status, String message, Object... params) {
        super(status, params == null ? message : MessageFormat.format(message, params));
    }

}
