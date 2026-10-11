package resqnet.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class SOSRequest implements Serializable {
private static int counter = 1001;
    private String requestId, status, requestDateTime;
    private Victim victim;
    private Volunteer assignedVolunteer;
    public SOSRequest(Victim victim) {

        this.victim = victim;
        

        requestId = "SOS-" + counter++;

        status = "Pending";

        requestDateTime = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(
                        "dd MMM yyyy | hh:mm a"
                )
        );
    }
    public String getRequestId() {
        return requestId;
    }

    public Victim getVictim() {
        return victim;
    }

    public Volunteer getAssignedVolunteer() {
        return assignedVolunteer;
    }

    public String getStatus() {
        return status;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void assignVolunteer(Volunteer volunteer) {
        assignedVolunteer = volunteer;
        status = "Assigned";
    }

    public void markResolved() {
        status = "Resolved";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}