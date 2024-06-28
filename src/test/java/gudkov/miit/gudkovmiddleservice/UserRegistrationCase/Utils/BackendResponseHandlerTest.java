package gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Utils;

import org.junit.jupiter.api.Test;
import org.openapi.example.model.ErrorV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class BackendResponseHandlerTest {

    @Test
    void handleUserCreateBackendResponse_Success() {
        BackendResponseHandler handler = new BackendResponseHandler();
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        ResponseEntity<?> handledEntity = handler.handleUserCreateBackendResponse(response);

        assertEquals(handledEntity.getStatusCode().value(),204);
    }


    @Test
    void handleUserCreateBackendResponse_Error_409() {
        BackendResponseHandler handler = new BackendResponseHandler();
        String jsonErrorMock = """
                                    {
                                    "message": "Такой счет у данного пользователя уже есть.",
                                    "type": "General",
                                    "code": 409,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        ResponseEntity<?> response = new ResponseEntity<>(jsonErrorMock, HttpStatus.CONFLICT);

        ResponseEntity<?> handledEntity = handler.handleUserCreateBackendResponse(response);

        assertEquals(handledEntity.getStatusCode().value(),409);
        assertEquals(handledEntity.getBody().getClass(), ErrorV2.class);
    }

    @Test
    void handleUserCreateBackendResponse_Error_General() {
        BackendResponseHandler handler = new BackendResponseHandler();
        String jsonErrorMock = """
                                    {
                                    "message": "Непредвиденная ошибка.",
                                    "type": "General",
                                    "code": 500,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        ResponseEntity<?> response = new ResponseEntity<>(jsonErrorMock, HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<?> handledEntity = handler.handleUserCreateBackendResponse(response);

        assertEquals(handledEntity.getStatusCode().value(),500);
        assertEquals(handledEntity.getBody().getClass(), ErrorV2.class);
    }
}