/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ClienteDao {

    private Connection con;
    private Statement stm;

    public ClienteDao() {

        try {
            con = Conexao.getConnection();
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    public boolean inserir(Cliente c) {
        try {
            ResultSet rs1 = stm.executeQuery("select * from cliente where cpf='" + c.getCpf() + "'");
            boolean cpf = rs1.next();
            rs1.close();

            ResultSet rs2 = stm.executeQuery("select * from cliente where login='" + c.getLogin() + "'");
            boolean login = rs2.next();
            rs2.close();
            if (!cpf && !login) {
                stm.executeUpdate("insert into cliente(nome,cpf,data_nas,telefone, rg, login, senha) values('"
                        + c.getNome() + "','" + c.getCpf() + "','" + c.getData_nas() + "','" + c.getTelefone() + "', '"
                        + c.getRg() + "', '" + c.getLogin() + "', '" + c.getSenha() + "')");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public Cliente consultar(String valor, String coluna) throws SQLException {

        ResultSet rs = stm.executeQuery("select * from cliente where " + coluna + "='" + valor + "'");
        Cliente c = new Cliente();
        while (rs.next()) {

            c.setId_cliente(rs.getInt("id_cliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setLogin(rs.getString("login"));
            c.setSenha(rs.getString("senha"));
            c.setData_nas(rs.getString("data_nas"));
            c.setRg(rs.getString("rg"));
            c.setTelefone(rs.getString("telefone"));

        }
        return c;
    }

    public boolean excluir(Integer id_cliente) throws SQLException {
        try {
            stm.executeUpdate("delete from cliente where id_cliente= " + id_cliente);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Cliente a, String senhaAtual) throws SQLException {
        try {
            ResultSet rs = stm.executeQuery("select senha from cliente where id_cliente = " + a.getId_cliente());
            rs.next();
            if (!rs.getString("senha").equals(senhaAtual)) {
                return false;
            } else {
                rs = stm.executeQuery(
                        "select count(*) as total from cliente where (cpf = '" + a.getCpf() + "' or login = '"
                                + a.getLogin() + "') and id_cliente != " + a.getId_cliente());
                rs.next();
                if (rs.getInt("total") > 0) {
                    return false;
                }else {
                stm.executeUpdate("update cliente set nome='" + a.getNome() + "', cpf='" + a.getCpf() + "', data_nas='"
                        + a.getData_nas() + "', telefone='" + a.getTelefone() + "', rg='" + a.getRg() + "', login='"
                        + a.getLogin() + "', senha='" + a.getSenha() + "' where cpf='" + a.getCpf() + "'");
                return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean entrar(Cliente e) throws SQLException {
        try {
            ResultSet rs = stm.executeQuery(
                    "select * from cliente where login='" + e.getLogin() + "' and senha='" + e.getSenha() + "'");
            return rs.next() && !rs.next();
        } catch (Exception ex) {
            return false;
        }
    }

    public List<Cliente> lista_fun() throws SQLException {
        ResultSet rs = stm.executeQuery("select * from cliente");
        List<Cliente> lista = new ArrayList<>();
        while (rs.next()) {
            Cliente c = new Cliente();

            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setLogin(rs.getString("login"));
            c.setSenha(rs.getString("senha"));
            c.setData_nas(rs.getString("data_nas"));
            c.setRg(rs.getString("rg"));
            c.setTelefone(rs.getString("telefone"));

            lista.add(c);
        }
        return lista;
    }
}
