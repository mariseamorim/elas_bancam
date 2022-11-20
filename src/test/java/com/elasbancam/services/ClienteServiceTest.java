package com.elasbancam.services;

import com.elasbancam.controllers.mappers.ClienteMapper;
import com.elasbancam.enums.Genero;
import com.elasbancam.enums.Regiao;
import com.elasbancam.enums.TipoOperacao;
import com.elasbancam.exceptions.NegocioException;
import com.elasbancam.models.Conta;
import com.elasbancam.models.Endereco;
import com.elasbancam.models.PessoaFisica;
import com.elasbancam.models.PessoaJuridica;
import com.elasbancam.repositories.PessoaFisicaRepository;
import com.elasbancam.repositories.PessoaJuridicaRepository;
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

class ClienteServiceTest {
    public static final String IDCONTAPF = "fbc24f8d-d2f2-4d69-aa3d-255103bc67ed";
    public static final int NUMERO_CONTAPF = 101517;
    public static final int AGENCIA = 123;
    public static final TipoOperacao TIPO_OPERACAOPF = TipoOperacao.CONTACORRENTEPF;
    public static final BigDecimal SALDO = BigDecimal.valueOf(1000);
    public static final boolean STATUS = true;
    public static final long IDENDERECO = 1L;
    public static final String CEP = "89224150";
    public static final String RUA = "Rua dois";
    public static final int NUMERO = 35;
    public static final String COMPLEMENTO = "casa";
    public static final String BAIRRO = "Iririu";
    public static final String CIDADE = "Rio Negro";
    public static final String UF = "SC";
    public static final Regiao REGIAO = Regiao.S;
    public static final String NOME = "Maria Tereza";
    public static final String EMAIL = "maria@teste.com";
    public static final String TELEFONE = "4732248989";
    public static final String CELULAR = "47997320224";
    public static final String CPF = "07142993815";
    public static final String RG = "454568";
    public static final String NOME_MAE = "Tereza da Silva";
    public static final Genero GENERO = Genero.FEMININO;
    public static final LocalDate DT_NASCIMENTO = LocalDate.parse("2002-11-15");
    public static final long IDPF = 1L;
    public static final LocalDateTime CRIADO_EM = LocalDateTime.now();
    public static final LocalDateTime ALTERADO_EM = LocalDateTime.now();
    public static final int NUMERO_CONTAPJ = 123456;
    public static final TipoOperacao TIPO_OPERACAOPJ = TipoOperacao.CONTACORRENTEPJ;
    public static final String NOMEPJ = "Elas Bancam Ltda";
    public static final String INSCRICAO_ESTADUAL = "373310810939";
    public static final String CNPJ = "11614183000151";
    public static final long IDPJ = 99L;

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private PessoaFisicaRepository _repositoryPessoaFisica;

    @Mock
    private PessoaJuridicaRepository _repositoryPessoaJuridica;

    @Mock
    private ContaService contaService;

    @Mock
    private ClienteMapper clienteMapper;

    private PessoaFisica pessoaFisica;
    private Optional<PessoaFisica> optionalPessoaFisica;

    private PessoaJuridica pessoaJuridica;
    private Optional<PessoaJuridica> optionalPessoaJuridica;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void quandoCadastrarPessoaFisicaRetornaSucesso() {
        when(_repositoryPessoaFisica.save(any())).thenReturn(pessoaFisica);

        PessoaFisica resposta = clienteService.cadastrarPessoaFisica(pessoaFisica);

        assertNotNull(resposta);
        assertEquals(PessoaFisica.class, resposta.getClass());
        assertEquals(IDPF, resposta.getId());
        assertEquals(NOME, resposta.getNome());
        assertEquals(EMAIL, resposta.getEmail());
        assertEquals(CPF, resposta.getCpf());
    }

    @Test
    void quandoCadastrarcadastrarPessoaJuridicaRetornaSucesso() {
        when(_repositoryPessoaJuridica.save(any())).thenReturn(pessoaJuridica);

        PessoaJuridica resposta = clienteService.cadastrarPessoaJuridica(pessoaJuridica);

        assertNotNull(resposta);
        assertEquals(PessoaJuridica.class, resposta.getClass());
        assertEquals(IDPJ, resposta.getId());
        assertEquals(NOMEPJ, resposta.getNome());
        assertEquals(EMAIL, resposta.getEmail());
        assertEquals(CNPJ, resposta.getCnpj());
    }

