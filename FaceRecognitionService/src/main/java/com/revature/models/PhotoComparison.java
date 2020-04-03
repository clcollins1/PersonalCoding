package com.revature.models;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PhotoComparison implements Serializable{
	
	private MultipartFile file1;
	private MultipartFile file2;
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	public MultipartFile getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file1 == null) ? 0 : file1.hashCode());
		result = prime * result + ((file2 == null) ? 0 : file2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhotoComparison other = (PhotoComparison) obj;
		if (file1 == null) {
			if (other.file1 != null)
				return false;
		} else if (!file1.equals(other.file1))
			return false;
		if (file2 == null) {
			if (other.file2 != null)
				return false;
		} else if (!file2.equals(other.file2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhotoComparison [file1=" + file1 + ", file2=" + file2 + "]";
	}
	public PhotoComparison(MultipartFile file1, MultipartFile file2) {
		super();
		this.file1 = file1;
		this.file2 = file2;
	}
	public PhotoComparison() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
