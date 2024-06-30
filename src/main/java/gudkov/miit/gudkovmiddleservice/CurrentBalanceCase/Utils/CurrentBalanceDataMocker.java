package gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Data mocks for /v2/users/{id}/accounts requests.
 */
@Component
public class CurrentBalanceDataMocker {

    public ResponseEntity<?> mockGetCurrentBalance_WithData(){
        String jsonDataMock = """
                                    [{
                                    "accountId": "4c431738-4012-4689-a9ca-75dfc0e104e0",
                                    "accountName": "My first awesome account",
                                    "amount": "5000.00"
                                    }]""";
        return new ResponseEntity<>(jsonDataMock, HttpStatus.OK);
    }

    public ResponseEntity<?> mockGetCurrentBalance_WithMultipleData(){
        String jsonDataMock = """
                                    [{
                                    "accountId": "4c431738-4012-4689-a9ca-75dfc0e104e0",
                                    "accountName": "My first awesome account",
                                    "amount": "5000.00"
                                    },
                                    {
                                    "accountId": "8c431738-4012-4689-a9ca-75dfc0e104e0",
                                    "accountName": "My first awesome account",
                                    "amount": "5555.00"
                                    }]""";
        return new ResponseEntity<>(jsonDataMock, HttpStatus.OK);
    }


    public ResponseEntity<?> mockGetCurrentBalance_Error(){
        String jsonErrorMock = """
                                    {
                                    "message": "Непредвиденная ошибка",
                                    "type": "General",
                                    "code": 404,
                                    "traceId": "8107da96-6734-4828-8017-7aed6a3cf6d4"
                                    }""";
        return new ResponseEntity<>(jsonErrorMock, HttpStatus.NOT_FOUND);
    }
}
