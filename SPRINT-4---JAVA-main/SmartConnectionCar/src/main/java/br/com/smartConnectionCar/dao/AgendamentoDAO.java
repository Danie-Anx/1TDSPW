package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Agendamento;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public void create(Agendamento agendamento) {
        String sql = "INSERT INTO Agendamentos (id_agendamento, age_data, age_servico) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getIdAgendamento());
            stmt.setDate(2, Date.valueOf(agendamento.getData()));
            stmt.setString(3, agendamento.getServico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Agendamento read(int id) {
        String sql = "SELECT * FROM Agendamentos WHERE id_agendamento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setIdAgendamento(rs.getInt("id_agendamento"));
                agendamento.setData(rs.getDate("age_data").toLocalDate());
                agendamento.setServico(rs.getString("age_servico"));
                return agendamento;
            } else {
                System.out.println("Agendamento não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Agendamento agendamento) {
        if (read(agendamento.getIdAgendamento()) != null) {
            String sql = "UPDATE Agendamentos SET age_data = ?, age_servico = ? WHERE id_agendamento = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDate(1, Date.valueOf(agendamento.getData()));
                stmt.setString(2, agendamento.getServico());
                stmt.setInt(3, agendamento.getIdAgendamento());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Agendamento não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Agendamentos WHERE id_agendamento = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Agendamento não encontrado. Exclusão não realizada.");
        }
    }

    public List<Agendamento> findAll() {
        List<Agendamento> list = new ArrayList<>();
        String query = "SELECT * FROM Agendamentos";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Agendamento item = new Agendamento();
                item.setIdAgendamento(rs.getInt("id_agendamento"));
                item.setData(rs.getDate("age_data").toLocalDate());
                item.setServico(rs.getString("age_servico"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
