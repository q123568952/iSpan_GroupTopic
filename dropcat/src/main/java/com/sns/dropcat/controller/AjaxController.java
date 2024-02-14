package com.sns.dropcat.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sns.dropcat.model.CollectionPost;
import com.sns.dropcat.model.Comments;
import com.sns.dropcat.model.Likes;
import com.sns.dropcat.model.Post;
import com.sns.dropcat.model.PostImg;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.model.tellYouHaveLike;
import com.sns.dropcat.pojo.LetYouKnowWhoIsYourDaddy;
import com.sns.dropcat.replyfomat.Result;
import com.sns.dropcat.service.AddPersonalPostService;
import com.sns.dropcat.service.ExploreService;
import com.sns.dropcat.service.InformationService;
import com.sns.dropcat.service.PopOutService;
import com.sns.dropcat.service.PosttestService;
import com.sns.dropcat.service.UserService;
import com.sns.dropcat.ws.WSServer2;
import com.utils.JwtParseAllClaim;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AjaxController {

	UserService userService;
	ExploreService exploreService;
	PopOutService popOutService;
	AddPersonalPostService addPersonalPostService;
	RelationshipService relationshipService;
	WSServer2 wsSoCool;
	InformationService informationService;
//	之後要拿掉
	PosttestService posttestService;

	@Autowired
	public AjaxController(UserService userService, ExploreService exploreService, PosttestService posttestService,
			PopOutService popOutService, AddPersonalPostService addPersonalPostService,
			RelationshipService relationshipService, WSServer2 wsSoCool, InformationService informationService) {
		super();
		this.userService = userService;
		this.exploreService = exploreService;
		this.posttestService = posttestService;
		this.popOutService = popOutService;
		this.addPersonalPostService = addPersonalPostService;
		this.relationshipService = relationshipService;
		this.wsSoCool = wsSoCool;
		this.informationService = informationService;
	}
////  取得explorepost初始資訊
//	@GetMapping("/morganexplorepost")
//	public Result postdata() {
//		Result res = exploreService.getExplorePost();
//		return res;
//	}

//  取得explorepost加載資訊
	@GetMapping("/morganexplorepost/{page}/{uploadamount}/{nowTime}")
	public Result uploadpostdata(@PathVariable Integer page, @PathVariable Integer uploadamount,
			@PathVariable Date nowTime, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		Result res = exploreService.uploadExplorePost(page, uploadamount, nowTime, userId);
		return res;
	}

//	取的noprefer資料
	@GetMapping("/morganexplorepostnoprefer/{page}/{uploadamount}/{lastNum}/{nowTime}")
	public Result uploadpostnopreferdata(@PathVariable Integer page, @PathVariable Integer uploadamount,
			@PathVariable Date nowTime, @PathVariable Integer lastNum, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();

		Result res = exploreService.uploadpostnopreferdata(page, uploadamount, nowTime, userId, lastNum);

		return res;
	}

//	取得map初始標籤資訊
	@GetMapping("/morganexploremap/{lat}/{lng}/{dis}")
	public Result mapdata(@PathVariable Double lat, @PathVariable Double lng, @PathVariable Integer dis) {
		Result res = exploreService.getMapPost(lat, lng, dis);
		return res;
	}

//	取得彈出貼文資料
	@GetMapping("/morganpopout/{postId}")
	public Result getpopoutdata(@PathVariable String postId, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		Result res = popOutService.getPopOutData(userId, postId);

		return res;
	}

//刪除貼文
	@DeleteMapping("/morgandeletepost/{postId}")
	public Result deletepost(@PathVariable String postId, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		Result res = popOutService.deletepost(postId, userId);
		return res;
	}

//取得貼文讚數
	@GetMapping("/morganpopoutliked/{postId}")
	public Result countLiked(@PathVariable String postId) {
		Result res = popOutService.getLikeNum(postId);

		return res;
	}

//按讚
	@PostMapping("/morganpopoutliked")
	public Result addLiked(@RequestBody Likes like, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		like.setUserLikedId(userInformation.getId());
		Result res = popOutService.insertLiked(like);
		try {
			List<tellYouHaveLike> owner = informationService.find_WhoOwnThisPost(like.getPostContextId());
			for (int i = 0; i < owner.size(); i++) {
				if (owner.get(i).getGive_You_Like_UserId().equals(like.getUserLikedId())) {
					String json = JSONObject.toJSONString(owner.get(i));
					System.out.println(owner.get(i).getPost_userId());
					wsSoCool.sendToOne(json, owner.get(i).getPost_userId());
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}

//收回讚
	@DeleteMapping("/morganpopoutliked")
	public Result deleteLiked(@RequestBody Likes like, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		like.setUserLikedId(userInformation.getId());
		Result res = popOutService.deleteLiked(like);
		return res;
	}

	@PostMapping("/morganpopoutcollected")
	public Result addCollected(@RequestBody CollectionPost collectionPost, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		collectionPost.setUserID(userInformation.getId());
		Result res = popOutService.insertCollected(collectionPost);
		return res;
	}

//收藏
	@DeleteMapping("/morganpopoutcollected")
	public Result deleteCollected(@RequestBody CollectionPost collectionPost, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		collectionPost.setUserID(userInformation.getId());
		Result res = popOutService.deleteCollected(collectionPost);
		return res;
	}
//取消收藏
	@PostMapping("/morganpopoutcomment")
	public Result addComment(@RequestBody Comments comments, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		comments.setUserId(userInformation.getId());
		Result res = popOutService.insertComment(comments);
		return res;
	}

//新增貼文
	@PostMapping("/morganaddpostpersonalpost")
	public Result addPersonalPost(@RequestBody Map<String, Object> postinfo, HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		postinfo.put("userId", userId);
		Post post = addPersonalPostService.packPost(postinfo);
//		確認type後放入
		post = addPersonalPostService.getType(post);
		List<PostImg> postImgs = addPersonalPostService.packPostImg(postinfo);
		Result res = addPersonalPostService.addPost(post, postImgs);
		return res;
	}

//顯示頭像
	@GetMapping("/morganshowicon")
	public Result showIcon(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		Result res = userService.getUserIcon(userId);
		return res;
	}

//顯示被追蹤清單
	@GetMapping("/whofollowme")
	public Result getWhoFollowMe(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		Result res = relationshipService.whofollowme(userId);
		return res;
	}
	
//顯示我追蹤清單
	@GetMapping("/whoifollow")
	public Result getwhoifollow(HttpServletRequest req) {
		LetYouKnowWhoIsYourDaddy userInformation = JwtParseAllClaim.letYouKnowWhoIsYourDaddy(req);
		String userId = userInformation.getId().toString();
		Result res = relationshipService.whoifollow(userId);
		return res;
	}

// 從前端傳入 userInfo JSON物件
	@PostMapping("/adduser")
	@ResponseBody
	public Result findAll(@RequestBody UserInfo userInfo, Model model) {
		Result res = userService.addUser(userInfo);
		return res;
	}
}
