package capstone.sangcom.service;

import capstone.sangcom.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class LoginService {

    private RestTemplate restTemplate;

    public LoginService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public LoginResponse login(LoginDTO loginInfo){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:3000")
                .path("/api/user/login")
                .encode()
                .build()
                .toUri();

        log.info(String.valueOf(uri));

        RequestEntity<LoginDTO> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization","null")
                .body(loginInfo);

        log.info("sending");
        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<LoginResponse> response = restTemplate.exchange(requestEntity, LoginResponse.class);

        return response.getBody();
    }

}
