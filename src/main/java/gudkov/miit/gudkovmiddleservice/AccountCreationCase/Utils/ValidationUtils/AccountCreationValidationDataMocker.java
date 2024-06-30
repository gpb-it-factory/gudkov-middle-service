package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils.ValidationUtils;


import org.openapi.example.model.UserResponseV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountCreationValidationDataMocker {

    public ResponseEntity<?> getUserByTelegramId_Found(){
        UserResponseV2 data = new UserResponseV2(UUID.randomUUID());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public ResponseEntity<?> getUserByTelegramId_NotFound(){
        String jsonErrorMock = """
                                    {
                                    "message": "Непредвиденная ошибка",
                                    "type": "Internal",
                                    "code": 500,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        return new ResponseEntity<>(jsonErrorMock, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
