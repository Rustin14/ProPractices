/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import Domain.Activity;
import Domain.ActivityPlan;
import InterfacesDAO.IActivityPlanDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ActivityPlanDAO implements IActivityPlanDAO {

    private final ConnectDB connectDB;
    private Connection connection;
    private Statement consultation;
    private ResultSet results;

    public ActivityPlanDAO () {
        connectDB = new ConnectDB();
    }
    
    @Override
    public void saveActivityPlan(int id_activityPlan, int id_practicing, int id_professor) throws SQLException, ClassNotFoundException {
        Connection connect = connectDB.getConnection();
        String query = "INSERT INTO activityplan (id_activity, id_practicing, id_professor) VALUES (?, ?, ?)";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_activityPlan);
        statement.setInt(2, id_practicing);
        statement.setInt(3, id_professor);
        statement.executeQuery();
        connectDB.closeConnection();
    }

    @Override
    public ActivityPlan readActivityPlanByIDActivityPlan(int id_activityPlan) throws SQLException, ClassNotFoundException {
        ActivityPlan activityPlan = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM activityplan where id_activityplan = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_activityPlan);
        statement.executeQuery();
        while (results.next()) {
            activityPlan = new ActivityPlan();
            activityPlan.setId_activityPlan(results.getInt("id_activityplan"));
            activityPlan.setId_practicing(results.getInt("id_practicing"));
            activityPlan.setId_professor(results.getInt("id_professor"));
        }
        connectDB.closeConnection();
        return activityPlan;
    }

    @Override
    public ArrayList<ActivityPlan> readAllActivityPlans() throws SQLException, ClassNotFoundException {
        ArrayList<ActivityPlan> allActivityPlans = new ArrayList();
        ActivityPlan activityPlan = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM activityplan";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeQuery();
        while(results.next()) {
            activityPlan = new ActivityPlan();
            activityPlan.setId_activityPlan(results.getInt("id_activityplan"));
            activityPlan.setId_practicing(results.getInt("id_practicing"));
            activityPlan.setId_professor(results.getInt("id_professor"));
            allActivityPlans.add(activityPlan);
        }
        connectDB.closeConnection();
        return allActivityPlans;
    }
    
}
