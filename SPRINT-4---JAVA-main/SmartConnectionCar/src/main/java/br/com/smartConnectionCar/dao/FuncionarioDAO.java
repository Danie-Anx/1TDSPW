package br.com.smartConnectionCar.dao;

import br.com.smartConnectionCar.model.Funcionario;
import br.com.smartConnectionCar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void create(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario (id_funcionario, fun_nome, fun_telefone, fun_email, fun_cpf, fun_dataContratacao, fun_setor, fun_cargo, fun_salario, id_orcamento, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionario.getIdFuncionario());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getCpf());
            stmt.setDate(6, Date.valueOf(funcionario.getDataContratacao()));
            stmt.setString(7, funcionario.getSetor());
            stmt.setString(8, funcionario.getCargo());
            stmt.setInt(9, funcionario.getSalario());
            stmt.setInt(10, funcionario.getIdOrcamento());
            stmt.setInt(11, funcionario.getIdOficina());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Funcionario read(int id) {
        String sql = "SELECT * FROM Funcionario WHERE id_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setTelefone(rs.getString("fun_telefone"));
                funcionario.setEmail(rs.getString("fun_email"));
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setDataContratacao(rs.getDate("fun_dataContratacao").toLocalDate());
                funcionario.setSetor(rs.getString("fun_setor"));
                funcionario.setCargo(rs.getString("fun_cargo"));
                funcionario.setSalario(rs.getInt("fun_salario"));
                funcionario.setIdOrcamento(rs.getInt("id_orcamento"));
                funcionario.setIdOficina(rs.getInt("id_oficina"));
                return funcionario;
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Funcionario funcionario) {
        if (read(funcionario.getIdFuncionario()) != null) {
            String sql = "UPDATE Funcionario SET fun_nome = ?, fun_telefone = ?, fun_email = ?, fun_cpf = ?, fun_dataContratacao = ?, fun_setor = ?, fun_cargo = ?, fun_salario = ?, id_orcamento = ?, id_oficina = ? WHERE id_funcionario = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getTelefone());
                stmt.setString(3, funcionario.getEmail());
                stmt.setString(4, funcionario.getCpf());
                stmt.setDate(5, Date.valueOf(funcionario.getDataContratacao()));
                stmt.setString(6, funcionario.getSetor());
                stmt.setString(7, funcionario.getCargo());
                stmt.setInt(8, funcionario.getSalario());
                stmt.setInt(9, funcionario.getIdOrcamento());
                stmt.setInt(10, funcionario.getIdOficina());
                stmt.setInt(11, funcionario.getIdFuncionario());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Funcionário não encontrado. Atualização não realizada.");
        }
    }

    public void delete(int id) {
        if (read(id) != null) {
            String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Funcionário não encontrado. Exclusão não realizada.");
        }
    }

    public List<Funcionario> findAll() {
        List<Funcionario> list = new ArrayList<>();
        String query = "SELECT * FROM Funcionario";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario item = new Funcionario();
                item.setIdFuncionario(rs.getInt("id_funcionario"));
                item.setNome(rs.getString("fun_nome"));
                item.setTelefone(rs.getString("fun_telefone"));
                item.setEmail(rs.getString("fun_email"));
                item.setCpf(rs.getString("fun_cpf"));
                item.setDataContratacao(rs.getDate("fun_dataContratacao").toLocalDate());
                item.setSetor(rs.getString("fun_setor"));
                item.setCargo(rs.getString("fun_cargo"));
                item.setSalario(rs.getInt("fun_salario"));
                item.setIdOrcamento(rs.getInt("id_orcamento"));
                item.setIdOficina(rs.getInt("id_oficina"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
