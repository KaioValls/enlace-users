package br.com.enlace.user.domain.http;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    public Long userId;
    private String category;
}
