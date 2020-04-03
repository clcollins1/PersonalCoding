package com.revature.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.Image;
import com.revature.services.CompareFacesService;

@Service
public class CompareFacesServiceImpl implements CompareFacesService{
	
	String photo = "C:\\Users\\chris\\Documents\\CodeChallengeImgs\\doubleliftPic1.png";
	String photo2 = "C:\\Users\\chris\\Documents\\CodeChallengeImgs\\doubleliftPic2.png";
	
	
	BasicAWSCredentials credentials = new BasicAWSCredentials(System.getenv("Rekognition_Access_Key"),System.getenv("Rekognition_Secret_Key"));

	AmazonRekognition rekognition = AmazonRekognitionClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
	

	public Float getSimilarity(MultipartFile photo1, MultipartFile photo2)
	{
		
		
		if (extractBytes(photo1).equals(null))
		{
			System.out.println("img 1 not found");
			return 0f;
		}
		if (extractBytes(photo2).equals(null))
		{
			System.out.println("img 2 not found");
			return 0f;
		}
		ByteBuffer imageBytes = extractBytes(photo1);
		
		ByteBuffer image2Bytes = extractBytes(photo2);
		 CompareFacesRequest request = new CompareFacesRequest()
	                .withSourceImage(new Image()
	                        .withBytes(imageBytes))
	                .withTargetImage(new Image()
	                		.withBytes(image2Bytes))
	                .withSimilarityThreshold(0F);

		 Float sim = 0f;
	        try {

	            CompareFacesResult result = rekognition.compareFaces(request);
	            List <CompareFacesMatch> labels = result.getFaceMatches();

	            System.out.println("Detected Similarities for " + photo1);
	            System.out.println("Detected Similiarities for " + photo2);
	            for (CompareFacesMatch label: labels) {
	               System.out.println("Similary Percentage: " + label.getSimilarity().toString());
	               sim = label.getSimilarity();
	            }

	        } catch (AmazonRekognitionException e) {
	            e.printStackTrace();
	        }
	        
	        return sim;

	}
	
	public ByteBuffer extractBytes(MultipartFile file)
	{
		ByteBuffer imageBytes = null;
        try {
            imageBytes = ByteBuffer.wrap(file.getBytes());
        }catch (FileNotFoundException e)
        {
        	e.printStackTrace();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        
        return imageBytes;
	}
}
