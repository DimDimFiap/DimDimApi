package br.com.dimdim.dto;

import br.com.dimdim.entity.Transaction;
import org.springframework.data.domain.Page;

public record ResultObject (
        Object result,
        boolean hasError,
        String msg
) {
}
