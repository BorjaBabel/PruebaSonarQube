package es.babel.bankExercise.service;

import es.babel.bankExercise.model.*;
import es.babel.bankExercise.repository.BankAccountRepository;
import es.babel.bankExercise.repository.ClienteRepository;
import es.babel.bankExercise.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OperationService implements IOperationService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Override
    public void createDepositOperation(String accountNumber, double amount) {
        OperationDeposit deposito = new OperationDeposit();
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber);
        List<Operation> operationList = new ArrayList<>();
        if(bankAccount.getOperations() != null){
            operationList = bankAccount.getOperations();
        }
        deposito.setFecha("2024-03-08");
        deposito.setOrigin("Castelldefels");
        deposito.setCantidad(amount);
        deposito.setDescripcion("Depósito por guapo");
        deposito.setInterest(0);
        deposito.setBankAccount(bankAccount.getId());
        deposito.setEntity("N/A");
        double totalAmount = bankAccount.getTotalAmount();
        totalAmount = totalAmount + amount;
        bankAccount.setTotalAmount(totalAmount);
        operationList.add(deposito);
        bankAccount.setOperations(operationList);
        bankAccountRepository.save(bankAccount);
        operationRepository.save(deposito);
    }

    @Override
    public void createWithdrawalOperation(String accountNumber, double amount) {
        OperationWithdrawal withdrawal = new OperationWithdrawal();
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber);
        List<Operation> operationList = new ArrayList<>();
        if(bankAccount.getOperations() != null){
            operationList = bankAccount.getOperations();
        }
        withdrawal.setFecha("2024-03-08");
        withdrawal.setCantidad(amount);
        withdrawal.setDescripcion("Depósito por guapo");
        withdrawal.setInterest(0);
        withdrawal.setBankAccount(bankAccount.getId());
        withdrawal.setEntity("N/A");
        double totalAmount = bankAccount.getTotalAmount();
        if(totalAmount < amount){
            //TODO: Poner log INFO
            return;
        }
        totalAmount = totalAmount - amount;
        bankAccount.setTotalAmount(totalAmount);
        operationList.add(withdrawal);
        bankAccount.setOperations(operationList);
        bankAccountRepository.save(bankAccount);
        operationRepository.save(withdrawal);
    }

    //TODO: recoger el metodo en metodos mas pequeños
    @Override
    public void createTransferOperation(String originAccountNumber, String destinationAccountNumber, double amount) {
        OperationTransfer transfer = new OperationTransfer();
        BankAccount originAccount = bankAccountRepository.findById(originAccountNumber);
        BankAccount destinationAccount = bankAccountRepository.findById(destinationAccountNumber);
        List<Operation> destinationOperationList = new ArrayList<>();
        List<Operation> originOperationList = new ArrayList<>();
        if(originAccount.getOperations() != null){
            originOperationList = originAccount.getOperations();
        }
        if(destinationAccount.getOperations() != null){
            destinationOperationList = destinationAccount.getOperations();
        }
        transfer.setFecha("2024-03-08");
        transfer.setCantidad(amount);
        transfer.setDescripcion("Depósito por guapo");
        if(originAccount.getEntity() != destinationAccount.getEntity()){
            transfer.setInterest(2.99);
        }else{
            transfer.setInterest(0);
        }
        transfer.setBankAccount(originAccount.getId());
        transfer.setDestinationAccount(destinationAccount.getId());
        transfer.setDestinationEntity(destinationAccount.getEntity());
        transfer.setEntity(originAccount.getEntity());
        double originTotalAmount = originAccount.getTotalAmount();
        double destinationTotalAmount = destinationAccount.getTotalAmount();
        if(originTotalAmount < amount){
            //TODO: Poner log INFO
            return;
        }
        originTotalAmount = originTotalAmount - amount - transfer.getInterest();
        destinationTotalAmount = destinationTotalAmount + amount;

        originAccount.setTotalAmount(originTotalAmount);
        destinationAccount.setTotalAmount(destinationTotalAmount);

        originOperationList.add(transfer);
        destinationOperationList.add(transfer);

        originAccount.setOperations(originOperationList);
        destinationAccount.setOperations(destinationOperationList);

        bankAccountRepository.save(originAccount);
        bankAccountRepository.save(destinationAccount);
        operationRepository.save(transfer);
    }
    @Override
    public BankAccount createDepositOperationObjectBank(BankAccount bankAccount, double amount) {
        OperationDeposit deposit = new OperationDeposit();
        List<Operation> operationList = new ArrayList<>();
        if(bankAccount.getOperations() != null){
             operationList = bankAccount.getOperations();
        }
        deposit.setFecha("2024-03-08");
        deposit.setOrigin("Castelldefels");
        deposit.setCantidad(amount);
        deposit.setDescripcion("Depósito por guapo");
        deposit.setInterest(0);
        deposit.setBankAccount(bankAccount.getId());
        deposit.setEntity("N/A");
        operationList.add(deposit);
        bankAccount.setOperations(operationList);
        double totalAmount = bankAccount.getTotalAmount();
        totalAmount = totalAmount + amount;
        bankAccount.setTotalAmount(totalAmount);
        operationRepository.save(deposit);
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }


}
