package gudkov.miit.gudkovmiddleservice.AccountCreationCase.FeignBackendAPI;

import jakarta.validation.Valid;
import org.openapi.example.model.CreateAccountRequestV2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * FeignClient to communicate with backend-service
 */

@FeignClient(name = "AccountCreationBackendService", url = "${backend.server.url}")
public interface AccountCreateBackendServiceAPI {
    @RequestMapping(method = RequestMethod.POST,
                    value ="/v2/users/{id}/accounts",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUserAccountV2(@PathVariable("id") long id,
                                          @Valid @RequestBody CreateAccountRequestV2 createAccountRequestV2);

    @RequestMapping(method = RequestMethod.GET,
            value ="/v2/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getUserByTelegramIdV2(@PathVariable("id") long id);
}
