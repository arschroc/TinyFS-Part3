package com.master;

import java.util.List;

import com.chunkserver.ChunkServer;

public class Master {
	
	private FileSystem fileSystem; 				// Instance of file system
	private ChunkServer chunkServer; 			// Instance of chunk server
	final static String filePath = "csci485/"; 	// Root file path of project
	
	public Master() {
		// Initialize the file system
		fileSystem = new FileSystem(filePath);
		chunkServer = new ChunkServer();
		System.out.println("Master running");
		// Single Master, single chunkServer
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
	public boolean createDir(String src, String dirname) {
		return fileSystem.createDir(src, dirname);
	}
	
	/**
	 * Invokes the fileSystem to list the sub-directories and
	 * files of the provided target path
	 */
	public String[] listDir(String tgt) {
		// Convert ArrayList to string array before returning
//		return (String[]) fileSystem.listDir(tgt);
		List<String> items = fileSystem.listDir(tgt);
		return items.toArray(new String[items.size()]);
	}
	
	/**
	 * TODO:
	 */
	public boolean deleteDir(String src, String dirname) {
		return fileSystem.deleteDir(src, dirname);
	}
	
	public boolean hasSubDirs(String src, String dirname) {
		return fileSystem.hasSubDirs(src, dirname);
	}
	
	/**
	 * -1 = tgtDir DNE, -2 = file Already Exists, 0 = success
	 */
	public int createFile(String tgtdir, String filename) {
		return fileSystem.createFile(tgtdir, filename);
	}
	
	/**
	 * TODO:
	 */
	public void openFile() {
		
	}
	
	/**
	 * -1 = tgtDir DNE, -2 = file DNE, 0 = success
	 */
	public int deleteFile(String tgtdir, String filename) {
		return fileSystem.deleteFile(tgtdir, filename);
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
