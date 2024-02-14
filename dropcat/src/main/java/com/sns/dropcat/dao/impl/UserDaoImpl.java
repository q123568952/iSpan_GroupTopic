package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.UserDao;
import com.sns.dropcat.model.UserInfo;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {

	// like session
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<UserInfo> findAll() {
		String hql = "FROM UserInfo";
		List<UserInfo> userlist = entityManager.createQuery(hql, UserInfo.class).getResultList();
		System.out.println(userlist.toString());
		return userlist;
	}

	// 2024/1/23 註冊新用戶資料
	@Override
	public Result addUser(UserInfo userInfo) {
		entityManager.persist(userInfo);
		return new Result(true, MessageConstant.ADD_User_SUCCESS, null);
	}

	// 搜尋是否有這個使用者的註冊資料
	@Override
	public UserInfo selectUser(UserInfo userInfo) {
		// Query query = entityManager.createQuery("SELECT ui FROM UserInfo ui WHERE
		// userAccount=?1 and ");

		// UserInfo z= entityManager.createQuery("SELECT * FROM UserInfo WHERE
		// userAccount= :xxx ", UserInfo.class).getSingleResult();
		// UserInfo z= entityManager.createQuery("FROM UserInfo WHERE userAccount= :xxx
		// ", UserInfo.class).getSingleResult();

		// z.setuserAccount(userInfo.getuserAccount());
		// System.out.println(z);

		// z.set("xxx", name);

		TypedQuery<UserInfo> query = entityManager.createQuery(
				"select u from UserInfo u "
						+ "where u.userAccount = :zzz ",
				UserInfo.class);

		// userInfo.getuserAccount() 為從前端傳過來的資料
		// 把 u.userAccount 改成 userInfo.getuserAccount()
		query.setParameter("zzz", userInfo.getuserAccount());

		// 得到單1筆列資料，就是1個UserInfo物件
		UserInfo resultList1 = query.getSingleResult();
		System.out.println("這裡是UserDaoImpl##########################################");
		System.out.println("從後端傳回去到前端的資料 " + resultList1);
		System.out.println("這裡是UserDaoImpl結束-----------------------------------------------------------------");

		return resultList1;
	}

	// 2024/1/23 搜尋是否有這個使用者的註冊資料
	@Override
	public Result getUserIcon(String userId) {
		List<Object[]> result = entityManager
				.createNativeQuery("select usericon,userAccount,introduction,gender from UserInfo where id=:userId")
				.setParameter("userId", userId).getResultList();
		return new Result(true, null, result);
	}

	@Override
	public Result getRegisterUser(UserInfo userInfo) {

		// List<UserInfo> fromRegisterGetUser = entityManager.createQuery(
		// "SELECT U.id ID, U.userAccount userAccount, U.usericon userIcon, "
		// + "FROM UserInfo U L "
		// + " WHERE U.userAccount = :zzzzzzzzzz",UserInfo.class)
		// .setParameter("zzzzzzzzzz", userInfo.getuserAccount())
		// .getResultList();

		List<UserInfo> fromRegisterGetUser = entityManager.createQuery(
				"FROM UserInfo U "
						+ " WHERE U.userAccount = :zzzzzzzzzz",
				UserInfo.class)
				.setParameter("zzzzzzzzzz", userInfo.getuserAccount())
				.getResultList();

		if (fromRegisterGetUser.size() > 0) {
			System.out.println("這裡是UserDaoImpl 成功查詢到用戶資料會是List<UserInfo>型態========"
					+ fromRegisterGetUser);
			System.out.println("這裡是UserDaoImpl 成功查詢到用戶資料會是UserInfo型態========"
					+ fromRegisterGetUser.get(0));

			return new Result(true, MessageConstant.GET_User_SUCCESS, fromRegisterGetUser.get(0));
		} else {
			System.out.println("這裡是UserDaoImpl 沒有查詢到用戶資料");

			return new Result(false, MessageConstant.GET_User_FAILED, null);

		}

		// UserInfo fromRegisterGetOneUser = entityManager.createQuery(
		//
		// "FROM UserInfo U "
		// + " WHERE U.userAccount = :zzzzzzzzzz", UserInfo.class)
		// .setParameter("zzzzzzzzzz", userInfo.getuserAccount())
		// .getSingleResult();

		// return new Result(true, MessageConstant.GET_User_SUCCESS,
		// fromRegisterGetOneUser);

	}

	@Override
	public Result updateUserProfile(UserInfo userInfo) {
		 entityManager.createQuery("UPDATE UserInfo SET usericon=:setUserIcon, introduction=:setUserIntroDuction, gender=:setUserGender WHERE id=:getUserId")
				.setParameter("getUserId", userInfo.getId())
				.setParameter("setUserIcon", userInfo.getUsericon())
				.setParameter("setUserGender", userInfo.getGender())
				.setParameter("setUserIntroDuction", userInfo.getIntroduction())
				.executeUpdate(); 	
				
		return new Result(true, MessageConstant.DELETE_Liked_SUCCESS, null);
	}

}
