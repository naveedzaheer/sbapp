package com.nzdemo.sbapp;

import java.io.Serializable;

public class Greeting implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

	private long id;
	private String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

    public void setId(long value) {
        this.id = value;
    }

	public String getContent() {
		return content;
    }
    
    public void setContent(String value) {
		this.content = value;
    }
    
    @Override
    public String toString() {
        return String.format("Greeting{Content=%s, Id=%s}", getContent(), getId());
    }
}