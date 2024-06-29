package gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Controller;


import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Service.CurrentBalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2")
public class CurrentBalanceController {

    private final CurrentBalanceService currentBalanceService;

    public CurrentBalanceController(CurrentBalanceService currentBalanceService){
        this.currentBalanceService = currentBalanceService;
    }

    @GetMapping("/users/{id}/accounts")
    public ResponseEntity<?> getUserAccountsV2(@PathVariable("id") long id){
        return currentBalanceService.getUserAccountsV2(id);
    }

}
