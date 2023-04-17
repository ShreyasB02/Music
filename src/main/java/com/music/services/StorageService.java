package com.music.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;


@Service
public class StorageService {
    private final AmazonS3 space;

    public StorageService(){
        AWSCredentialsProvider awsCredentialsProvider=new AWSStaticCredentialsProvider(
            new BasicAWSCredentials("DO00YN8HLAE4Q4J9QREC", "W7vy35hK9rHASFIVptfgUo/6jfjuyGmkESTTGY1dvnE")
        );
        space=AmazonS3ClientBuilder.standard()
            .withCredentials(awsCredentialsProvider)
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://musicarchive1.sfo3.cdn.digitaloceanspaces.com", "sfo3"))
            .build();
    }

    public void getSongFileNames(){
        ListObjectsV2Result result=space.listObjectsV2("musicarchive1");
        List<S3ObjectSummary> objects=result.getObjectSummaries();
        objects.stream().forEach(object->{
            System.out.println(object.toString());
        });
    }
}
