/*
Institution: Universidad Veracruzana
File creator: Carlos Gabriel Flores Lira
Class name: PartialReportDAO
Date of creation: May 8th. 2020
*/
package dataAccess;

import Domain.PartialReport;
import InterfacesDAO.IPartialReportDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;

public class PartialReportDAO implements IPartialReportDAO {
    private ConnectDB connectDB;
    private Connection connection;
    private Statement statement;
    private ResultSet results;


    @Override
    public void savePartialReport(int id_practicing) throws SQLException, ClassNotFoundException {
        Connection connect = connectDB.getConnection();
        String query = "INSERT INTO partialreport  (id_practicing) VALUES (?)";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(7, id_practicing);
        statement.executeQuery();
        connectDB.closeConnection();
    }
    
    @Override
    public PartialReport readPartialReportByIDPracticing(int id_practicing) throws SQLException, ClassNotFoundException {
        PartialReport paper = null;
        Connection connect = connectDB.getConnection();
        String query = "SELECT * FROM partialreport where id_practicing = ?";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_practicing);
        results = statement.executeQuery();
        while (results.next()) {
            paper = new PartialReport();
            paper.setId_partial(results.getInt("id_partial"));
            paper.setId_practicing(results.getInt("id_practicing"));
        }
        return paper;
    }
    
    @Override
    public PartialReport readPartialReportByIDPartial(int id_partial) throws SQLException, ClassNotFoundException {
        PartialReport paper = null;
        Connection connect = connectDB.getConnection();
        String query = "SELECT * FROM partialreport where id_partial = ?";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_partial);
        results = statement.executeQuery();
        while (results.next()) {
            paper = new PartialReport();
            paper.setId_partial(results.getInt("id_partial"));
            paper.setId_practicing(results.getInt("id_practicing"));
        }
        return paper;
    }

    @Override
    public void deletePartialReportByIDPartial(int id_partial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ArrayList<PartialReport> ReadAllPartialReports() throws SQLException, ClassNotFoundException {
        PartialReport paper = null;
        ArrayList<PartialReport> allPartialReports = new ArrayList();
        Connection connect = connectDB.getConnection();
        String query = "SELECT id_partial FROM partialreport";
        PreparedStatement statement = connect.prepareStatement(query);
        results = statement.executeQuery();
        while (results.next()) {
            paper = new PartialReport();
            paper.setId_partial(results.getInt("id_partial"));
            allPartialReports.add(paper);
        }
        return allPartialReports;
    }
}
