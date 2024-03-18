package es.babel.bankExercise.service;


import es.babel.bankExercise.model.BankAccount;
import es.babel.bankExercise.model.Client;
import es.babel.bankExercise.repository.BankAccountRepository;
import es.babel.bankExercise.repository.ClienteRepository;
import es.babel.bankExercise.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Service
public class BankAccountService implements IBankAccountService{
    private static final Logger logger = LogManager.getLogger(BankAccountService.class);

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private OperationService operationService;
    public BankAccount createBankAccountWithOperations() {
        // Crear cliente
        List<BankAccount> bankAccountList = new ArrayList<>();
        Client client = new Client();
        client.setName("Manolo");
        client.setBankAccounts(bankAccountList);
        clienteRepository.save(client);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId("1234567890");
        bankAccount.setTotalAmount(0);
        bankAccount.setOwnerId(client.getId());
        bankAccount.setEntity("LaCaixe");

        operationService.createDepositOperationObjectBank(bankAccount,1000);
        operationService.createDepositOperationObjectBank(bankAccount,200);

        clienteRepository.save(client);
        bankAccountRepository.save(bankAccount);
        logger.info("Creada la cuenta con id $" + bankAccount.getId());
        return bankAccount;
    }


    @Override
    public void BankInitializer() {

    }

    @Override
    public List<BankAccount> getBankAccountsByClient(int clientCode) {
        Client client = clienteRepository.findById(clientCode);
        assert client != null;
        return client.getBankAccounts();
    }

    @Override
    public boolean createBankAccount(int clientCode) {
        BankAccount bankAccount = new BankAccount();
        Random rand = new Random();
        // Generar un número aleatorio entre 1 y 100,000
        int numeroAleatorio = rand.nextInt(100000) + 1;
        bankAccount.setId("ES"+numeroAleatorio);
        bankAccount.setTotalAmount(0);
        Client client = clienteRepository.findById(clientCode);
        bankAccount.setOwnerId(client.getId());
        bankAccountRepository.save(bankAccount);
        return true;
    }
}
