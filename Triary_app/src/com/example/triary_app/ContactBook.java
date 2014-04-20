package com.example.triary_app;

public class ContactBook {
	private int id;
	private String title;
	private String author;
	private String country;
	private String img;
	private String status;
	
	public void setID(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCountry() {
		return country;
	}

	public String getImg() {
		return img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
