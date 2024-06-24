package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils;

import org.junit.jupiter.api.Test;
import org.openapi.example.model.ErrorV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class AccountCreationBackendResponseHandlerTest {

    @Test
    void handleBackendCreateAccountResponse_Success() {
        AccountCreationBackendResponseHandler handler = new AccountCreationBackendResponseHandler();
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        ResponseEntity<?> handledEntity = handler.handleBackendCreateAccountResponse(response);

        assertEquals(handledEntity.getStatusCode().value(),204);
    }

    @Test
    void handleBackendCreateAccountResponse_Error_409() {
        AccountCreationBackendResponseHandler handler = new AccountCreationBackendResponseHandler();
        String jsonErrorMock = """
                                    {
                                    "message": "Счет уже открыт.",
                                    "type": "General",
                                    "code": 409,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        ResponseEntity<?> response = new ResponseEntity<>(jsonErrorMock, HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<?> handledEntity = handler.handleBackendCreateAccountResponse(response);

        assertEquals(handledEntity.getStatusCode().value(),500);
        assertEquals(handledEntity.getBody().getClass(), ErrorV2.class);

    }

    @Test
    void handleBackendCreateAccountResponse_Error_General() {
        AccountCreationBackendResponseHandler handler = new AccountCreationBackendResponseHandler();
        String jsonErrorMock = """
                                    {
                                    "message": "Непредвиденная ошибка.",
                                    "type": "General",
                                    "code": 500,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        ResponseEntity<?> response = new ResponseEntity<>(jsonErrorMock, HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<?> handledEntity = handler.handleBackendCreateAccountResponse(response);

        assertEquals(handledEntity.getStatusCode().value(),500);
        assertEquals(handledEntity.getBody().getClass(), ErrorV2.class);

    }
}