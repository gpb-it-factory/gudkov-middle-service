package gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Service;

import feign.FeignException;
import gudkov.miit.gudkovmiddleservice.UserRegistrationCase.FeignBackendAPI.UserRegistrationBackendServiceAPI;
import gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Utils.UserRegistrationBackendResponseMocker;
import gudkov.miit.gudkovmiddleservice.UserRegistrationCase.Utils.BackendResponseHandler;
import jakarta.validation.Valid;
import org.openapi.example.model.CreateUserRequestV2;
import org.openapi.example.model.ErrorV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This service is built according to OpenAPI specification.
 * This service is not implementing OpenAPIGenerator's interface
 * Not implementing deprecated endpoints either
 */
@Service
public class UserRegistrationService {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);
    private final UserRegistrationBackendServiceAPI backendServiceAPI;
    private final BackendResponseHandler backendResponseHandler;
    private final UserRegistrationBackendResponseMocker backendRequestMocker;

    public UserRegistrationService(UserRegistrationBackendServiceAPI backendServiceAPI,
                                   BackendResponseHandler backendResponseHandler,
                                   UserRegistrationBackendResponseMocker backendRequestMocker){
        this.backendServiceAPI = backendServiceAPI;
        this.backendResponseHandler = backendResponseHandler;
        this.backendRequestMocker = backendRequestMocker;
    }

    public ResponseEntity<?> createUserV2(@Valid @RequestBody CreateUserRequestV2 createUserRequestV2) {
        try{
            return backendResponseHandler.handleUserCreateBackendResponse(backendRequestMocker.mockCreateUserResponseV2_Success());
            //return backendResponseHandler.handleBackendResponse(backendServiceAPI.createUserV2(createUserRequestV2));
        } catch (FeignException feignException){
            log.info("FeignClient caused an exception. Check feign's backendApiService. Timestamp: {}",LocalDateTime.now());
            return new ResponseEntity<>(new ErrorV2("InternalError","Internal","500",UUID.randomUUID()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
