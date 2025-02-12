package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Orcamento;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO {

    public void create(Orcamento orcamento) {
        String sql = "INSERT INTO Orcamento (id_orcamento, orc_descricao, orc_valor, orc_tempoEstimado, orc_veiculo, id_cliente, id_agendamento, id_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orcamento.getIdOrcamento());
            stmt.setString(2, orcamento.getDescricao());
            stmt.setInt(3, orcamento.getValor());
            stmt.setDate(4, Date.valueOf(orcamento.getTempoEstimado()));
            stmt.setInt(5, orcamento.getIdVeiculo());
            stmt.setInt(6, orcamento.getIdCliente());
            stmt.setInt(7, orcamento.getIdAgendamento());
            stmt.setInt(8, orcamento.getIdPagamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Orcamento read(int id) {
        String sql = "SELECT * FROM Orcamento WHERE id_orcamento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Orcamento orcamento = new Orcamento();
                orcamento.setIdOrcamento(rs.getInt("id_orcamento"));
                orcamento.setDescricao(rs.getString("orc_descricao"));
                orcamento.setValor(rs.getInt("orc_valor"));
                orcamento.setTempoEstimado(rs.getDate("orc_tempoEstimado").toLocalDate());
                orcamento.setIdVeiculo(rs.getInt("orc_veiculo"));
                orcamento.setIdCliente(rs.getInt("id_cliente"));
                orcamento.setIdAgendamento(rs.getInt("id_agendamento"));
                orcamento.setIdPagamento(rs.getInt("id_pagamento"));
                return orcamento;
            } else {
                System.out.println("Orçamento não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Orcamento orcamento) {
        if (read(orcamento.getIdOrcamento()) != null) {
            String sql = "UPDATE Orcamento SET orc_descricao = ?, orc_valor = ?, orc_tempoEstimado = ?, orc_veiculo = ?, id_cliente = ?, id_agendamento = ?, id_pagamento = ? WHERE id_orcamento = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, orcamento.getDescricao());
                stmt.setInt(2, orcamento.getValor());
                stmt.setDate(3, Date.valueOf(orcamento.getTempoEstimado()));
                stmt.setInt(4, orcamento.getIdVeiculo());
                stmt.setInt(5, orcamento.getIdCliente());
                stmt.setInt(6, orcamento.getIdAgendamento());
                stmt.setInt(7, orcamento.getIdPagamento());
                stmt.setInt(8, orcamento.getIdOrcamento());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Orçamento não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Orcamento WHERE id_orcamento = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Orçamento não encontrado. Exclusão não realizada.");
        }
    }

    public List<Orcamento> findAll() {
        List<Orcamento> list = new ArrayList<>();

        String query = "SELECT * FROM Orcamento";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Orcamento item = new Orcamento();
                item.setIdOrcamento(rs.getInt("id_orcamento"));
                item.setDescricao(rs.getString("orc_descricao"));
                item.setValor(rs.getInt("orc_valor"));

                Date tempoEstimadoDate = rs.getDate("orc_tempoEstimado");
                if (tempoEstimadoDate != null) {
                    item.setTempoEstimado(tempoEstimadoDate.toLocalDate());
                }

                item.setIdVeiculo(rs.getInt("orc_veiculo"));
                item.setIdCliente(rs.getInt("id_cliente"));
                item.setIdAgendamento(rs.getInt("id_agendamento"));
                item.setIdPagamento(rs.getInt("id_pagamento"));

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
