package gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Controller;

import gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Service.UserRegistrationService;
import org.openapi.example.model.CreateUserRequestV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController{

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/v2/users/")
    public ResponseEntity<?> userRegistration(CreateUserRequestV2 createUserRequestV2){
        return userRegistrationService.createUserV2(createUserRequestV2);
    }
}
