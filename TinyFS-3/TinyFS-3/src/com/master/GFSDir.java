package com.master;

import java.util.ArrayList;
import java.util.List;

public class GFSDir {

	private String name;				// The name of the directory
	private String absPathName;		// The absolute path of the directory
	private GFSDir parent;			// The parent directory
	private List<GFSFile> files;		// List of files within directory
	private List<GFSDir> subDirs;	// List of sub-directories
	
	/**
	 * Parameterized constructor for GFSDir
	 * Creates a new directory object with given name
	 * and parent directory. Initializes empty lists
	 * for files and sub-directories
	 * @param name
	 * @param root
	 */
	public GFSDir(String name, GFSDir parent) {
		this.name = name;
		this.parent = parent;
		this.files = new ArrayList<>();
		this.subDirs = new ArrayList<>();
		this.absPathName = getAbsPath();
	}
	
	/**
	 * Creates a new sub-directory within this directory
	 * @param filePath
	 * @return
	 */
	public GFSDir createSubDir(String filePath) {
		for (GFSDir sub : subDirs) {
			if (sub.name.equals(filePath)) {
				// Sub-directory already exists
				return null;
			}
		}
		// Sub-directory doesn't exist, so create it
		GFSDir newDir = new GFSDir(filePath, this);
		subDirs.add(newDir);
		return newDir;
	}
	
	/**
	 * Recursively constructs the absolute file path of the directory
	 * since the relationship between parent and sub-directory is inherently
	 * recursive
	 * @return absolute file path of directory
	 */
	private String getAbsPath() {
		if (parent == null) {
			// Terminating condition when the srcDir is encountered
			return "";
		} else {
			// Recursively call getAbsPath on parent to create abs path
			return parent.getAbsPath() + "/" + name;
		}
	}
	
	/**
	 * Getter method for subDirs
	 * @return
	 */
	public List<GFSDir> getSubDirs() {
		return subDirs;
	}
	
	/**
	 * Getter method for files within directory
	 * @return
	 */
	public List<GFSFile> getFiles() {
		return files;
	}
	
	/**
	 * Getter method for absPathName
	 * @return
	 */
	public String getAbsPathName() {
		return absPathName;
	}
	
	/**
	 * Getter method for name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
}
