package com.master;

import com.chunkserver.ChunkServer;

public class Master {
	
	public static final int CREATE_FILE_CMD = 104;
	public static final int OPEN_FILE_CMD = 105;
	public static final int DELETE_FILE_CMD = 106;
	
	private FileSystem fileSystem; 				// Instance of file system
	private ChunkServer chunkServer; 			// Instance of chunk server
	final static String filePath = "csci485/"; 	// Root file path of project
	
	public Master() {
		// TODO: Initialize the file system
//		fileSystem = new FileSystem(filePath);
		chunkServer = new ChunkServer();
		System.out.println("Master running");
		// Single Master, single chunkServer
		chunkServer.ReadAndProcessRequests();
	}
	
	/**
	 * Creates a chunk corresponding to a file
	 * @return the chunk handle of the last chunk
	 */
	public void createChunk() {
		
	}
	
	/**
	 * TODO:
	 */
	public void createFile() {
		
	}
	
	/**
	 * TODO:
	 */
	public void openFile() {
		
	}
	
	/**
	 * TODO:
	 */
	public void deleteFile() {
		
	}
	
	/**
	 * Main method for master
	 * @param args
	 */
	public static void main(String[] args) {
		// Create an instance of master
		Master master = new Master();
	}
}
