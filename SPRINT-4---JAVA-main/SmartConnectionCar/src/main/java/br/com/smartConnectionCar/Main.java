package br.com.smartConnectionCar;

import br.com.smartConnectionCar.dao.*;
import br.com.smartConnectionCar.model.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;

public class Main {
    // Defina a URI base da API
    public static final String BASE_URI = "http://localhost:8080/api/";

    // Método para iniciar o servidor Grizzly
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("br.com.smartConnectionCar");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        // Inicie o servidor
        final HttpServer server = startServer();
        System.out.println(String.format("Aplicação Jersey iniciada com o endpoint disponível em %s", BASE_URI));
        System.out.println("Pressione CTRL+C para parar o servidor...");

        try {
            server.start();

            // Inicialização dos DAOs
            OficinaDAO oficinaDAO = new OficinaDAO();
            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            PagamentoDAO pagamentoDAO = new PagamentoDAO();
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();

            System.out.println("### Testando operações CRUD ###");

            // Oficina - CRUD
            Oficina oficina = new Oficina(1, "Auto Center ABC", "1122334455", "abc@oficinas.com");
            oficinaDAO.create(oficina);
            System.out.println("Oficina Criada: " + oficinaDAO.read(1));
            oficina.setNome("Auto Center XYZ");
            oficinaDAO.update(oficina);
            System.out.println("Oficina Atualizada: " + oficinaDAO.read(1));

            // Agendamentos - CRUD
            Agendamento agendamento = new Agendamento(1, LocalDate.now(), "Revisão Completa");
            agendamentoDAO.create(agendamento);
            System.out.println("Agendamento Criado: " + agendamentoDAO.read(1));
            agendamento.setServico("Troca de Óleo");
            agendamentoDAO.update(agendamento);
            System.out.println("Agendamento Atualizado: " + agendamentoDAO.read(1));

            // Cliente - CRUD
            Cliente cliente = new Cliente(1, "Maria Souza", "1188776655", "09876543210", "maria@cliente.com", "Carro A", "mariasouza", "senha123", agendamento.getIdAgendamento());
            clienteDAO.create(cliente);
            System.out.println("Cliente Criado: " + clienteDAO.read(1));
            cliente.setNome("Maria Souza Silva");
            clienteDAO.update(cliente);
            System.out.println("Cliente Atualizado: " + clienteDAO.read(1));

            // Pagamentos - CRUD
            Pagamento pagamento = new Pagamento(1, 500, LocalDate.now(), cliente.getIdCliente(), agendamento.getIdAgendamento());
            pagamentoDAO.create(pagamento);
            System.out.println("Pagamento Criado: " + pagamentoDAO.read(1));
            pagamento.setValor(600);
            pagamentoDAO.update(pagamento);
            System.out.println("Pagamento Atualizado: " + pagamentoDAO.read(1));

            // Veiculo - CRUD
            Veiculo veiculo = new Veiculo(1, "Civic", "Honda", 2019, 20000, "Em boas condições", cliente.getIdCliente(), agendamento.getIdAgendamento());
            veiculoDAO.create(veiculo);
            System.out.println("Veículo Criado: " + veiculoDAO.read(1));
            veiculo.setModelo("Civic Sport");
            veiculoDAO.update(veiculo);
            System.out.println("Veículo Atualizado: " + veiculoDAO.read(1));

            // Orcamento - CRUD
            Orcamento orcamento = new Orcamento(1, "Troca de óleo e filtros", 150, LocalDate.now().plusDays(2), veiculo.getIdVeiculo(), cliente.getIdCliente(), agendamento.getIdAgendamento(), pagamento.getIdPagamento());
            orcamentoDAO.create(orcamento);
            System.out.println("Orçamento Criado: " + orcamentoDAO.read(1));
            orcamento.setValor(200);
            orcamentoDAO.update(orcamento);
            System.out.println("Orçamento Atualizado: " + orcamentoDAO.read(1));

            // Endereço - CRUD
            Endereco endereco = new Endereco(1, "12345-678", "Rua A", 123, "Brasil", "São Paulo", "Centro", cliente.getIdCliente(), agendamento.getIdAgendamento(), oficina.getIdOficina());
            enderecoDAO.create(endereco);
            System.out.println("Endereço Criado: " + enderecoDAO.read(1));
            endereco.setCidade("Rio de Janeiro");
            enderecoDAO.update(endereco);
            System.out.println("Endereço Atualizado: " + enderecoDAO.read(1));

            // Funcionario - CRUD
            Funcionario funcionario = new Funcionario(1, "João da Silva", "1199887766", "joao@empresa.com", "12345678901", LocalDate.now(), "Mecânico", "Chefe", 3000, orcamento.getIdOrcamento(), oficina.getIdOficina());
            funcionarioDAO.create(funcionario);
            System.out.println("Funcionário Criado: " + funcionarioDAO.read(1));
            funcionario.setCargo("Supervisor");
            funcionarioDAO.update(funcionario);
            System.out.println("Funcionário Atualizado: " + funcionarioDAO.read(1));

            // Diagnostico - CRUD
            Diagnostico diagnostico = new Diagnostico(1, veiculo.getIdVeiculo(), "Falha no motor", "Vazamento de óleo detectado", orcamento.getIdOrcamento(), funcionario.getIdFuncionario(), oficina.getIdOficina());
            diagnosticoDAO.create(diagnostico);
            System.out.println("Diagnóstico Criado: " + diagnosticoDAO.read(1));
            diagnostico.setProblema("Troca de Filtro de Óleo");
            diagnosticoDAO.update(diagnostico);
            System.out.println("Diagnóstico Atualizado: " + diagnosticoDAO.read(1));

            // Exclusões em ordem reversa de dependências
            diagnosticoDAO.delete(1);
            System.out.println("Diagnóstico Excluído: " + diagnosticoDAO.read(1));
            funcionarioDAO.delete(1);
            System.out.println("Funcionário Excluído: " + funcionarioDAO.read(1));
            enderecoDAO.delete(1);
            System.out.println("Endereço Excluído: " + enderecoDAO.read(1));
            orcamentoDAO.delete(1);
            System.out.println("Orçamento Excluído: " + orcamentoDAO.read(1));
            veiculoDAO.delete(1);
            System.out.println("Veículo Excluído: " + veiculoDAO.read(1));
            pagamentoDAO.delete(1);
            System.out.println("Pagamento Excluído: " + pagamentoDAO.read(1));
            clienteDAO.delete(1);
            System.out.println("Cliente Excluído: " + clienteDAO.read(1));
            agendamentoDAO.delete(1);
            System.out.println("Agendamento Excluído: " + agendamentoDAO.read(1));
            oficinaDAO.delete(1);
            System.out.println("Oficina Excluída: " + oficinaDAO.read(1));

            System.out.println("### Testes de operações CRUD concluídos ###");

            // Manter o servidor ativo
            Thread.currentThread().join();
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Servidor interrompido.");
        } finally {
            // Encerrar o servidor ao finalizar
            server.shutdownNow();
        }
    }
}
