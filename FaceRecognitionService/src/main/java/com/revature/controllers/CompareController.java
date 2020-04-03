package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.PhotoComparison;
import com.revature.services.CompareFacesService;

@RestController
@CrossOrigin
public class CompareController {

	@Autowired
	private CompareFacesService compare;
	
	
	@PostMapping(value = "/compareFaces", consumes = { "multipart/form-data" })
	public ResponseEntity<Float> compareUploadedFaces(@ModelAttribute PhotoComparison files)
	{
		Float simalarity = compare.getSimilarity(files.getFile1(), files.getFile2());
		return new ResponseEntity<>(simalarity, HttpStatus.OK);
	}
	
}
