/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Agencia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AgenciaDao {
    private Connection con;
    private Statement stm;

    public AgenciaDao() {

        try {
            con = Conexao.getConnection();
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    public List<Agencia> listar() {
        try {
            List<Agencia> agencias = new ArrayList<>();
            ResultSet rs = stm.executeQuery("SELECT * FROM agencia");
            while (rs.next()) {
                Agencia agencia = new Agencia();
                agencia.setId_agencia(rs.getInt("id_agencia"));
                agencia.setNome(rs.getString("nome"));
                agencia.setEndereco(rs.getString("endereco"));
                agencia.setTelefone(rs.getString("telefone"));
                agencias.add(agencia);
            }
            return agencias;
        } catch (SQLException ex) {
            return null;
        }
    }
}
