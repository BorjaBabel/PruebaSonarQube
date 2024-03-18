package es.babel.bankExercise.service;

import es.babel.bankExercise.model.BankAccount;

public interface IOperationService {
    void createDepositOperation(String accountNumber, double amount);
    void createWithdrawalOperation(String accountNumber, double amount);
    void createTransferOperation(String accountNumber, String destinationAccountNumber, double amount);

    BankAccount createDepositOperationObjectBank(BankAccount accountNumber, double amount);

}
