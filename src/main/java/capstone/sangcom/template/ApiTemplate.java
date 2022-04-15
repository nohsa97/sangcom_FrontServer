package capstone.sangcom.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class ApiTemplate {

    private final RestTemplate restTemplate;
    private static final String post = "http://localhost:3000";

    public ApiTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <P> P getExecute(String detailUri, String token, Class<P> cls){
        URI uri = UriComponentsBuilder
                .fromUriString(post)
                .path("/api" + detailUri)
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("authorization",token)
                .build();

        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<P> response = restTemplate.exchange(requestEntity, cls);

        return response.getBody();
    }

    public <Q, P> P postExecute(String detailUri, Q data, String token, Class<P> cls){
        URI uri = UriComponentsBuilder
                .fromUriString(post)
                .path("/api" + detailUri)
                .build()
                .toUri();

        RequestEntity<Q> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization",token)
                .body(data);

        ResponseEntity<P> response = restTemplate.exchange(requestEntity, cls);

        return response.getBody();
    }

    public <Q, P> P putExecute(String detailUri, Q data, String token, Class<P> cls){
        URI uri = UriComponentsBuilder
                .fromUriString(post)
                .path("/api" + detailUri)
                .build()
                .toUri();

        RequestEntity<Q> requestEntity = RequestEntity
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization",token)
                .body(data);

        ResponseEntity<P> response = restTemplate.exchange(requestEntity, cls);

        return response.getBody();
    }

    public <Q, P> P deleteExecute(String detailUri, Q data, String token, Class<P> cls) {
        URI uri = UriComponentsBuilder
                .fromUriString(post)
                .path("/api" + detailUri)
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("authorization", token)
                .build();

        ResponseEntity<P> response = restTemplate.exchange(requestEntity, cls);

        return response.getBody();
    }
}
