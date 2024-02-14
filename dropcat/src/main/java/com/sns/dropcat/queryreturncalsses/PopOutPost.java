package com.sns.dropcat.queryreturncalsses;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PopOutPost {
 private List<String> postimg;
 private String acconticon;
 private String accountname;
 private String posttext;
 private Date posttime;
 private List<Map<String,Object>> postMessage;
 private Double lat;
 private Double lng;
 private Long totallike;
 private Date latestliketime;
 private Long liked;
 private Long collected;
public List<String> getPostimg() {
	return postimg;
}
public void setPostimg(List<String> postimg) {
	this.postimg = postimg;
}
public String getAcconticon() {
	return acconticon;
}
public void setAcconticon(String acconticon) {
	this.acconticon = acconticon;
}
public String getAccountname() {
	return accountname;
}
public void setAccountname(String accountname) {
	this.accountname = accountname;
}
public String getPosttext() {
	return posttext;
}
public void setPosttext(String posttext) {
	this.posttext = posttext;
}
public Date getPosttime() {
	return posttime;
}
public void setPosttime(Date posttime) {
	this.posttime = posttime;
}
public List<Map<String, Object>> getPostMessage() {
	return postMessage;
}
public void setPostMessage(List<Map<String, Object>> postMessage) {
	this.postMessage = postMessage;
}
public Double getLat() {
	return lat;
}
public void setLat(Double lat) {
	this.lat = lat;
}
public Double getLng() {
	return lng;
}
public void setLng(Double lng) {
	this.lng = lng;
}
public Long getTotallike() {
	return totallike;
}
public void setTotallike(Long totallike) {
	this.totallike = totallike;
}
public Date getLatestliketime() {
	return latestliketime;
}
public void setLatestliketime(Date latestliketime) {
	this.latestliketime = latestliketime;
}
public Long getLiked() {
	return liked;
}
public void setLiked(Long liked) {
	this.liked = liked;
}
public Long getCollected() {
	return collected;
}
public void setCollected(Long collected) {
	this.collected = collected;
}
@Override
public String toString() {
	return "PopOutPost [postimg=" + postimg + ", acconticon=" + acconticon + ", accountname=" + accountname
			+ ", posttext=" + posttext + ", posttime=" + posttime + ", postMessage=" + postMessage + ", lat=" + lat
			+ ", lng=" + lng + ", totallike=" + totallike + ", latestliketime=" + latestliketime + ", liked=" + liked
			+ ", collected=" + collected + "]";
}
 
 
}
