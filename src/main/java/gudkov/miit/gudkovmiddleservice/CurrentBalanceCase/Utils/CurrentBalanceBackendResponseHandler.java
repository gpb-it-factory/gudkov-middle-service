package gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapi.example.model.AccountsListResponseV2Inner;
import org.openapi.example.model.ErrorV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This ResponseHandler handles response from /v2/users/{id}/accounts GET requests.
 */
@Component
public class CurrentBalanceBackendResponseHandler {

    private static final Logger log = LoggerFactory.getLogger(CurrentBalanceBackendResponseHandler.class);

    public ResponseEntity<?> handleBackendGetAccountsResponse(ResponseEntity<?> response){
        if(response.getStatusCode().value() == 200) {
            return buildAccountListResponse(response);
        } else {
            return buildErrorV2Response(response);
        }
    }

    /**
     * Method that trying to map backend response to AccountListResponseV2Inner entity.
     * @param incomingData - backend response to getUserAccountsV2 call
     * @return new ResponseEntity with ErrorV2 body and backendResponse HttpStatus code.
     */
    private ResponseEntity<?> buildAccountListResponse(ResponseEntity<?> incomingData){
        AccountsListResponseV2Inner responseBody;
        try {
            responseBody = new ObjectMapper().readValue(incomingData.getBody().toString(), AccountsListResponseV2Inner.class);
            return new ResponseEntity<>(responseBody, incomingData.getStatusCode());
        } catch (JsonProcessingException e) {
            log.error("JSON couldn't response parse into AccountListResponse. Ended with exception: {} at {}", e.getMessage(), LocalDateTime.now());
            return new ResponseEntity<>(new ErrorV2("InternalError","Internal","500", UUID.randomUUID()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method that trying to map backend response to ErrorV2 entity.
     * @param incomingData - backend response to getUserAccountsV2 call
     * @return new ResponseEntity with ErrorV2 body and backendResponse HttpStatus code.
     */
    private ResponseEntity<?> buildErrorV2Response(ResponseEntity<?> incomingData){
        ErrorV2 responseErrorDefault;
        try {
            responseErrorDefault = new ObjectMapper().readValue(incomingData.getBody().toString(), ErrorV2.class);
            return new ResponseEntity<>(responseErrorDefault, incomingData.getStatusCode());
        } catch (JsonProcessingException e) {
            log.error("JSON couldn't parse error from GetAccount. Ended with exception: {} at {}", e.getMessage(), LocalDateTime.now());
            return new ResponseEntity<>(new ErrorV2("InternalError","Internal","500", UUID.randomUUID()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
