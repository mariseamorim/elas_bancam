package com.elasbancam.controllers.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.elasbancam.dtos.PessoaFisicaDto;
import com.elasbancam.dtos.PessoaFisicaUpdateDto;
import com.elasbancam.dtos.PessoaJuridicaDto;
import com.elasbancam.dtos.PessoaJuridicaUpdateDto;
import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClienteMapper {
    private ModelMapper modelMapper;

    public PessoaFisica toModel(PessoaFisica pessoaFisicaInput) {
        return modelMapper.map(pessoaFisicaInput, PessoaFisica.class);
    }

    public List<PessoaFisica> toCollectionModelPf(List<PessoaFisica> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

   public PessoaFisica toEntityPf(PessoaFisicaDto pessoaFisicaDtoInput) {
        return modelMapper.map(pessoaFisicaDtoInput, PessoaFisica.class);
    }

    public PessoaJuridica toModelPj(PessoaJuridica pessoaJuridica) {
        return modelMapper.map(pessoaJuridica, PessoaJuridica.class);
    }

    public List<PessoaJuridica> toCollectionModelPj(List<PessoaJuridica> clientes) {
        return clientes.stream()
                .map(this::toModelPj)
                .collect(Collectors.toList());
    }

    public PessoaJuridica toEntityPj(PessoaJuridicaDto pessoaJuridicaDtoInput) {
        return modelMapper.map(pessoaJuridicaDtoInput, PessoaJuridica.class);
    }

    public void toEntityUpdatePf(PessoaFisicaUpdateDto pessoaFisicaUpdateDto, PessoaFisica pessoaFisica) {
        modelMapper.map(pessoaFisicaUpdateDto, pessoaFisica);
    }

    public void toEntityUpdatePj(PessoaJuridicaUpdateDto pessoaFisicaUpdateDto, PessoaJuridica pessoaJuridica) {
        modelMapper.map(pessoaFisicaUpdateDto, pessoaJuridica);
    }
}
