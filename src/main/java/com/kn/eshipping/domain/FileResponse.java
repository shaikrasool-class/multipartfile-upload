package com.kn.eshipping.domain;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author rasool
 *
 */
@Document
public class FileResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	private String name;
	
	private String uri;
	
	private String type;
	
	private long size;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public FileResponse(String id, String name, String uri, String type, long size) {
		super();
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.size = size;
	}

	public FileResponse(String name, String uri, long size, String type) {
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.size = size;
	}

	public FileResponse(String fileName, String uri2, long size2, HttpServletResponse res) {
		// TODO Auto-generated constructor stub
	}



}