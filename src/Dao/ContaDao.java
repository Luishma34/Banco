/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Cliente;
import Model.Conta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {

    private Connection con;
    private Statement stm;

    public ContaDao() {

        try {
            con = Conexao.getConnection();
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    public boolean inserir(Conta c) {
        try {
            stm.executeUpdate("insert into conta(num_conta,saldo,id_cliente,id_agencia)values('" + c.getNum_conta()
                    + "','" + c.getSaldo() + "','" + c.getId_cliente() + "','" + c.getId_agencia() + "')");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean atualizar(Integer id_agencia, Integer id_cliente) throws SQLException {
        try {

            stm.executeUpdate("update conta SET id_agencia='" + id_agencia + "' where id_cliente='" + id_cliente + "'");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean excluir(Integer id_cliente) {
        try {
            stm.executeUpdate("delete from conta where id_cliente=" + id_cliente);
            return true;
        } catch (SQLException ex) {

            return false;
        }
    }

    public ResultSet extrato(Conta c) throws SQLException {

        return stm.executeQuery("select saldo from conta where num_conta='" + c.getNum_conta() + "'");

    }

    public boolean depositar(Integer id_cliente, double valor) {
        try {
            stm.executeUpdate("UPDATE conta SET saldo = saldo + " + valor + " WHERE id_cliente = " + id_cliente);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean sacar(Integer id_cliente, double valor) {
        try {

            ResultSet rs = stm.executeQuery("select saldo from conta where id_cliente='" + id_cliente + "'");
            rs.next();
            Double saldo = rs.getDouble("saldo");
            if (saldo - valor >= 0) {
                stm.executeUpdate(
                        "update conta SET saldo = '" + (saldo - valor) + "' where id_cliente='" + id_cliente + "'");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {

            return false;
        }
    }

    public boolean transferir(Integer id_clienteA, String cpf, double valor) {
        try {
            ClienteDao cd = new ClienteDao();
            Cliente c = cd.consultar(cpf, "cpf");
            if (!c.getCpf().isEmpty()) {
                return sacar(id_clienteA, valor) && depositar(c.getId_cliente(), valor);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Conta consultar(Integer id_cliente) throws SQLException {

        ResultSet rs = stm.executeQuery("select * from conta where id_cliente='" + id_cliente + "'");
        Conta c = new Conta();
        while (rs.next()) {

            c.setId_agencia(rs.getInt("id_agencia"));
            c.setId_cliente(rs.getInt("id_cliente"));
            c.setId_conta(rs.getInt("id_conta"));
            c.setNum_conta(rs.getString("num_conta"));
            c.setSaldo(rs.getDouble("saldo"));

        }
        return c;
    }

    public List<Conta> lista_contas() throws SQLException {
        ResultSet rs = stm.executeQuery("select * from conta ");
        List<Conta> lista = new ArrayList<>();
        while (rs.next()) {
            Conta c = new Conta();

            c.setId_agencia(rs.getInt("id_agencia"));
            c.setId_cliente(rs.getInt("id_cliente"));
            c.setId_conta(rs.getInt("id_conta"));
            c.setNum_conta(rs.getString("num_conta"));
            c.setSaldo(rs.getDouble("saldo"));

            lista.add(c);
        }
        return lista;
    }
}
