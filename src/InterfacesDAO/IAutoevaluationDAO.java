/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesDAO;

import Domain.Autoevaluation;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabrielflores
 */
public interface IAutoevaluationDAO {
    public void saveAutoevaluation(int id_autoevaluation, int id_practicing) throws SQLException, ClassNotFoundException;
    public Autoevaluation readAutoevaluationByIDAutoevaluation(int id_autoevaluation) throws SQLException, ClassNotFoundException;
    public ArrayList<Autoevaluation> readAllAutoevaluations() throws SQLException, ClassNotFoundException;
}
