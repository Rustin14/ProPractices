/*
Institution: Universidad Veracruzana
File creator: Brandon Adalit Trujillo Capistrán
Class name: PracticingDAO
Date of creation: April 22nd. 2020
*/
package dataAccess;
import Domain.Practicing;
import InterfacesDAO.IPracticingDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PracticingDAO implements IPracticingDAO {
    private final ConnectDB connectDB;
    private Connection connection;
    private Statement consulta;
    private ResultSet results;
    
    public PracticingDAO() {
        connectDB = new ConnectDB();
    }

    @Override
    public void savePracticing(int id_person, String name, String enrollment, int id_project, int id_professor)  throws SQLException, ClassNotFoundException {
        Connection connect = connectDB.getConnection();
        String query = "INSERT INTO practicing (id_person, name, enrollment, id_project, id_professor) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_person);
        statement.setString(2, name);
        statement.setString(3, enrollment);
        statement.setInt(4, id_project);
        statement.setInt(4, id_professor);
        statement.executeQuery();
        connectDB.closeConnection();
    }
    

    @Override
    public Practicing searchPracticingByEnrollment (String enrollment) throws SQLException, ClassNotFoundException {
        Practicing practicing = null;
        Connection connect = ConnectDB.getConnection();
        String query = "SELECT name, enrollment, id_project FROM practicing WHERE enrollment = ?";
        PreparedStatement sentencia = connect.prepareStatement(query);
        sentencia.setString(1, enrollment);
        results = sentencia.executeQuery();
        while (results.next()){
            practicing = new Practicing();
            practicing.setEnrollment(results.getString("enrollment"));
            practicing.setPracticingName(results.getString("name"));
            practicing.setId_project(results.getInt("id_project"));
        }
        connectDB.closeConnection();
        return practicing;
    }
    
    public Practicing searchPracticingByIDPracticing (int id_person) throws SQLException, ClassNotFoundException{
        Practicing practicing = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT id_person, enrollment, name from practicing where id_person = ?";
        PreparedStatement sentence = connection.prepareStatement(query);
        sentence.setInt(1, id_person);
        results = sentence.executeQuery();
        while (results.next()) {
            practicing = new Practicing();
            practicing.setId_person(results.getInt("id_person"));
            practicing.setEnrollment(results.getString("enrollment"));
            practicing.setPracticingName(results.getString("name"));
        }
        return practicing;
    }

    public Practicing searchPracticingByKeyword (String keyword) throws SQLException, ClassNotFoundException {
        Practicing practicing = null;
        Connection connect = connectDB.getConnection();
        String query = "SELECT enrollment, name FROM practicing WHERE enrollment LIKE %{?}%";
        PreparedStatement sentencia = connect.prepareStatement(query);
        sentencia.setString(1, keyword);
        results = sentencia.executeQuery();
        while (results.next()) {
            practicing = new Practicing();
            practicing.setEnrollment(results.getString("enrollment"));
            practicing.setPracticingName(results.getString("name"));
        }
        connectDB.closeConnection();
        return practicing;
    }

    public void deletePracticingByEnrollment (String enrollment) throws SQLException, ClassNotFoundException {
        Connection connect = null;
        PreparedStatement sentence = null;
        connect = connectDB.getConnection();
        String query = "START TRANSACTION; DELETE FROM practicing where enrollment = ?";
        sentence = connect.prepareStatement(query);
        sentence.setString(1, enrollment);
        results = sentence.executeQuery();
        boolean userAnswer = true;
        if (userAnswer) {
                sentence = connect.prepareStatement("COMMIT");
                sentence.executeQuery();
                connectDB.closeConnection();
            } else {
                sentence = connect.prepareStatement("ROLLBACK");
                sentence.executeQuery();
                connectDB.closeConnection();
            }
    }
    
    @Override
    public List<Practicing> returnAllPracticing() throws SQLException, ClassNotFoundException {
        List<Practicing> allPracticing = new ArrayList();
        connection = connectDB.getConnection();
        consulta = connection.createStatement();
        results = consulta.executeQuery("select name, enrollment from practicing");
        while (results.next()){
            Practicing practicing = new Practicing();
            practicing.setEnrollment(results.getString("enrollment"));
            practicing.setPracticingName(results.getString("name"));
            allPracticing.add(practicing);
        }
        connectDB.closeConnection();
        return allPracticing;
    } 
}

