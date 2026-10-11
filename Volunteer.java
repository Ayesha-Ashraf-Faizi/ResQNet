package resqnet.model;

public class Volunteer extends Person {

    private String skill, assignedVictim;
    private boolean available;

    public Volunteer(
            String name,
            String phone,
            String location,
            String skill
    ) {

        super(name, phone, location);

        this.skill = skill;
        available = true;
        assignedVictim = "None";
    }
    public String getSkill() {
        return skill;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getAssignedVictim() {
        return assignedVictim;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setAssignedVictim(String assignedVictim) {
        this.assignedVictim = assignedVictim;
    }

    @Override
    public String getRole() {
        return "Volunteer";
    }
}