package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Service;


import feign.FeignException;
import gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils.AccountCreationBackendResponseHandler;
import gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils.AccountCreationResponseMocker;
import gudkov.miit.gudkovmiddleservice.FeignBackendAPI.BackendServiceAPI;
import jakarta.validation.Valid;
import org.openapi.example.model.CreateAccountRequestV2;
import org.openapi.example.model.ErrorV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountCreationService {

    private static final Logger log = LoggerFactory.getLogger(AccountCreationService.class);
    private final BackendServiceAPI backendServiceAPI;
    private final AccountCreationBackendResponseHandler backendResponseHandler;
    private final AccountCreationResponseMocker responseMocker;

    public AccountCreationService(BackendServiceAPI backendServiceAPI,
                                  AccountCreationBackendResponseHandler backendResponseHandler,
                                  AccountCreationResponseMocker responseMocker){
        this.backendServiceAPI = backendServiceAPI;
        this.backendResponseHandler = backendResponseHandler;
        this.responseMocker = responseMocker;
    }

    public ResponseEntity<?> createUserAccount(@PathVariable("id") long id,
                                               @Valid CreateAccountRequestV2 createAccountRequestV2){
        try{
            return backendResponseHandler.handleBackendCreateAccountResponse(responseMocker.mockCreateAccountResponseV2_Success());
            //return backendResponseHandler.handleBackendCreateAccountResponse(backendServiceAPI.createUserAccountV2(id, createAccountRequestV2));
        } catch (FeignException e){
            log.info("FeignClient caused an exception. Check feign's backendApiService. Timestamp: {}", LocalDateTime.now());
            return new ResponseEntity<>(new ErrorV2("InternalError","Internal","500", UUID.randomUUID()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
