package es.babel.bankExercise.controller;

import es.babel.bankExercise.service.BankAccountService;
import es.babel.bankExercise.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping("/createDeposit")
    public boolean createDeposit(@RequestParam String accountNumber, @RequestParam Double amount)
    {
        operationService.createDepositOperation(accountNumber, amount);
        return true;
    }
    @GetMapping("/createWithdrawal")
    public boolean createWithdrawal(@RequestParam String accountNumber, @RequestParam Double amount)
    {
        operationService.createWithdrawalOperation(accountNumber, amount);
        return true;
    }
    @GetMapping("/createTransfer")
    public boolean createTransfer(@RequestParam String originAccountNumber, @RequestParam String destinationAccountNumber,
                                  @RequestParam Double amount)
    {
        operationService.createTransferOperation(originAccountNumber, destinationAccountNumber, amount);
        return true;
    }
}
