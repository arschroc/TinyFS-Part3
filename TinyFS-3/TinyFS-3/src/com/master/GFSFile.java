package com.master;

import java.util.ArrayList;
import java.util.List;

public class GFSFile {

	private String filename; // File name
	private List<String> chunkHandles; // List of chunk handles
	
	// TODO: Implement 
	
	// Parameterized constructor
	public GFSFile(String filename) {
		this.filename = filename;
		this.chunkHandles = new ArrayList<>();
	}
	
	// Getter for filename
	public String getFilename() {
		return filename;
	}

	// Setter for filename
	public void setFilename(String filename) {
		this.filename = filename;
	}

	// Getter for chunk handles of file
	public List<String> getChunkHandles() {
		return chunkHandles;
	}
}
