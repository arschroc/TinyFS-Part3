package com.master;

public class Master {

	// Instance of FS
	private FileSystem fileSystem;
	
	public Master() {
		// TODO: Need to initialize the file system, perhaps with a config file?
		fileSystem = new FileSystem();
	}
	
	// Main method for Master
	public static void main(String[] args) {
		// Create an instance of master
		Master master = new Master();
	}
}
