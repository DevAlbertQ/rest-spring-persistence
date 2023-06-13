package br.com.albert.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date tymeStamp;
	private String message;
	private String details;
	
	
	
	public ExceptionResponse(Date tymeStamp, String message, String details) {
		this.tymeStamp = tymeStamp;
		this.message = message;
		this.details = details;
	}
	
	
	public Date getTymeStamp() {
		return tymeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	

}
