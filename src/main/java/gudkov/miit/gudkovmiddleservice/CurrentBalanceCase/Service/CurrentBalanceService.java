package gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Service;

import feign.FeignException;
import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.FeignBackendAPI.CurrentBalanceBackendServiceAPI;
import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils.CurrentBalanceBackendResponseHandler;
import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils.CurrentBalanceDataMocker;
import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils.CurrentBalanceRequestValidator;
import org.openapi.example.model.ErrorV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CurrentBalanceService {

    private static final Logger log = LoggerFactory.getLogger(CurrentBalanceService.class);
    private final CurrentBalanceBackendServiceAPI backendServiceAPI;
    private final CurrentBalanceRequestValidator requestValidator;
    private final CurrentBalanceBackendResponseHandler responseHandler;
    private final CurrentBalanceDataMocker dataMocker;

    public CurrentBalanceService(CurrentBalanceBackendServiceAPI backendServiceAPI,
                                 CurrentBalanceRequestValidator requestValidator,
                                 CurrentBalanceBackendResponseHandler responseHandler,
                                 CurrentBalanceDataMocker dataMocker){
        this.backendServiceAPI = backendServiceAPI;
        this.requestValidator = requestValidator;
        this.responseHandler = responseHandler;
        this.dataMocker = dataMocker;
    }

    public ResponseEntity<?> getUserAccountsV2(long userId){
        if(requestValidator.isRequestValid(userId)){
            try{
                //return responseHandler.handleBackendGetAccountsResponse(backendServiceAPI.getUsersAccountsV2(userId));
                return responseHandler.handleBackendGetAccountsResponse(dataMocker.mockGetCurrentBalance_WithData());
            } catch (FeignException e){
                log.error("FeignClient caused an exception while getUserAccount(). Check feign's backendApiService. Timestamp: {}", LocalDateTime.now());
                return new ResponseEntity<>(new ErrorV2("FeignInternalError","Internal","500", UUID.randomUUID()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            log.warn("Non-valid request to getUserAccountsV2() at CurrentBalanceFlow at {}", LocalDateTime.now());
            return new ResponseEntity<>(new ErrorV2("Невалидный запрос","BAD_REQUEST","400", UUID.randomUUID()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
