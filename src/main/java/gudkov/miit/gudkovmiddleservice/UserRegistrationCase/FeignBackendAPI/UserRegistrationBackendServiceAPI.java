package gudkov.miit.gudkovmiddleservice.UserRegistrationCase.FeignBackendAPI;

import jakarta.validation.Valid;
import org.openapi.example.model.CreateUserRequestV2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * FeignClient to communicate with backend-service
 */

@FeignClient(name = "UserRegistrationBackendService", url = "${backend.server.url}")
public interface UserRegistrationBackendServiceAPI {

    @RequestMapping(method = RequestMethod.POST,
                    value ="/v2/users",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUserV2(@Valid @RequestBody CreateUserRequestV2 createUserRequestV2);
}
