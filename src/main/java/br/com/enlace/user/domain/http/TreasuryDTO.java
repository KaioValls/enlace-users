package br.com.enlace.user.domain.http;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TreasuryDTO {

    private BigDecimal currentBalance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private LocalDateTime lastUpdated;
    private List<TransactionDTO> recentTransactions;
    private BigDecimal monthlyGoal;

    private LocalDateTime createdAt;


}
