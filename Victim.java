package resqnet.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Victim extends Person {

    private String emergencyType, description, status, requestTime;
    private int numberOfPeople;

    public Victim(
            String name,
            String phone,
            String location,
            String emergencyType,
            int numberOfPeople,
            String description
    ) {

        super(name, phone, location);

        this.emergencyType = emergencyType;
        this.numberOfPeople = numberOfPeople;
        this.description = description;

        status = "Pending";

        requestTime = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                )
        );
    }
    public String getEmergencyType() {
        return emergencyType;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getRole() {
        return "Victim";
    }
}