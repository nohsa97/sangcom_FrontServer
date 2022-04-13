package capstone.sangcom.service;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.RegisterDTO;
import capstone.sangcom.request.login.RefreshRequest;
import capstone.sangcom.response.login.LoginResponse;
import capstone.sangcom.response.login.SuccessResponse;
import capstone.sangcom.response.login.TokenResponse;
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
public class LoginServiceImpl implements LoginService{

    private RestTemplate restTemplate;

    public LoginServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public LoginResponse login(LoginDTO loginInfo){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:3000")
                .path("/api/user/login")
                .encode()
                .build()
                .toUri();

        RequestEntity<LoginDTO> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization","null")
                .body(loginInfo);

        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<LoginResponse> response = restTemplate.exchange(requestEntity, LoginResponse.class);

        return response.getBody();
    }

    @Override
    public boolean checkLogin(String access, String refresh){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:3000")
                .path("/api/user/login/check")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("authorization",access)
                .build();

        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<SuccessResponse> response = restTemplate.exchange(requestEntity, SuccessResponse.class);

        return response.getBody().isSuccess();
    }

    @Override
    public String getAccessToken(String refresh){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:3000")
                .path("/api/auth/refresh")
                .encode()
                .build()
                .toUri();

        RequestEntity<RefreshRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization","null")
                .body(new RefreshRequest(refresh));

        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<TokenResponse> response = restTemplate.exchange(requestEntity, TokenResponse.class);

        return response.getBody().getAccess_token();
    }

    @Override
    public boolean findPassword(FindPasswordDTO findPasswordDTO) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:3000")
                .path("/api/user/password/find")
                .encode()
                .build()
                .toUri();


        RequestEntity<FindPasswordDTO> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization","null")
                .body(findPasswordDTO);

        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<SuccessResponse> response = restTemplate.exchange(requestEntity, SuccessResponse.class);

        return response.getBody().isSuccess();
    }

    @Override
    public boolean register(RegisterDTO registerDTO) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:3000")
                .path("/api/user/register")
                .encode()
                .build()
                .toUri();

        RequestEntity<RegisterDTO> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization","null")
                .body(registerDTO);

        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<SuccessResponse> response = restTemplate.exchange(requestEntity, SuccessResponse.class);

        System.out.println(response.getBody().isSuccess());

        return response.getBody().isSuccess();
    }
}
