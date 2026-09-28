package br.com.doefacil.srv.ongs.controller;

import br.com.doefacil.srv.ongs.controller.dto.req.LoginDtoReq;
import br.com.doefacil.srv.ongs.controller.dto.resp.LoginRespDto;
import br.com.doefacil.srv.ongs.service.OngsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:5173") 
public class LoginContoller {

    OngsService service;

    public LoginContoller(OngsService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public LoginRespDto executarLogin(@RequestBody @Valid LoginDtoReq login){
        return service.login(login);
    }

}
