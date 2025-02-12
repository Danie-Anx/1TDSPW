package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Veiculo;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public void create(Veiculo veiculo) {
        String sql = "INSERT INTO Veiculo (id_veiculo, vei_modelo, vei_marca, vei_ano, vei_quilometragem, vei_diagnostico, id_cliente, id_agendamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, veiculo.getIdVeiculo());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getMarca());
            stmt.setInt(4, veiculo.getAno());
            stmt.setInt(5, veiculo.getQuilometragem());
            stmt.setString(6, veiculo.getDiagnostico());
            stmt.setInt(7, veiculo.getIdCliente());
            stmt.setInt(8, veiculo.getIdAgendamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veiculo read(int id) {
        String sql = "SELECT * FROM Veiculo WHERE id_veiculo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("id_veiculo"));
                veiculo.setModelo(rs.getString("vei_modelo"));
                veiculo.setMarca(rs.getString("vei_marca"));
                veiculo.setAno(rs.getInt("vei_ano"));
                veiculo.setQuilometragem(rs.getInt("vei_quilometragem"));
                veiculo.setDiagnostico(rs.getString("vei_diagnostico"));
                veiculo.setIdCliente(rs.getInt("id_cliente"));
                veiculo.setIdAgendamento(rs.getInt("id_agendamento"));
                return veiculo;
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Veiculo veiculo) {
        if (read(veiculo.getIdVeiculo()) != null) {
            String sql = "UPDATE Veiculo SET vei_modelo = ?, vei_marca = ?, vei_ano = ?, vei_quilometragem = ?, vei_diagnostico = ?, id_cliente = ?, id_agendamento = ? WHERE id_veiculo = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, veiculo.getModelo());
                stmt.setString(2, veiculo.getMarca());
                stmt.setInt(3, veiculo.getAno());
                stmt.setInt(4, veiculo.getQuilometragem());
                stmt.setString(5, veiculo.getDiagnostico());
                stmt.setInt(6, veiculo.getIdCliente());
                stmt.setInt(7, veiculo.getIdAgendamento());
                stmt.setInt(8, veiculo.getIdVeiculo());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Veículo não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Veiculo WHERE id_veiculo = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Veículo não encontrado. Exclusão não realizada.");
        }
    }

    public List<Veiculo> findAll() {
        List<Veiculo> list = new ArrayList<>();

        String query = "SELECT * FROM Veiculo";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo item = new Veiculo();
                item.setIdVeiculo(rs.getInt("id_veiculo"));
                item.setModelo(rs.getString("vei_modelo"));
                item.setMarca(rs.getString("vei_marca"));
                item.setAno(rs.getInt("vei_ano"));
                item.setQuilometragem(rs.getInt("vei_quilometragem"));
                item.setDiagnostico(rs.getString("vei_diagnostico"));
                item.setIdCliente(rs.getInt("id_cliente"));
                item.setIdAgendamento(rs.getInt("id_agendamento"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
