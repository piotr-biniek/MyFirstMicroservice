/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;



/**
 *
 * @author java
 */
@Entity
@Table(name = "Event")

public class Event extends AbstractEntity implements Serializable {
	 protected static final long serialVersionUID = 8765431L;
    @Id
    @Column(name = "id", updatable = false)
 //   @TableGenerator(name = "EventGenerator", table = "GENERATOR", valueColumnName = "ID_RANGE", initialValue = 200, allocationSize = 7919)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "name",  nullable = false, length = 256)
    private String name;

//    @NotNull
//    @Column(name = "dateOfEvent", nullable = false)
//    private LocalDateTime dateOfEvent;
//
//    @NotNull
//    @Column(name = "duration", unique = false, nullable = true)
//    private Duration duration;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

//    public LocalDateTime getDateOfEvent() {
//        return dateOfEvent;
//    }
//
//    public void setDateOfEvent(LocalDateTime dateOfEvent) {
//        this.dateOfEvent = dateOfEvent;
//    }
//
//    public Duration getDuration() {
//        return duration;
//    }
//
//   
//    public void setDuration(Duration duration) {
//        this.duration = duration;
//    }

    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }


    @Override
    protected String getBusinessKey() {//todo

        return "id :" + String.valueOf(id) + ", name :" + name;

    }

   
}
