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

    private static final String EMPTY_LIST = "[]";
    private static final String X_SECRET = "X-Secret";
    private static final String L_PARAM = "l";
    private static final String Q_PARAM = "q";
    private static final String PL = "pl";

    private final RestTemplate restTemplate;

    @Value("${dict.api.secret}")
    private String dictApiSecret;

    public ExternalDictionaryService( RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private String makeQueryURLFriendly( Language lang, String query) {
        query = query.toLowerCase();

        if( Language.DE.equals( lang))
            return makeDeQueryUrlFriendly( query);

        return query;
    }

    private String makeDeQueryUrlFriendly(String query) {
        return query.replace( "ä", "ae")
                    .replace( "ö", "oe")
                    .replace( "ü", "ue")
                    .replace( "ß", "ss");
    }

    public String getTranslation(Language lang, String query) {
        String langParam = lang.getLabel() + PL;

        String urlTemplate = UriComponentsBuilder
                .fromHttpUrl( REST_URI)
                .queryParam(L_PARAM, langParam)
                .queryParam(Q_PARAM, makeQueryURLFriendly(lang, query))
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)
        );
        headers.add(X_SECRET, dictApiSecret);
        HttpEntity<String> httpEntity = new HttpEntity<>( headers);

        try {
            ResponseEntity<String> response
                    = restTemplate.exchange(urlTemplate, HttpMethod.GET, httpEntity, String.class);

            HttpStatusCode code = response.getStatusCode();
            if( code == HttpStatus.OK)
                return response.getBody();

            return EMPTY_LIST;
        }
        catch ( Exception ex) {
            return EMPTY_LIST;
        }
    }
}
