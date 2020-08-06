/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import Domain.PSPLog;
import InterfacesDAO.IPSPLogDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabrielflores
 */
public class PSPLogDAO implements IPSPLogDAO {
    
    private final ConnectDB connectDB;
    private Connection connection;
    private Statement consultation;
    private ResultSet results;

    public PSPLogDAO () {
        connectDB = new ConnectDB();
    }

    @Override
    public void savePSPLog(int id_practicing) throws SQLException, ClassNotFoundException {
        connection = connectDB.getConnection();
        String query = "INSERT INTO psplog (id_practicing) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_practicing);
        statement.executeQuery();
    }
    
    @Override
    public PSPLog readPSPLogByIDPSPLog(int id_pspLog) throws SQLException, ClassNotFoundException {
        connection = connectDB.getConnection();
        PSPLog pspLog = null;
        String query = "SELECT * from psplog where id_psplog = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_pspLog);
        results = statement.executeQuery();
        while (results.next()) {
            pspLog = new PSPLog();
            pspLog.setId_pspLog(results.getInt("id_psplog"));
            pspLog.setId_practicing(results.getInt("id_practicing"));
        }
        return pspLog;
    }

    @Override
    public ArrayList<PSPLog> readAllPSPLogs() throws SQLException, ClassNotFoundException {
        connection = connectDB.getConnection();
        ArrayList<PSPLog> allPSPLogs = new ArrayList<PSPLog>();
        PSPLog pspLog = null;
        String query = "SELECT * FROM psplog";
        PreparedStatement statement = connection.prepareStatement(query);
        results = statement.executeQuery();
        while (results.next()) {
            pspLog = new PSPLog();
            pspLog.setId_pspLog(results.getInt("id_psplog"));
            pspLog.setId_practicing(results.getInt("id_practicing"));
            allPSPLogs.add(pspLog);
        }
        return allPSPLogs;
    }
    
}
