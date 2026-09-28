package br.com.doefacil.srv.ongs.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnderecoLookupService {

    private final RestClient restClient = RestClient.create();

    public EnderecoCep consultar(String cepInformado) {
        String cep = cepInformado.replaceAll("\\D", "");
        if (!cep.matches("\\d{8}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP deve conter oito dígitos");
        }

        ViaCepResponse response;
        try {
            response = restClient.get()
                    .uri("https://viacep.com.br/ws/{cep}/json/", cep)
                    .retrieve()
                    .body(ViaCepResponse.class);
        } catch (RestClientException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de consulta de CEP indisponível");
        }

        if (response == null || Boolean.TRUE.equals(response.erro())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado");
        }
        return new EnderecoCep(cep, response.logradouro(), response.localidade(), response.uf());
    }

    private record ViaCepResponse(
            String cep,
            @JsonProperty("logradouro") String logradouro,
            @JsonProperty("localidade") String localidade,
            String uf,
            Boolean erro
    ) {
    }
}