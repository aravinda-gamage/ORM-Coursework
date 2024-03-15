package lk.ijse.ormCoursework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "branches")
public class Branches {
    @Id
    @Column(name = "br_id")
    private String brId;
    @Column(name = "br_name")
    private String brName;
    @Column(name = "br_location")
    private String location;
    @Column(name = "br_status")
    private String brStatus;

    public Branches() {
    }

    public Branches(String brId, String brName, String location, String brStatus) {
        this.brId = brId;
        this.brName = brName;
        this.location = location;
        this.brStatus = brStatus;
    }

    public String getBrId() {
        return brId;
    }

    public void setBrId(String brId) {
        this.brId = brId;
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrStatus() {
        return brStatus;
    }

    public void setBrStatus(String brStatus) {
        this.brStatus = brStatus;
    }
}
