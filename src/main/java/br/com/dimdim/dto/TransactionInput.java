package br.com.dimdim.dto;

public record TransactionInput(
        String title,
        Double value,
        Integer transactionTypeId
) {
}
