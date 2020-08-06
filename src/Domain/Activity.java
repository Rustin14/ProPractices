package Domain;

public class Activity {
    private int id_activity;
    private String activityName;
    private String description;
    private int timePlanned;
    private int timeInvested;
    private int id_practicing;
    private int id_professor;

    public Activity(int id_activity, String name, String description, int plannedTime, int timeInvested, int id_professor, int id_practicing) {
        this.id_activity = id_activity;
        this.activityName = name;
        this.description = description;
        this.timePlanned = plannedTime;
        this.timeInvested = timeInvested;
        this.id_practicing = id_practicing;
        this.id_professor = id_professor;
    }

    public Activity() { }

    public int getId_activity() {
        return id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getId_practicing() {
        return id_practicing;
    }

    public void setId_practicing(int id_practicing) {
        this.id_practicing = id_practicing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimePlanned() {
        return timePlanned;
    }

    public void setTimePlanned(int timePlanned) {
        this.timePlanned = timePlanned;
    }

    public int getTimeInvested() {
        return timeInvested;
    }

    public void setTimeInvested(int timeInvested) {
        this.timeInvested = timeInvested;
    }

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
    
    
}
