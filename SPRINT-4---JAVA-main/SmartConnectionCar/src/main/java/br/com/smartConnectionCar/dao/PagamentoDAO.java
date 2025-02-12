package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Pagamento;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void create(Pagamento pagamento) {
        String sql = "INSERT INTO Pagamentos (id_pagamento, pag_valor, pag_data, id_cliente, id_agendamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getIdPagamento());
            stmt.setInt(2, pagamento.getValor());
            stmt.setDate(3, Date.valueOf(pagamento.getData()));
            stmt.setInt(4, pagamento.getIdCliente());
            stmt.setInt(5, pagamento.getIdAgendamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pagamento read(int id) {
        String sql = "SELECT * FROM Pagamentos WHERE id_pagamento = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(rs.getInt("id_pagamento"));
                pagamento.setValor(rs.getInt("pag_valor"));
                pagamento.setData(rs.getDate("pag_data").toLocalDate());
                pagamento.setIdCliente(rs.getInt("id_cliente"));
                pagamento.setIdAgendamento(rs.getInt("id_agendamento"));
                return pagamento;
            } else {
                System.out.println("Pagamento não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Pagamento pagamento) {
        if (read(pagamento.getIdPagamento()) != null) {
            String sql = "UPDATE Pagamentos SET pag_valor = ?, pag_data = ?, id_cliente = ?, id_agendamento = ? WHERE id_pagamento = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, pagamento.getValor());
                stmt.setDate(2, Date.valueOf(pagamento.getData()));
                stmt.setInt(3, pagamento.getIdCliente());
                stmt.setInt(4, pagamento.getIdAgendamento());
                stmt.setInt(5, pagamento.getIdPagamento());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pagamento não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Pagamentos WHERE id_pagamento = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pagamento não encontrado. Exclusão não realizada.");
        }
    }

    public List<Pagamento> findAll() {
        List<Pagamento> list = new ArrayList<>();

        String query = "SELECT * FROM Pagamentos";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagamento item = new Pagamento();
                item.setIdPagamento(rs.getInt("id_pagamento"));
                item.setValor(rs.getInt("pag_valor"));

                Date dataDate = rs.getDate("pag_data");
                if (dataDate != null) {
                    item.setData(dataDate.toLocalDate());
                }

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
