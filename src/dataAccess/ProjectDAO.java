/*
Institution: Universidad Veracruzana
File creator: Carlos Gabriel Flores Lira
Class name: ProjectDAO
Date of creation: April 30th. 2020
*/
package dataAccess;
import Domain.Project;
import InterfacesDAO.IProjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IProjectDAO {
    private final ConnectDB connectDB;
    private Connection connection;
    private Statement consultation;
    private ResultSet results;

    public ProjectDAO () {
        connectDB = new ConnectDB();
    }

    @Override
    public void saveProject(int id_project, String name, float duration, String general_purpose, String general_description,
                            int id_company, String chargeResponsable, String nameResponsable, String emailResponsable) throws SQLException, ClassNotFoundException{

        Connection connect = connectDB.getConnection();
            String query = "INSERT INTO project (id_project, name, duration, general_purpose, general_description, " +
                    "id_company, charge_responsable, name_responsable, email_responsable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1, id_project);
            statement.setString(2, name);
            statement.setFloat(3, duration);
            statement.setString(4, general_purpose);
            statement.setString(5, general_description);
            statement.setInt(6, id_company);
            statement.setString(7, chargeResponsable);
            statement.setString(8, nameResponsable);
            statement.setString(9, emailResponsable);
            statement.executeQuery();
            connectDB.closeConnection();
    }

    @Override
    public Project searchProjectByIDProject(int id_project) throws SQLException, ClassNotFoundException{
        Project project = null;
        Connection connection = ConnectDB.getConnection();
        String query = "SELECT id_project, name FROM project WHERE id_project = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt (1, id_project);
        results = statement.executeQuery();
        while (results.next()) {
            project = new Project();
            project.setId_project(results.getInt("id_project"));
            project.setProjectName(results.getString("name"));
        }
        connectDB.closeConnection();
        return project;
    }

    @Override
    public Project searchProjectByKeyword(String keyword) throws SQLException, ClassNotFoundException {
        Project project = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT id_project, name FROM project WHERE name LIKE %{?}%";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, keyword);
        statement.executeQuery();
        while (results.next()) {
            project = new Project();
            project.setId_project(results.getInt("id_project"));
            project.setProjectName(results.getString("name"));
        }
            connectDB.closeConnection();
        return project;
    }

    @Override
    public void deleteProjectByIDProject(int id_project) throws SQLException, ClassNotFoundException {
        PreparedStatement sentence = null;
        Connection connect = connectDB.getConnection();
        String query = "START TRANSACTION; DELETE FROM project WHERE id_project = ?";
        sentence = connect.prepareStatement(query);
        sentence.setInt(1, id_project);
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
    public List<Project> returnAllProjects() throws SQLException, ClassNotFoundException {
        List<Project> allProjects = new ArrayList();
        Project project = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT id_project, name FROM project";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeQuery();
        while(results.next()) {
            project = new Project();
            project.setId_project(results.getInt("id_project"));
            project.setProjectName(results.getString("name"));
            allProjects.add(project);
        }
        connectDB.closeConnection();
        return allProjects;
    }
}
