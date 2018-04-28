package com.master;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {

	private Map<String, GFSDir> fileNamespace;	// File namespace member
	private GFSDir root;							// The file namespace root
	
	/**
	 * Parameterized constructor for the file system
	 * @param filePath
	 */
	public FileSystem(String filePath) {
		fileNamespace = new HashMap<String, GFSDir>();
		// Initialize the file system
		init();
	}
	
	/**
	 * Init the file system with a root directory
	 */
	private void init() {
		// Root is not contained by any directory
		this.root = new GFSDir("/", null);
	}
	
	/**
	 * Tries to create a directory for a given source dir
	 * and filePath.
	 * @param srcDir, filePath
	 * @return true if directory was successfully created
	 */
	public boolean createDir(String srcDir, String filePath) {
		// Check if parent directory exists
		GFSDir parentDir = getParent(srcDir);
		if (parentDir != null) {
			// Try to add new directory under the parent directory
			GFSDir newDir = parentDir.createSubDir(filePath);
			if (newDir != null) {
				// Add to namespace map
				fileNamespace.put(newDir.getName(), newDir);
				System.out.println("Sucessfully created Directory");
				return true;
			} else {
				// Handle directory already exists error
				System.out.println("Error, directory already exists");
				return false;
			}
		} else {
			System.out.println("Error, parent directory doesn't exist");
			return false;
		}
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean deleteDir(String srcDir, String filePath) {
		GFSDir parentDir = getParent(srcDir);
		GFSDir toDelete = null;
		for (int i=0; i<parentDir.getSubDirs().size(); i++) {
			if (parentDir.getSubDirs().get(i).getName().equals(filePath)) {
				toDelete = parentDir.getSubDirs().get(i);
			}
		}
		//check if dir DNE
		if (toDelete == null) {
			return false;
		}
		deleteSubDirs(toDelete);
		
		//Check if dir DNE in GFSDir
		if (!parentDir.deleteSubDir(filePath)) {
			return false;
		}
		
		return true;
	}
	
	public boolean hasSubDirs(String src, String filePath) {
		GFSDir parentDir = getParent(src);
		GFSDir toDelete = null;
		for (int i=0; i<parentDir.getSubDirs().size(); i++) {
			if (parentDir.getSubDirs().get(i).getName().equals(filePath)) {
				toDelete = parentDir.getSubDirs().get(i);
			}
		}
		if (toDelete.getSubDirs().size() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteSubDirs(GFSDir dir) {
		for (int i=0; i<dir.getSubDirs().size(); i++) {
			deleteSubDirs(dir.getSubDirs().get(i));
		}
		fileNamespace.remove(dir.getName());
	}
	
	/**
	 * Creates File
	 * @param srcDir
	 * @return
	 */
	public int createFile(String tgtdir, String filename) {
		GFSDir targetDir = getParent(tgtdir);
//		GFSDir targetDir = null;
//		for (int i=0; i<parentDir.getSubDirs().size(); i++) {
//			if (parentDir.getSubDirs().get(i).getName().equals(tgtdir)) {
//				targetDir = parentDir.getSubDirs().get(i);
//			}
//		}
		//tgtdir DNE
		if (targetDir == null) {
			return -1; 
		}
		
		GFSFile toAdd = null;
		for (int i=0; i<targetDir.getFiles().size(); i++) {
			if (targetDir.getFiles().get(i).getFilename().equals(filename)) {
				return -2;
			}
		}
		
		//can add, add now!
		toAdd = new GFSFile(filename);
		targetDir.addFile(toAdd);
		return 0;
	}
	
	/**
	 * Deletes File
	 * @param srcDir
	 * @return
	 */
	public int deleteFile(String tgtdir, String filename) {
		GFSDir targetDir = getParent(tgtdir);
		//tgtdir DNE
		if (targetDir == null) {
			return -1; 
		}
		
		GFSFile toDelete = null;
		for (int i=0; i<targetDir.getFiles().size(); i++) {
			if (targetDir.getFiles().get(i).getFilename().equals(filename)) {
				toDelete = targetDir.getFiles().get(i);
			}
		}
		
		if (toDelete == null) {
			return -2;
		}
		
		//can delete, delete now!
		targetDir.deleteFile(toDelete);
		return 0;
	}
	
	/**
	 * Lists the contents of a given directory
	 * @param srcDir
	 * @return
	 */
	public List<String> listDir(String filePath) {
		System.out.println("Filepath for list: " + filePath);
		// Create an ArrayList of directory tree starting at srcDir
		List<String> items = new ArrayList<>();
		GFSDir srcDir = getParent(filePath);
		// Get the sub-directories, recursively get nested dirs/files
		if (srcDir != null) {
			for (GFSDir sub : srcDir.getSubDirs()) {
				items.add(sub.getAbsPathName());
				// Recursively get nested dirs/files
				items.addAll(listDir(sub.getAbsPathName()));
			}
			// Get files within directory
			for (GFSFile file : srcDir.getFiles()) {
				// Add file to items to be returned
				items.add(srcDir.getAbsPathName() + "/" + file.getFilename());
			}
		}
		return items;
	}
	
	/**
	 * Returns the parent directory given a filePath string
	 * @param srcDir
	 * @return
	 */
	private GFSDir getParent(String srcDir) {
		System.out.println("Src Dir: " + srcDir);
		if (srcDir.equals("/")) {
			System.out.println("Parent Dir: /");
			return root;
		}
		// Split the srcDir path into individual parts
		String[] partsArr = srcDir.split("/");
		// Check if last part is in the fileNamespace map
		ArrayList<String> parts = new ArrayList<>(Arrays.asList(partsArr));
		ArrayList<String> toRemove = new ArrayList<>();
		for (String s : parts) {
			if (s.equals("")) {
				toRemove.add(s);
			}
		}
		// Remove spaces from parts ArrayList
		for (int i = 0; i < toRemove.size(); i++) {
			parts.remove(toRemove.get(i));
		}
		// Find parent in file namespace
		System.out.println("Parent Dir: " + parts.get(parts.size() - 1));
		return fileNamespace.get(parts.get(parts.size() - 1));
	}
	
}
