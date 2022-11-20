package com.elasbancam.services;

import com.elasbancam.enums.TipoOperacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.repositories.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


class ContaServiceTest {

    public static final String IDPF = "cdd98c9d-4076-4004-b32f-9d3aa6ed7753";
    public static final int NUMERO_CONTAPF = 58596;
    public static final int AGENCIA = 1423;
    public static final TipoOperacao TIPO_OPERACAO = TipoOperacao.CONTACORRENTEPF;
    public static final BigDecimal SALDO = BigDecimal.valueOf(1000);
    public static final boolean STATUS = true;
    public static final int NUMERO_CONTAPJ = 141516;
    public static final String IDPJ = "7df09933-7eb1-4fb9-8261-46a8ff71a506";
    @Mock
    private ContaRepository _repositoryConta;
    @InjectMocks
    private  ContaService contaService;

    private Conta contaPF;

    private Conta contaPJ;
    private Transacao transacao;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        starContas();
    }

    @Test
    void quandoBuscarContaPorIdRetornaInstanciaConta() {
        when(_repositoryConta.buscarPorId(anyString())).thenReturn(contaPF);

        Conta resposta = contaService.buscarContaPorId(IDPF);

        assertNotNull(resposta);
        assertEquals(Conta.class, resposta.getClass());
        assertEquals(IDPF, resposta.getId());
        assertEquals(NUMERO_CONTAPF, resposta.getNumero_conta());
        assertEquals(AGENCIA, resposta.getAgencia());
    }

    @Test
    void quandoBuscarContaPorIdRetornaExeception() {
        try{
             contaService.buscarContaPorId(IDPF);
        }catch (NegocioException ex){
            assertEquals(NegocioException.class, ex.getClass());
            assertEquals("A conta informada não encontrada ou está inativa (ID: " + IDPF + ").", ex.getMessage());
        }


    }

    @Test
    void quandoBuscarContaPorNumeroContaRetornaSucesso() {
        when(_repositoryConta.buscarPorNumeroConta(anyInt())).thenReturn(contaPF);

        Conta resposta = contaService.buscarContaPorNumeroConta(NUMERO_CONTAPF);

        assertNotNull(resposta);
        assertEquals(Conta.class, resposta.getClass());
        assertEquals(IDPF, resposta.getId());
        assertEquals(NUMERO_CONTAPF, resposta.getNumero_conta());
        assertEquals(AGENCIA, resposta.getAgencia());
    }

    @Test
    void quandoAtualizarSaldoContaRetornaException() {
       when(_repositoryConta.buscarPorId(anyString())).thenReturn(contaPF);
        try{
            transacao.setValor(BigDecimal.valueOf(80000L));
            contaService.atualizarSaldo(transacao);
        }
        catch (NegocioException ex){
            assertEquals(NegocioException.class, ex.getClass());
            assertEquals("Saldo insuficiente para realizar transação", ex.getMessage());
        }
    }

    @Test
    void quandoAtualizarSaldoContaRetornaSucesso() {
        when(_repositoryConta.buscarPorId(anyString())).thenReturn(contaPF);

        var resposta = contaService.atualizarSaldo(transacao);

        assertNotNull(resposta);
        assertEquals(resposta.get(0).getSaldo(),contaPF.getSaldo() );
    }

    @Test
    void quandoInativarContaRetornaStatusFalse() {
        when(_repositoryConta.buscarPorId(anyString())).thenReturn(contaPF);
         contaService.inativarConta(IDPJ);
        assertFalse(contaPF.isStatus());
    }

    public void starContas(){
        contaPF = new Conta(IDPF, NUMERO_CONTAPF, AGENCIA, TIPO_OPERACAO, SALDO, STATUS) ;
        contaPJ = new Conta(IDPJ, NUMERO_CONTAPJ,AGENCIA, TipoOperacao.CONTACORRENTEPJ, BigDecimal.valueOf(5000),STATUS );
        transacao = new Transacao("0d43c3bf-1f4b-4ae6-9fcd-59eb1b508340", contaPF, contaPJ, TipoTransacao.TED, LocalDateTime.now(), BigDecimal.valueOf(150),"pagamento");
    }
}