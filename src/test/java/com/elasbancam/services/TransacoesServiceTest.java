package com.elasbancam.services;

import com.elasbancam.enums.TipoOperacao;
import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Transacao;
import com.elasbancam.repositories.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TransacoesServiceTest {
    public static final String IDPF = "cdd98c9d-4076-4004-b32f-9d3aa6ed7753";
    public static final int NUMERO_CONTAPF = 58596;
    public static final int AGENCIA = 1423;
    public static final TipoOperacao TIPO_OPERACAO = TipoOperacao.CONTACORRENTEPF;
    public static final BigDecimal SALDO = BigDecimal.valueOf(1000);
    public static final boolean STATUS = true;
    public static final int NUMERO_CONTAPJ = 141516;
    public static final String IDPJ = "7df09933-7eb1-4fb9-8261-46a8ff71a506";
    public static final String ID = "0d43c3bf-1f4b-4ae6-9fcd-59eb1b508340";
    public static final TipoTransacao TIPO_TRANSACAO = TipoTransacao.TED;
    public static final BigDecimal VALOR = BigDecimal.valueOf(150);
    public static final String DESCRICAO = "pagamento";
    public static final LocalDateTime DATA = LocalDateTime.now();
    public static final LocalDate DATA_INICIAL = LocalDate.now().plusMonths(1);
    public static final LocalDate DATA_FINAL = LocalDate.now().plusMonths(1);
    @Mock
    private TransacaoRepository _repositoryTransacao;
    @Mock
    private ContaService contaService;
    @InjectMocks
    private TransacoesService transacoesService;

    private Conta contaPF;

    private Conta contaPJ;
    private Transacao transacao;

    private Optional<Transacao> optionalTransacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starTransacao();
    }

    @Test
    void quandoEfetuarTransacaoRetornaSucesso() {
        when(_repositoryTransacao.save(any())).thenReturn(transacao);
        when(contaService.atualizarSaldo(transacao)).thenReturn(List.of(contaPF, contaPJ));


        Transacao resposta = transacoesService.efetuarTransacao(transacao);

        assertNotNull(resposta);
        assertEquals(Transacao.class, resposta.getClass());
        assertEquals(ID, resposta.getId());
        assertEquals(TIPO_TRANSACAO, resposta.getTipo_transacao());
        assertEquals(VALOR, resposta.getValor());

    }

    @Test
    void quandoBuscarTransacaoPorIdRetornaIstanciaTransacao() {
        when(_repositoryTransacao.findById(anyString())).thenReturn(optionalTransacao);

        Transacao resposta = transacoesService.buscarTransacaoPorId(ID);

        assertNotNull(resposta);
        assertEquals(Transacao.class, resposta.getClass());
        assertEquals(ID, resposta.getId());
        assertEquals(TIPO_TRANSACAO, resposta.getTipo_transacao());
        assertEquals(VALOR, resposta.getValor());

    }

    @Test
    void quandoBuscarTransacaoPorTipoRetornaLista() {
        when(_repositoryTransacao.listarTransacoesPorTipo(anyString())).thenReturn(List.of(transacao, transacao));

        List<Transacao> resposta = transacoesService.listarTransacoesPorTipo(TIPO_TRANSACAO);

        assertNotNull(resposta);
        assertEquals(2, resposta.size());

    }

    @Test
    void quandoBuscarTransacaoPorPeriodoRetornarException() {
        try {
            transacoesService.listarTransacoesPorPeriodo(DATA_INICIAL, DATA_FINAL);
        } catch (NegocioException ex) {
            assertEquals(NegocioException.class, ex.getClass());
            assertEquals("Nenhuma transação encontrada entre " + DATA_INICIAL + " e " + DATA_FINAL, ex.getMessage());
        }

    }

    @Test
    void quandoBuscarTransacaoPorIdDaContaRetornaSucesso() {
        when(_repositoryTransacao.listarTransacoesPorConta(anyString())).thenReturn(List.of(transacao, transacao, transacao));

        List<Transacao> resposta = transacoesService.listarTransacoesPorIdConta(ID);

        assertNotNull(resposta);
        assertEquals(3, resposta.size());

    }

    public void starTransacao() {
        contaPF = new Conta(IDPF, NUMERO_CONTAPF, AGENCIA, TIPO_OPERACAO, SALDO, STATUS);
        contaPJ = new Conta(IDPJ, NUMERO_CONTAPJ, AGENCIA, TipoOperacao.CONTACORRENTEPJ, BigDecimal.valueOf(5000), STATUS);
        transacao = new Transacao(ID, contaPF, contaPJ, TIPO_TRANSACAO, DATA, VALOR, DESCRICAO);
        optionalTransacao = Optional.of(new Transacao(ID, contaPF, contaPJ, TIPO_TRANSACAO, DATA, VALOR, DESCRICAO));
    }
}