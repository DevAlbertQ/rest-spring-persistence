package br.com.albert.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class BooksDTO extends RepresentationModel<BooksDTO>{

	private Integer key;
	private String author;
	private Date launchDate;
	private Double price;
	private String title;
	
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
