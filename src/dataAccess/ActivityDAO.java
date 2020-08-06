package dataAccess;

import Domain.Activity;
import Domain.Administrator;
import Domain.Project;
import InterfacesDAO.IActivityDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityDAO implements IActivityDAO {
    private final ConnectDB connectDB;
    private Connection connection;
    private Statement consultation;
    private ResultSet results;

    public ActivityDAO () {
        connectDB = new ConnectDB();
    }

    @Override
    public void saveActivity(int id_activity, String name, int id_practicing)  throws SQLException, ClassNotFoundException {
        Connection connect = connectDB.getConnection();
        String query = "INSERT INTO activity (id_activity, name, id_practicing) VALUES (?, ?, ?)";
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1, id_activity);
        statement.setString(2, name);
        statement.setInt(3, id_practicing);
        statement.executeQuery();
        connectDB.closeConnection();
    }

    @Override
    public Activity readActivityByIDActivity(int id_activity)  throws SQLException, ClassNotFoundException {
        Activity activity = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM activity where id_activity = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_activity);
        statement.executeQuery();
        while (results.next()) {
            activity = new Activity();
            activity.setId_activity(results.getInt("id_activity"));
            activity.setActivityName(results.getString("activityName"));
            activity.setDescription(results.getString("description"));
            activity.setId_practicing(results.getInt("id_practicing"));
        }
        return activity;
    }

    @Override
    public void deleteActivity(int id_activity)  throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        PreparedStatement sentence = null;
        Connection connect = null;
        connect = connectDB.getConnection();
        String query = "START TRANSACTION; DELETE FROM activity WHERE id_activity = ?";
        sentence = connect.prepareStatement(query);
        sentence.setInt(1, id_activity);
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
    public ArrayList<Activity> returnAllActivities()  throws SQLException, ClassNotFoundException {
        ArrayList<Activity> allActivities = new ArrayList();
        Activity activity = null;
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM activity";
        PreparedStatement statement = connection.prepareStatement(query);
        results = statement.executeQuery();
        while(results.next()) {
            activity = new Activity();
            activity.setId_activity(results.getInt("id_activity"));
            activity.setActivityName(results.getString("activityName"));
            activity.setDescription(results.getString("description"));
            activity.setId_practicing(results.getInt("id_practicing"));
            allActivities.add(activity);
        }
        return allActivities;
    }
}
