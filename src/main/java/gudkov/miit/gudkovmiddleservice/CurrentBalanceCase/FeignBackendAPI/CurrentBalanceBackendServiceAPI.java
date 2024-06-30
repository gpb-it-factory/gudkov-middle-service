package gudkov.miit.gudkovmiddleservice.CurrentBalanceCase.FeignBackendAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * FeignClient to communicate with backend-service
 */

@FeignClient(name = "CurrentBalanceBackendService", url = "${backend.server.url}")
public interface CurrentBalanceBackendServiceAPI {

    @RequestMapping(method = RequestMethod.GET,
            value ="/v2/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getUserByTelegramIdV2(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.GET,
            value ="/v2/users/{id}/accounts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getUsersAccountsV2(@PathVariable("id") long id);
}
