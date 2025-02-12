package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Cliente;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void create(Cliente cliente) {
        String sql = "INSERT INTO Cliente (id_cliente, cli_nome, cli_telefone, cli_cpf, cli_email, cli_veiculo, cli_usuario, cli_senha, id_agendamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getVeiculo());
            stmt.setString(7, cliente.getUsuario());
            stmt.setString(8, cliente.getSenha());
            stmt.setInt(9, cliente.getIdAgendamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente read(int id) {
        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setTelefone(rs.getString("cli_telefone"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setVeiculo(rs.getString("cli_veiculo"));
                cliente.setUsuario(rs.getString("cli_usuario"));
                cliente.setSenha(rs.getString("cli_senha"));
                cliente.setIdAgendamento(rs.getInt("id_agendamento"));
                return cliente;
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Cliente cliente) {
        if (read(cliente.getIdCliente()) != null) {
            String sql = "UPDATE Cliente SET cli_nome = ?, cli_telefone = ?, cli_cpf = ?, cli_email = ?, cli_veiculo = ?, cli_usuario = ?, cli_senha = ?, id_agendamento = ? WHERE id_cliente = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getTelefone());
                stmt.setString(3, cliente.getCpf());
                stmt.setString(4, cliente.getEmail());
                stmt.setString(5, cliente.getVeiculo());
                stmt.setString(6, cliente.getUsuario());
                stmt.setString(7, cliente.getSenha());
                stmt.setInt(8, cliente.getIdAgendamento());
                stmt.setInt(9, cliente.getIdCliente());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cliente não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cliente não encontrado. Exclusão não realizada.");
        }
    }

    public List<Cliente> findAll() {
        List<Cliente> list = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente item = new Cliente();
                item.setIdCliente(rs.getInt("id_cliente"));
                item.setNome(rs.getString("cli_nome"));
                item.setTelefone(rs.getString("cli_telefone"));
                item.setCpf(rs.getString("cli_cpf"));
                item.setEmail(rs.getString("cli_email"));
                item.setVeiculo(rs.getString("cli_veiculo"));
                item.setUsuario(rs.getString("cli_usuario"));
                item.setSenha(rs.getString("cli_senha"));
                item.setIdAgendamento(rs.getInt("id_agendamento"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
