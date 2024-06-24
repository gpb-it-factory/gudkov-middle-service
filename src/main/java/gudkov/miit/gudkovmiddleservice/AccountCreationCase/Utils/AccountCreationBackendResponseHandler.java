package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapi.example.model.ErrorV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountCreationBackendResponseHandler {

    private static final Logger log = LoggerFactory.getLogger(AccountCreationBackendResponseHandler.class);

    public ResponseEntity<?> handleBackendCreateAccountResponse(ResponseEntity<?> response){
        if (response.getStatusCode().value() == 204) {
            log.info("Successfully created new account at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ErrorV2 responseErrorDefault = new ErrorV2();
        try {
            responseErrorDefault = new ObjectMapper().readValue(response.getBody().toString(), ErrorV2.class);
        } catch (JsonProcessingException e) {
            log.error("JSON couldn't parse. Ended with exception: {} at {}", e.getMessage(), LocalDateTime.now());
        }
        log.error("Exception happened on AccountCreationFlow at {}", LocalDateTime.now());
        return new ResponseEntity<>(responseErrorDefault, response.getStatusCode());
    }
}
