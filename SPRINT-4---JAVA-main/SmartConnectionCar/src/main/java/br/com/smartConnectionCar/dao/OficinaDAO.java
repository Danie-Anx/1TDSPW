package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Oficina;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaDAO {

    public void create(Oficina oficina) {
        String sql = "INSERT INTO Oficina (id_oficina, ofc_nome, ofc_telefone, ofc_email) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oficina.getIdOficina());
            stmt.setString(2, oficina.getNome());
            stmt.setString(3, oficina.getTelefone());
            stmt.setString(4, oficina.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Oficina read(int id) {
        String sql = "SELECT * FROM Oficina WHERE id_oficina = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Oficina oficina = new Oficina();
                oficina.setIdOficina(rs.getInt("id_oficina"));
                oficina.setNome(rs.getString("ofc_nome"));
                oficina.setTelefone(rs.getString("ofc_telefone"));
                oficina.setEmail(rs.getString("ofc_email"));
                return oficina;
            } else {
                System.out.println("Oficina não encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Oficina oficina) {
        if (read(oficina.getIdOficina()) != null) {
            String sql = "UPDATE Oficina SET ofc_nome = ?, ofc_telefone = ?, ofc_email = ? WHERE id_oficina = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, oficina.getNome());
                stmt.setString(2, oficina.getTelefone());
                stmt.setString(3, oficina.getEmail());
                stmt.setInt(4, oficina.getIdOficina());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Oficina não encontrada. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Oficina WHERE id_oficina = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Oficina não encontrada. Exclusão não realizada.");
        }
    }

    public List<Oficina> findAll() {
        List<Oficina> list = new ArrayList<>();
        String query = "SELECT * FROM Oficina";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Oficina item = new Oficina();
                item.setIdOficina(rs.getInt("id_oficina"));
                item.setNome(rs.getString("ofc_nome"));
                item.setTelefone(rs.getString("ofc_telefone"));
                item.setEmail(rs.getString("ofc_email"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
