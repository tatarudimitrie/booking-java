package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @Column(name = "time")
    private String time;

    public Schedule(){}

    public Schedule(long serviceId, String time) {
        this.service = new Service(serviceId,"","","",0,0d,"");
        this.time = time;
    }

    public Schedule(String time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", service=" + service +
                ", time='" + time + '\'' +
                '}';
    }
}
