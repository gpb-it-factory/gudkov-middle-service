package gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils;

import feign.FeignException;
import gudkov.miit.gudkovmiddleservice.AccountCreationCase.FeignBackendAPI.AccountCreateBackendServiceAPI;
import gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils.ValidationUtils.AccountCreationValidationDataMocker;
import gudkov.miit.gudkovmiddleservice.AccountCreationCase.Utils.ValidationUtils.AccountCreationValidationResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Bean that using /v2/users/{telegram-id} to validate users request on account creation.
 * Communicating with backend-service trough FeignClient.
 */
@Component
public class AccountCreationRequestValidator {

    private static final Logger log = LoggerFactory.getLogger(AccountCreationRequestValidator.class);
    private final AccountCreateBackendServiceAPI backendServiceAPI;
    private final AccountCreationValidationDataMocker validationDataMocker;
    private final AccountCreationValidationResponseHandler validationResponseHandler;

    public AccountCreationRequestValidator(AccountCreateBackendServiceAPI backendServiceAPI,
                                           AccountCreationValidationDataMocker validationDataMocker,
                                           AccountCreationValidationResponseHandler validationResponseHandler){
        this.backendServiceAPI = backendServiceAPI;
        this.validationDataMocker = validationDataMocker;
        this.validationResponseHandler = validationResponseHandler;
    }

    /**
     * Method that validates upcoming CreateAccountRequest.
     * The logic is -> to create an account user must be registered in our bank otherwise creation is not available.
     * @param id - Telegram's ID of user who's trying to create new account
     * @return - true or false value. True - user us registered and request can be handled.
     * False - user need to register first/ or there is problem with validation and user need to try later
     * also if the result of this method is false - AccountCreationService will send ErrorV2 as response.
     */
    public boolean isTheRequestValid(long id){
        try {
            //uncomment when backend is established
            //ResponseEntity<?> validationResponse = validationResponseHandler.handleGetUserByTelegramIdResponse(backendServiceAPI.getUserByTelegramIdV2(id));
            ResponseEntity<?> validationResponse = validationResponseHandler.handleGetUserByTelegramIdResponse(validationDataMocker.getUserByTelegramId_Found());
            return validationResponse.getStatusCode().value() == 200;
        } catch (FeignException e){
            log.error("FeignException happened when trying to access getUserByTelegramIdV2 endpoint on backend at {}", LocalDateTime.now());
            return false;
        }
    }

}
