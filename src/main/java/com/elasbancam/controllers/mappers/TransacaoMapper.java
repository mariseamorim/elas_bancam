package com.elasbancam.controllers.mappers;

import com.elasbancam.dtos.TransacaoDto;
import com.elasbancam.models.Transacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class TransacaoMapper {
    private ModelMapper modelMapper;

    public Transacao toEntityTransacao(TransacaoDto transacaoDto) {
        return modelMapper.map(transacaoDto, Transacao.class);
    }
}
