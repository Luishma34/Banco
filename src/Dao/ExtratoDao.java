/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Extrato;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExtratoDao {
    private Connection con;
    private Statement stm;

    public ExtratoDao() {

        try {
            con = Conexao.getConnection();
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    public boolean inserir(Extrato e) {
        try {
            LocalDateTime dataHora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHoraStr = dataHora.format(formatter);
            
            stm.executeUpdate("INSERT INTO extrato(id_cliente, valor, saldo, tipo, data, origem, destino) VALUES(" + e.getId_cliente() + ", " + e.getValor() + ", " + e.getSaldo() + ", '" + e.getTipo() + "', '" + dataHoraStr + "', '" + e.getOrigem() + "', '" + e.getDestino() + "')");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Integer id_cliente) {
        try {
            stm.executeUpdate("delete from extrato where id_cliente=" + id_cliente);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Extrato> lista_extrato(Integer id_cliente) throws SQLException {
        ResultSet rs = stm.executeQuery("select * from extrato where id_cliente = " + id_cliente + " order by data desc");
        List<Extrato> lista = new ArrayList<>();
        while (rs.next()) {
            Extrato e = new Extrato();

            e.setData(rs.getTimestamp("data"));
            e.setDestino(rs.getString("destino"));
            e.setId_cliente(rs.getInt("id_cliente"));
            e.setOrigem(rs.getString("origem"));
            e.setSaldo(rs.getDouble("saldo"));
            e.setTipo(rs.getString("tipo"));
            e.setValor(rs.getDouble("valor"));

            lista.add(e);
        }
        return lista;
    }
}
