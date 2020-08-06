package InterfacesDAO;

import Domain.ActivityPlan;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IActivityPlanDAO {
    public void saveActivityPlan(int id_activityPlan, int id_practicing, int id_professor) throws SQLException, ClassNotFoundException;
    public ActivityPlan readActivityPlanByIDActivityPlan (int id_activityPlan) throws SQLException, ClassNotFoundException;
    public ArrayList<ActivityPlan> readAllActivityPlans () throws SQLException, ClassNotFoundException;
}
