package model;

import java.io.File;

public class Item {

	private int id;
	private String name;
	private long number;
	private String path;
	private String date;
	private File file;

	public Item() {

	}

	public Item(int id, String name, long number, String path, String date) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.path = path;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
