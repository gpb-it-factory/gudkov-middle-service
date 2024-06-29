package gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils;

import feign.FeignException;
import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils.ValidationUtils.CurrentBalanceValidationDataMocker;
import gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.Utils.ValidationUtils.CurrentBalanceValidationResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Bean that validates /currentBalance requests by using /v2/users/{tg-id} backend endpoint to check
 * is user registered or not
 */
@Component
public class CurrentBalanceRequestValidator {

    private static final Logger log = LoggerFactory.getLogger(CurrentBalanceRequestValidator.class);
    private final CurrentBalanceValidationResponseHandler validationResponseHandler;
    private final CurrentBalanceValidationDataMocker validationDataMocker;

    public CurrentBalanceRequestValidator(CurrentBalanceValidationDataMocker validationDataMocker,
                                          CurrentBalanceValidationResponseHandler validationResponseHandler){
        this.validationDataMocker = validationDataMocker;
        this.validationResponseHandler = validationResponseHandler;
    }

    /**
     * This method validates user's request to get his account list by checking is this user registered in our app.
     * For now, Backend sends only OK-200 with user's account data / or unhandled error.
     * So there is no response status to check is account created or not - we just send him ErrorV2
     * @param id - TG user's id who's sending the request
     * @return true/false value - is request valid or not
     */
    public boolean isRequestValid(long id){
        try {
            //uncomment when backend is established
            //ResponseEntity<?> validationResponse = validationResponseHandler.handleGetUserByTelegramIdResponse(backendServiceAPI.getUserByTelegramIdV2(id));
            ResponseEntity<?> validationResponse = validationResponseHandler.handleGetUserByTelegramIdResponse(validationDataMocker.getUserByTelegramId_Found());
            return validationResponse.getStatusCode().value() == 200;
        } catch (FeignException e){
            log.error("FeignException: {} happened when trying to access getUserByTelegramIdV2 endpoint on backend at {}",e, LocalDateTime.now());
            return false;
        }
    }


}
