package com.redhat.emeatigers.storagedays;

import java.io.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@SuppressWarnings("serial")
@Table(name = "event", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Entity
public class Event implements Serializable {
	@Id
	@GeneratedValue
    private Long id;
	private long ts;
	private String source;
	private String event;
    public Long getId() {
        return id;
    }
	public void setId(Long id) {
        this.id = id;
    }
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	
}
