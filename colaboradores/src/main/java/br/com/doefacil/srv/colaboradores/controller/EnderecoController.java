package br.com.doefacil.srv.colaboradores.controller;


import br.com.doefacil.srv.colaboradores.controller.req.EnderecoReqDto;
import br.com.doefacil.srv.colaboradores.controller.resp.EnderecoRespDto;
import br.com.doefacil.srv.colaboradores.repository.entity.EnderecoEntity;
import br.com.doefacil.srv.colaboradores.service.iservice.IEnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "http://localhost:5173") 
public class EnderecoController {

    IEnderecoService enderecoService;

    public EnderecoController(IEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void salvarEndereco(@RequestBody @Valid EnderecoReqDto reqDto) {

        enderecoService.createEndereco(
                new EnderecoEntity(
                        reqDto.rua(),
                        reqDto.numero(),
                        reqDto.cep(),
                        reqDto.complemento())
        );
    }

    @GetMapping
    ResponseEntity<EnderecoRespDto> procuraEndereco(@RequestParam(required = true) String cep,
                                                    @RequestParam(required = false) String numero,
                                                    @RequestParam(required = false) String rua) {

        EnderecoEntity endereco = enderecoService.findByParam(cep, numero, rua);

        return ResponseEntity.ok(
                new EnderecoRespDto(
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getCep(),
                        endereco.getComplemento()
                )
        );
    }
}
