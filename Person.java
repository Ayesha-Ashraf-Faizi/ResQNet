package resqnet.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

    private String name, phone, location;

    public Person(String name, String phone, String location) {
        this.name = name;
        this.phone = phone;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public abstract String getRole();
}