    @Test
    void quandolistarTodosClientesRenoarUmaListaDeClientes() {
        when(_repositoryPessoaFisica.listarTodosPF()).thenReturn(List.of(pessoaFisica));
        when(_repositoryPessoaJuridica.listarTodosPJ()).thenReturn(List.of(pessoaJuridica));

        List<Object>  resposta = clienteService.listarTodosClientes();
        System.out.println(resposta);
        assertNotNull(resposta);
        assertEquals(2, resposta.size());
    }

    @Test
    void quandoBuscarPessoaFisicaPorIdEntaoRetornaUmaInstanciaPF() {
        when(_repositoryPessoaFisica.buscarPfPorId(anyLong())).thenReturn(optionalPessoaFisica);

        Optional<PessoaFisica> resposta = clienteService.listarPessoaFisicaPorId(IDPF);
        assertNotNull(resposta);
        assertEquals(PessoaFisica.class, resposta.get().getClass());
        assertEquals(IDPF, resposta.get().getId());
        assertEquals(NOME, resposta.get().getNome());
        assertEquals(EMAIL, resposta.get().getEmail());

    }

    @Test
    void quandoBuscarPessoaJuridicaPorIdEntaoRetornaUmaInstanciaPJ() {
        when(_repositoryPessoaJuridica.buscarPjPorId(anyLong())).thenReturn(optionalPessoaJuridica);

        Optional<PessoaJuridica> resposta = clienteService.listarPessoaJuridicaPorId(IDPJ);
        assertNotNull(resposta);
        assertEquals(PessoaJuridica.class, resposta.get().getClass());
        assertEquals(IDPJ, resposta.get().getId());
        assertEquals(NOMEPJ, resposta.get().getNome());
        assertEquals(CNPJ, resposta.get().getCnpj());
    }

    @Test
    void quandoBuscarClientePorIdEntaoRetornaException() {
        try{
            clienteService.listarClientePorId(IDPF);
        } catch (Exception ex) {
            assertEquals(NegocioException.class, ex.getClass());
            assertEquals("Cliente não encontrado ou inativo (ID:  " + IDPF + ").", ex.getMessage());
        }
    }

    @Test
    void verificarSeClienteExiste() {
    }

    @Test
    void listarClientePorId() {
    }

    @Test
    void atualizarPessoaFisica() {
    }

    @Test
    void atualizarPessoaJuridica() {
    }

    @Test
    void inativarPessoaFisica() {
    }

    @Test
    void inativarPessoaJuridica() {
    }

    private void startCliente() {
        Conta contapf = new Conta(IDCONTAPF, NUMERO_CONTAPF, AGENCIA, TIPO_OPERACAOPF, SALDO, STATUS);
        Conta contapj = new Conta("8954dc61-740a-4ae5-a477-b0e3b110dae4", NUMERO_CONTAPJ, AGENCIA, TIPO_OPERACAOPJ, SALDO, STATUS);
        Endereco endereco = new Endereco(IDENDERECO, CEP, RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, UF, REGIAO);
        pessoaFisica = new PessoaFisica(IDPF, NOME, EMAIL, TELEFONE, CELULAR, endereco, contapf, CRIADO_EM, ALTERADO_EM, STATUS, CPF, RG, DT_NASCIMENTO, NOME_MAE, GENERO);

        optionalPessoaFisica = Optional.of(new PessoaFisica(IDPF, NOME, EMAIL, TELEFONE, CELULAR, endereco, contapf, CRIADO_EM, ALTERADO_EM, STATUS, CPF, RG, DT_NASCIMENTO, NOME_MAE, GENERO));

        pessoaJuridica = new PessoaJuridica(IDPJ, NOMEPJ, EMAIL, TELEFONE,CELULAR,endereco,contapj,CRIADO_EM,ALTERADO_EM,STATUS,NOMEPJ, INSCRICAO_ESTADUAL, CNPJ,NOME);

        optionalPessoaJuridica = Optional.of(new PessoaJuridica(IDPJ, NOMEPJ, EMAIL, TELEFONE,CELULAR,endereco,contapj,CRIADO_EM,ALTERADO_EM,STATUS,NOMEPJ, INSCRICAO_ESTADUAL, CNPJ,NOME));

    }
}