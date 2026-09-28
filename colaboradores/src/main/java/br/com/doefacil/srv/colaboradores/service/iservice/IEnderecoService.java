package br.com.doefacil.srv.colaboradores.service.iservice;

import br.com.doefacil.srv.colaboradores.repository.entity.EnderecoEntity;

public interface IEnderecoService {

    void createEndereco(EnderecoEntity endereco);

    EnderecoEntity findByID(Long id);

    EnderecoEntity findByParam(String cep, String numero, String rua);
}
