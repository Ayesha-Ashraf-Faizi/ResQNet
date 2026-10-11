package resqnet.model;

import java.io.Serializable;

public class Resource implements Serializable {

    private String resourceId;
    private String resourceName;
    private String category;
    private String location;
    private boolean available;

    public Resource(String resourceId,
                    String resourceName,
                    String category,
                    String location) {

        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.category = category;
        this.location = location;
        this.available = true;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return resourceId + " | " +
               resourceName + " | " +
               category + " | " +
               location + " | " +
               (available ? "Available" : "Busy");
    }
}