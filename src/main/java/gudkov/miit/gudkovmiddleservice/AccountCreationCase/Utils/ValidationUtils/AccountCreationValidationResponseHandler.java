package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils.ValidationUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapi.example.model.ErrorV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Bean that should validate response from backend service call for /v2/users/{telegram-id} endpoint.
 * Due to specification:
 *  - if response code is 200 (deprecated: trying to map to UserResponseV2 entity) just sending backend response through.
 *  - otherwise we suppose that undefined error and map it into ErrorV2
 */
@Component
public class AccountCreationValidationResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(AccountCreationValidationResponseHandler.class);

    public ResponseEntity<?> handleGetUserByTelegramIdResponse(ResponseEntity<?> response){
        if (response.getStatusCode().value() == 200) {
            return response;
        } else {
            return buildErrorV2Response(response);
        }
    }

    /**
     * Method that trying to map backend response to ErrorV2 entity.
     * @param incomingData - backend response to getUserByTelegramID call
     * @return new ResponseEntity with ErrorV2 body and backendResponse HttpStatus code.
     */
    private ResponseEntity<?> buildErrorV2Response(ResponseEntity<?> incomingData){
        ErrorV2 responseErrorDefault = new ErrorV2();
        try {
            responseErrorDefault = new ObjectMapper().readValue(incomingData.getBody().toString(), ErrorV2.class);
        } catch (JsonProcessingException e) {
            log.error("JSON couldn't parse to ErrorV2. Ended with exception: {} at {}", e.getMessage(), LocalDateTime.now());
        }
        log.error("Exception happened on ValidationHandler at {}", LocalDateTime.now());
        return new ResponseEntity<>(responseErrorDefault, incomingData.getStatusCode());
    }
}
