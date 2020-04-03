package com.revature.services;

import java.nio.ByteBuffer;

import org.springframework.web.multipart.MultipartFile;

public interface CompareFacesService {
	
	public ByteBuffer extractBytes(MultipartFile fileStream);
	public Float getSimilarity(MultipartFile photo1, MultipartFile photo2);

}
