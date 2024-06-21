package gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BackendRequestMocker {

    public ResponseEntity<?> mockCreateUserResponseV2_Error(){
        String jsonErrorMock = """
                                    {
                                    "message": "TestError",
                                    "type": "General",
                                    "code": 403,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        return new ResponseEntity<>(jsonErrorMock, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<?> mockCreateUserResponseV2_Success(){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}