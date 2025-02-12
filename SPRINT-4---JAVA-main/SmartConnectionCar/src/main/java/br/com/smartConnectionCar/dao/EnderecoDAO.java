package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Endereco;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    public void create(Endereco endereco) {
        String sql = "INSERT INTO Endereco (id_endereco, end_cep, end_rua, end_numero, end_pais, end_cidade, end_bairro, id_cliente, id_agendamento, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, endereco.getIdEndereco());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getRua());
            stmt.setInt(4, endereco.getNumero());
            stmt.setString(5, endereco.getPais());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getBairro());
            stmt.setInt(8, endereco.getIdCliente());
            stmt.setInt(9, endereco.getIdAgendamento());
            stmt.setInt(10, endereco.getIdOficina());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Endereco read(int id) {
        String sql = "SELECT * FROM Endereco WHERE id_endereco = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setRua(rs.getString("end_rua"));
                endereco.setNumero(rs.getInt("end_numero"));
                endereco.setPais(rs.getString("end_pais"));
                endereco.setCidade(rs.getString("end_cidade"));
                endereco.setBairro(rs.getString("end_bairro"));
                endereco.setIdCliente(rs.getInt("id_cliente"));
                endereco.setIdAgendamento(rs.getInt("id_agendamento"));
                endereco.setIdOficina(rs.getInt("id_oficina"));
                return endereco;
            } else {
                System.out.println("Endereço não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Endereco endereco) {
        if (read(endereco.getIdEndereco()) != null) {
            String sql = "UPDATE Endereco SET end_cep = ?, end_rua = ?, end_numero = ?, end_pais = ?, end_cidade = ?, end_bairro = ?, id_cliente = ?, id_agendamento = ?, id_oficina = ? WHERE id_endereco = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, endereco.getCep());
                stmt.setString(2, endereco.getRua());
                stmt.setInt(3, endereco.getNumero());
                stmt.setString(4, endereco.getPais());
                stmt.setString(5, endereco.getCidade());
                stmt.setString(6, endereco.getBairro());
                stmt.setInt(7, endereco.getIdCliente());
                stmt.setInt(8, endereco.getIdAgendamento());
                stmt.setInt(9, endereco.getIdOficina());
                stmt.setInt(10, endereco.getIdEndereco());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Endereço não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Endereco WHERE id_endereco = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Endereço não encontrado. Exclusão não realizada.");
        }
    }

    public List<Endereco> findAll() {
        List<Endereco> list = new ArrayList<>();
        String query = "SELECT * FROM Endereco";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Endereco item = new Endereco();
                item.setIdEndereco(rs.getInt("id_endereco"));
                item.setCep(rs.getString("end_cep"));
                item.setRua(rs.getString("end_rua"));
                item.setNumero(rs.getInt("end_numero"));
                item.setPais(rs.getString("end_pais"));
                item.setCidade(rs.getString("end_cidade"));
                item.setBairro(rs.getString("end_bairro"));
                item.setIdCliente(rs.getInt("id_cliente"));
                item.setIdAgendamento(rs.getInt("id_agendamento"));
                item.setIdOficina(rs.getInt("id_oficina"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
