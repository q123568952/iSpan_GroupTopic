package com.sns.dropcat.queryreturncalsses;

public class PersonalPageUser {
		
		private Integer userID;
		private String userAccount;
		private String userIcon;
		private Long postNum;
		private Long followingNum;
		private String userDisplayName;
		private String userIntroduction;
		private Long fanNum;
		
		

		

		public PersonalPageUser(Integer userID, String userAccount, String userIcon, Long postNum, Long followingNum,
				String userDisplayName, String userIntroduction, Long fanNum) {
			super();
			this.userID = userID;
			this.userAccount = userAccount;
			this.userIcon = userIcon;
			this.postNum = postNum;
			this.followingNum = followingNum;
			this.userDisplayName = userDisplayName;
			this.userIntroduction = userIntroduction;
			this.fanNum = fanNum;
		}

		public String getUserAccount() {
			return userAccount;
		}

		public void setUserAccount(String userAccount) {
			this.userAccount = userAccount;
		}

		public String getUserIcon() {
			return userIcon;
		}

		public void setUserIcon(String userIcon) {
			this.userIcon = userIcon;
		}

		public Long getPostNum() {
			return postNum;
		}

		public void setPostNum(Long postNum) {
			this.postNum = postNum;
		}

		public Long getFollowingNum() {
			return followingNum;
		}

		public void setFollowingNum(Long followingNum) {
			this.followingNum = followingNum;
		}

		public String getUserDisplayName() {
			return userDisplayName;
		}

		public void setUserDisplayName(String userDisplayName) {
			this.userDisplayName = userDisplayName;
		}

		public String getUserIntroduction() {
			return userIntroduction;
		}

		public void setUserIntroduction(String userIntroduction) {
			this.userIntroduction = userIntroduction;
		}

		public Integer getUserID() {
			return userID;
		}

		public void setUserID(Integer userID) {
			this.userID = userID;
		}

		public Long getFanNum() {
			return fanNum;
		}

		public void setFanNum(Long fanNum) {
			this.fanNum = fanNum;
		}
		
		
}
