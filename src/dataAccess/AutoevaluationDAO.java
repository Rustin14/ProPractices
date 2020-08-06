/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import Domain.Autoevaluation;
import InterfacesDAO.IAutoevaluationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AutoevaluationDAO implements IAutoevaluationDAO {
    private ConnectDB connectDB;
    private Connection connection;
    private Statement statement;
    private ResultSet results;
    
    public AutoevaluationDAO() {
        connectDB = new ConnectDB();
    }
    

    @Override
    public void saveAutoevaluation(int id_autoevaluation, int id_practicing) throws SQLException, ClassNotFoundException {
        connection = connectDB.getConnection();
        String query = "INSERT INTO autoevaluation (id_autoevaluation, id_practicing) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_autoevaluation);
        statement.setInt(2, id_practicing);
        statement.executeQuery();
    }

    @Override
    public Autoevaluation readAutoevaluationByIDAutoevaluation(int id_autoevaluation) throws SQLException, ClassNotFoundException {
        connection = connectDB.getConnection();
        Autoevaluation autoevaluation = null;
        String query = "SELECT * FROM autoevaluation WHERE id_autoevaluation = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_autoevaluation);
        results = statement.executeQuery();
        while (results.next()) {
            autoevaluation = new Autoevaluation();
            autoevaluation.setId_autoevaluation(results.getInt("id_autoevaluation"));
            autoevaluation.setId_practicing(results.getInt("id_practicing"));
        }
        return autoevaluation;
    }

    @Override
    public ArrayList<Autoevaluation> readAllAutoevaluations() throws SQLException, ClassNotFoundException {
       connection = connectDB.getConnection();
       ArrayList<Autoevaluation> listAutoevaluations = new ArrayList<Autoevaluation>();
       Autoevaluation autoevaluation = null;
       String query = "SELECT * FROM autoevaluation";
       PreparedStatement statement = connection.prepareStatement(query);
       results = statement.executeQuery();
       while (results.next()) {
           autoevaluation = new Autoevaluation();
           autoevaluation.setId_autoevaluation(results.getInt("id_autoevaluation"));
           autoevaluation.setId_practicing(results.getInt("id_practicing"));
           listAutoevaluations.add(autoevaluation);
       }
       return listAutoevaluations;
    }
    
}
