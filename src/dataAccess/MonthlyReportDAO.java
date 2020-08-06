/*
Institution: Universidad Veracruzana
File creator: Carlos Gabriel Flores Lira
Class name: MonthlyReportDAO
Date of creation: May 8th. 2020
*/
package dataAccess;

import Domain.MonthlyReport;
import Domain.PartialReport;
import InterfacesDAO.IMonthlyReportDAO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonthlyReportDAO implements IMonthlyReportDAO {
    private ConnectDB connectDB;
    private Connection connection;
    private Statement statement;
    private ResultSet results;
    
    public MonthlyReportDAO() {
         connectDB = new ConnectDB();
    }

    @Override
    public void saveMonthlyReportByIDMonthly(int id_monthly, int id_practicing) throws SQLException, ClassNotFoundException{
        Connection connect = connectDB.getConnection();
        String query = "INSERT INTO monthlyreport  (id_monthly, id_practicing) VALUES (?, ?)";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_monthly);
        statement.setInt(7, id_practicing);
        statement.executeQuery(); 
        connectDB.closeConnection();
    }

    @Override
    public MonthlyReport readMonthlyReportByIDMonthly(int id_monthly) throws SQLException, ClassNotFoundException {
        MonthlyReport paper = null;
        ResultSet resultSet = null;
        Connection connect = connectDB.getConnection();
        String query = "SELECT id_monthly, id_practicing FROM monthlyreport where id_monthly = ?";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_monthly);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            paper = new MonthlyReport();
            paper.setId_monthly(resultSet.getInt("id_monthly"));
            paper.setId_practicing(resultSet.getInt("id_practicing"));
        }
        //connectDB.closeConnection();
        return paper;
    }

    @Override
    public void deleteMonthlyReportByIDMonthly(int id_monthly) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public ArrayList<MonthlyReport> ReadAllMonthlyReports() throws SQLException, ClassNotFoundException {
        MonthlyReport paper = null;
        ArrayList<MonthlyReport> allMonthlyReports = new ArrayList();
        Connection connect = connectDB.getConnection();
        String query = "SELECT id_monthly FROM monthlyreport";
        PreparedStatement statement = connect.prepareStatement(query);
        results = statement.executeQuery();
        while (results.next()) {
            paper = new MonthlyReport();
            paper.setId_monthly(results.getInt("id_monthly"));
            allMonthlyReports.add(paper);
        }
        return allMonthlyReports;
    }
}
