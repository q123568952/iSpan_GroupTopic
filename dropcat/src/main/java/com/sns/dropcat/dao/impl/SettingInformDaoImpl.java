package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.SettingInformDao;
import com.sns.dropcat.model.SettingInform;
import com.sns.dropcat.queryreturncalsses.BlockController;
import com.sns.dropcat.replyfomat.MessageConstant;
import com.sns.dropcat.replyfomat.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

//@Mapper
@Repository
public class SettingInformDaoImpl implements SettingInformDao {

	@PersistenceContext
	EntityManager entityManager;

//	SELECT 所有的列資料
	@Override
	public List<SettingInform> findAll(Integer userId) {

		String hql = "FROM SettingInform WHERE userId = :userId";
		List<SettingInform> settingInformlist = entityManager.createQuery(hql, SettingInform.class).setParameter("userId", userId).getResultList();
		System.out.println(settingInformlist.toString());
		return settingInformlist;

	}

	// 使用UserId 來找是否已經有用戶的設定資料已經存在資料庫裡面了
	@Override
	public List<SettingInform> findOne(Integer userId) {

		List<SettingInform> findOne = entityManager
				.createQuery("SELECT  SI FROM SettingInform SI LEFT JOIN UserInfo UI ON SI.userId = UI.id AND SI.userId = :userId"
				, SettingInform.class)
				.setParameter("userId", userId).getResultList();

		if (findOne.size() == 0) {
			return null;

		} else {
			return findOne;
		}

	}

	@Override
	public Result getBlockController(Integer id) {
		List<BlockController> res = entityManager.createQuery(
				"SELECT DISTINCT u.id, si.openState, b.blockedUserID, blocku.usericon, blocku.userAccount FROM UserInfo u LEFT JOIN Blacklist b ON u.id = b.blockerID LEFT JOIN SettingInform si ON u.id = si.userId LEFT JOIN UserInfo blocku ON b.blockedUserID = blocku.id\r\n"
				+ "WHERE u.id = :id",
				BlockController.class)
				.setParameter("id", id)
				.getResultList();
		return new Result(true, MessageConstant.GET_Setting_Success, res);
	}

	// 增加列資料到SettingInform mySQL表
	@Override
	public Result addOne(SettingInform settingInform, Integer userId) {

		entityManager.persist(settingInform);

		return new Result(true, MessageConstant.ADD_User_SUCCESS, null);

	}

//	更新列資料到SettingInform MySQL表
	public Result update(SettingInform settingInform, @Param("userId") Integer userId) {

//		TypedQuery<SettingInform> query  = entityManager.createQuery(
//				"UPDATE SettingInform  SI "
//				+ "SET SI.postInformState = : postInformState, SI.followInformState = :followInformState "
//				+ ", SI.likeInformState =: likeInformState"
//				+ "WHERE SI.userId= :userId", SettingInform.class)
//				.setParameter("postInformState", settingInform.getPostInformState())
//				.setParameter("followInformState", settingInform.getFollowInformState() )
//				.setParameter("likeInformState" , settingInform.getLikeInformState())
//				.setParameter("userId" , settingInform.getUserId());
//		

//		List<SettingInform> updateSettingInform_List=query.getResultList();
// Query: UPDATE SettingInform SET followInformState = 1, gender = 1, likeInformState = 1, openState =1, postInformState = 1 WHERE userId= 222258
		entityManager
				.createQuery("UPDATE SettingInform SET followInformState = :followInformState, gender = :gender, likeInformState = :likeInformState, openState = :openState, postInformState = :postInformState WHERE userId= 222258")
				.setParameter("followInformState", settingInform.getFollowInformState())
				.setParameter("likeInformState", settingInform.getLikeInformState())
				.setParameter("openState", settingInform.getOpenState())
				.setParameter("postInformState", settingInform.getPostInformState())
				.setParameter("userId", settingInform.getUserId())
				.executeUpdate();

		return new Result(true, MessageConstant.UPDATE_SettingInform_SUCCESS, null);
	}
	
	

}
