package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountCreationResponseMocker {

    public ResponseEntity<?> mockCreateAccountResponseV2_Error_409(){
        String jsonErrorMock = """
                                    {
                                    "message": "Такой счет у данного пользователя уже есть.",
                                    "type": "General",
                                    "code": 409,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        return new ResponseEntity<>(jsonErrorMock, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> mockCreateAccountResponseV2_Error(){
        String jsonErrorMock = """
                                    {
                                    "message": "Непредвиденная ошибка",
                                    "type": "Internal",
                                    "code": 500,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        return new ResponseEntity<>(jsonErrorMock, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> mockCreateAccountResponseV2_Success(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
