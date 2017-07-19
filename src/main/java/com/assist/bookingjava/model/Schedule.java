package com.assist.bookingjava.model;

import javax.persistence.*;

@Entity
@Table(name = "Schedule")
public class Schedule {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @Column(name = "time")
    private String time;

    public Schedule(Service service, String time) {
        this.service = service;
        this.time = time;
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
}
