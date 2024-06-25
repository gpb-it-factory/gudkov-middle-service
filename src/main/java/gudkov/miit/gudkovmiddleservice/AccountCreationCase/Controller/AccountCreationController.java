package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Controller;

import gudkov.miit.gudkovmiddleservice.AccountCreationCase.Service.AccountCreationService;
import jakarta.validation.Valid;
import org.openapi.example.model.CreateAccountRequestV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/users")
public class AccountCreationController {

    private final AccountCreationService accountCreationService;

    public AccountCreationController(AccountCreationService accountCreationService){
        this.accountCreationService = accountCreationService;
    }

    @PostMapping("/{id}/accounts")
    public ResponseEntity<?> createUserAccount(@PathVariable("id") long id,
                                               @Valid @RequestBody CreateAccountRequestV2 createAccountRequestV2){
        return accountCreationService.createUserAccount(id,createAccountRequestV2);
    }
}
