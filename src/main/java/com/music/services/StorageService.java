package com.music.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService{
    private final AmazonS3 space;

    public StorageService(){
        AWSCredentialsProvider awsCredentialsProvider=new AWSStaticCredentialsProvider(
            new BasicAWSCredentials("DO00YN8HLAE4Q4J9QREC", "W7vy35hK9rHASFIVptfgUo/6jfjuyGmkESTTGY1dvnE")
        );
        space=AmazonS3ClientBuilder.standard()
            .withCredentials(awsCredentialsProvider)
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("sfo3.digitaloceanspaces.com", "sfo3"))
            .build();
    }

    public List<String> getSongFileNames(){
        ListObjectsV2Result result=space.listObjectsV2("musicarchive1");
        List<S3ObjectSummary> objects=result.getObjectSummaries();
        return objects.stream()
                .map(s3ObjectSummary -> {
                    return s3ObjectSummary.getKey();
                }).collect(Collectors.toList());

        }
        public List<String> getSongQueue(){
            List<String> c = new ArrayList<String>();
            c.add("01 Jimpak Chipak.mp3");
            c.add("Flowers.mp3");
            c.add("test2.mp3");
            return c;
        }

    public void uploadSong(MultipartFile file) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        space.putObject(new PutObjectRequest("musicarchive1",file.getOriginalFilename(),file.getInputStream(),objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }
}