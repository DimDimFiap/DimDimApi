package br.com.dimdim.dto;

public record TransactionResult(
        Integer id,
        String title,
        Double value,
        Integer TransactionTypeId,
        String TransactionTypeName
) {
}
