-- Dados fictícios exclusivos da POC em H2.
INSERT INTO contract_debt_balance (
    contract_number, nominal_interest, debt_balance, economic_index,
    amortization_type, previous_interest, installment_amount, due_date
) VALUES (
    1001, 8.750, 150000.00, 1.025, 'SAC', 8.500, 2450.30, DATE '2026-09-15'
);
