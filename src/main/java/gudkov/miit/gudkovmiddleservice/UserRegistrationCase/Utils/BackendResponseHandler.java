package gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Utils;

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
public class BackendResponseHandler {

    private static final Logger log = LoggerFactory.getLogger(BackendResponseHandler.class);

    public ResponseEntity<?> handleUserCreateBackendResponse(ResponseEntity<?> response){
        if(response.getStatusCode().value() == 204){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            //todo; ну че-то подумать надо, а то выглядит как мясо
            ErrorV2 responseError = new ErrorV2();
            try {
                responseError = new ObjectMapper().readValue(response.getBody().toString(), ErrorV2.class);
            } catch (JsonProcessingException e) {
                log.info("JSON could parse. Ended with exception: {} at {}",e.getMessage(), LocalDateTime.now());
            }
            return new ResponseEntity<>(responseError,response.getStatusCode());
        }
    }
}
