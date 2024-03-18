package es.babel.bankExercise.service;

import es.babel.bankExercise.model.BankAccount;

import java.util.List;

public interface IBankAccountService {
    void BankInitializer();

    List<BankAccount> getBankAccountsByClient(int clientCode);

    boolean createBankAccount(int clientCode);

}
