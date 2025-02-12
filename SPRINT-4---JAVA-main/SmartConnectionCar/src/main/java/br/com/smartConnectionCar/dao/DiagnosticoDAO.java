package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Diagnostico;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAO {

    public void create(Diagnostico diagnostico) {
        String sql = "INSERT INTO Diagnostico (id_diagnostico, dia_veiculo, dia_problema, dia_descricao, dia_orcamento, id_funcionario, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, diagnostico.getIdDiagnostico());
            stmt.setInt(2, diagnostico.getIdVeiculo());
            stmt.setString(3, diagnostico.getProblema());
            stmt.setString(4, diagnostico.getDescricao());
            stmt.setInt(5, diagnostico.getIdOrcamento());
            stmt.setInt(6, diagnostico.getIdFuncionario());
            stmt.setInt(7, diagnostico.getIdOficina());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Diagnostico read(int id) {
        String sql = "SELECT * FROM Diagnostico WHERE id_diagnostico = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setIdDiagnostico(rs.getInt("id_diagnostico"));
                diagnostico.setIdVeiculo(rs.getInt("dia_veiculo"));
                diagnostico.setProblema(rs.getString("dia_problema"));
                diagnostico.setDescricao(rs.getString("dia_descricao"));
                diagnostico.setIdOrcamento(rs.getInt("dia_orcamento"));
                diagnostico.setIdFuncionario(rs.getInt("id_funcionario"));
                diagnostico.setIdOficina(rs.getInt("id_oficina"));
                return diagnostico;
            } else {
                System.out.println("Diagnóstico não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Diagnostico diagnostico) {
        if (read(diagnostico.getIdDiagnostico()) != null) {
            String sql = "UPDATE Diagnostico SET dia_veiculo = ?, dia_problema = ?, dia_descricao = ?, dia_orcamento = ?, id_funcionario = ?, id_oficina = ? WHERE id_diagnostico = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, diagnostico.getIdVeiculo());
                stmt.setString(2, diagnostico.getProblema());
                stmt.setString(3, diagnostico.getDescricao());
                stmt.setInt(4, diagnostico.getIdOrcamento());
                stmt.setInt(5, diagnostico.getIdFuncionario());
                stmt.setInt(6, diagnostico.getIdOficina());
                stmt.setInt(7, diagnostico.getIdDiagnostico());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Diagnóstico não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Diagnostico WHERE id_diagnostico = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Diagnóstico não encontrado. Exclusão não realizada.");
        }
    }

    public List<Diagnostico> findAll() {
        List<Diagnostico> list = new ArrayList<>();
        String query = "SELECT * FROM Diagnostico";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Diagnostico item = new Diagnostico();
                item.setIdDiagnostico(rs.getInt("id_diagnostico"));
                item.setIdVeiculo(rs.getInt("dia_veiculo"));
                item.setProblema(rs.getString("dia_problema"));
                item.setDescricao(rs.getString("dia_descricao"));
                item.setIdOrcamento(rs.getInt("dia_orcamento"));
                item.setIdFuncionario(rs.getInt("id_funcionario"));
                item.setIdOficina(rs.getInt("id_oficina"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
