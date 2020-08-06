/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesDAO;

import Domain.PSPLog;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author gabrielflores
 */
public interface IPSPLogDAO {
    public void savePSPLog(int id_practicing) throws SQLException, ClassNotFoundException;
    public PSPLog readPSPLogByIDPSPLog(int id_pspLog) throws SQLException, ClassNotFoundException;
    public ArrayList<PSPLog> readAllPSPLogs() throws SQLException, ClassNotFoundException;
}
