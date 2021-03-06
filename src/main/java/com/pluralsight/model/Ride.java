package com.pluralsight.model;

public class Ride {

    private String name;
    private int duration;
    private Integer id;

    public int getDuration() {
	return duration;
    }

    public String getName() {
	return name;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
	
        
}
