package es.babel.bankExercise.controller;
import es.babel.bankExercise.model.BankAccount;
import es.babel.bankExercise.service.BankAccountService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;
    @PostConstruct
    @GetMapping("/initialize")
    public BankAccount initializeBankAccount() {
        return bankAccountService.createBankAccountWithOperations();
    }
    @GetMapping("/listBankAccountsByClient/{clientCode}")
    public List<BankAccount> listBankAccount(@PathVariable int clientCode){
        return bankAccountService.getBankAccountsByClient(clientCode);
    }

    @GetMapping("/createBankAccount/{clientCode}")
    public boolean createBankAccount(@PathVariable int clientCode){
        return bankAccountService.createBankAccount(clientCode);
    }


}
