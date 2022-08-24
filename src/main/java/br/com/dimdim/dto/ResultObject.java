package br.com.dimdim.dto;

public record ResultObject(
        Object result,
        boolean hasError,
        String msg
) {
}
