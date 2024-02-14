package com.sns.dropcat.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sns.dropcat.dao.AwsS3Dao;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.model.UserInfo;
@Repository
public class AwsS3DaoImpl implements AwsS3Dao {
	
	@Value("${myaws.accesskey}")
	private String AWSaccesskey;
	@Value("${myaws.secretkey}")
	private String AWSsecretkey;
	@Value("${myaws.region}")
	private String AWSregion;


//上傳貼文圖片
	@Override
	public List<PostImg> uploadImg(List<PostImg> postImgs) {
//		AWS S3連線 
		AWSCredentials credentials = new BasicAWSCredentials(
				AWSaccesskey, //<AWS accesskey>
				AWSsecretkey //<AWS secretkey>
				);

		
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(AWSregion) //AWS s3 區域
				  .build();
		postImgs.forEach((e)->{
//			包裝imgString成InputStream
			String img = e.getImgURL();
			byte[] imagedata = DatatypeConverter.parseBase64Binary(img.substring(img.indexOf(",") + 1));
			InputStream fis = new ByteArrayInputStream(imagedata);
			String imgname = UUID.randomUUID().toString().replace("-", "");
//			傳至s3
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(imagedata.length);
			metadata.setContentType("image/png");
			metadata.setCacheControl("public, max-age=31536000");
			s3client.putObject("myjavatest20231207",  imgname+".png", fis, metadata);
			s3client.setObjectAcl("myjavatest20231207",  imgname+".png", CannedAccessControlList.PublicRead);
			e.setImgURL(s3client.getUrl("myjavatest20231207", imgname+".png").toString());
		});
		return postImgs;
	}

//上傳頭像圖片
	@Override
	public UserInfo uploadUserIcon(UserInfo userinfo) {
//		AWS S3連線 
		AWSCredentials credentials = new BasicAWSCredentials(
				AWSaccesskey, //<AWS accesskey>
				  AWSsecretkey //<AWS secretkey>
				);

		
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(AWSregion) //AWS s3 區域
				  .build();

		String img = userinfo.getUsericon();

		if(img.indexOf("myjavatest20231207")==-1){
			byte[] imagedata = DatatypeConverter.parseBase64Binary(img.substring(img.indexOf(",") + 1));
			InputStream fis = new ByteArrayInputStream(imagedata);
			String imgname = UUID.randomUUID().toString().replace("-", "");
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(imagedata.length);
			metadata.setContentType("image/png");
			metadata.setCacheControl("public, max-age=31536000");
			s3client.putObject("myjavatest20231207",  imgname+".png", fis, metadata);
			s3client.setObjectAcl("myjavatest20231207",  imgname+".png", CannedAccessControlList.PublicRead);
			userinfo.setUsericon(s3client.getUrl("myjavatest20231207", imgname+".png").toString());
		}

		return userinfo;

	}

//刪除貼文圖片
	@Override
	public Boolean deleteawspost(List<PostImg> imgdelete) {
//		AWS S3連線 
		AWSCredentials credentials = new BasicAWSCredentials(
				AWSaccesskey, //<AWS accesskey>
				  AWSsecretkey //<AWS secretkey>
				);

		
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(AWSregion) //AWS s3 區域
				  .build();
		
		try {
		for (int i = 0; i < imgdelete.size(); i++) {
			
			String temp =imgdelete.get(i).getImgURL();
			temp=temp.replace("https://myjavatest20231207.s3.ap-northeast-3.amazonaws.com/", "");
			System.out.println(temp);
			s3client.deleteObject(new DeleteObjectRequest("myjavatest20231207",temp));
		}
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}



	
}
