package pl.gatomek.webdicted.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.gatomek.webdicted.entity.Language;


import java.util.Collections;

@Service
public class ExternalDictionaryService {
    private static final String REST_URI = "https://api.pons.com/v1/dictionary";

    private final RestTemplate restTemplate;

    @Value("${dict.api.secret}")
    private String dictApiSecret;

    public ExternalDictionaryService( RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private String makeQueryURLFriendly( String query) {
        query = query.replace( "ä", "ae");
        query = query.replace( "ö", "oe");
        query = query.replace( "ü", "ue");
        query = query.replace( "ß", "ss");
        return query;
    }

    public String getTranslation(Language lang, String query) {
        String langParam = lang.getLabel() + "pl";

        String urlTemplate = UriComponentsBuilder
                .fromHttpUrl( REST_URI)
                .queryParam( "l", langParam)
                .queryParam("q", makeQueryURLFriendly(query.toLowerCase()))
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)
        );
        headers.add( "X-Secret", dictApiSecret);
        HttpEntity<String> httpEntity = new HttpEntity<>( headers);

        try {
            ResponseEntity<String> response
                    = restTemplate.exchange(urlTemplate, HttpMethod.GET, httpEntity, String.class);

            HttpStatusCode code = response.getStatusCode();
            if( code == HttpStatus.OK)
                return response.getBody();

            return "[]";
        }
        catch ( Exception ex) {
            System.out.println(ex.getMessage());
            return "[]";
        }
    }
}
