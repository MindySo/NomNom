package com.ssafy.nomnom.model.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nomnom.model.dao.AllergyDao;
import com.ssafy.nomnom.model.dao.UserDao;
import com.ssafy.nomnom.model.dto.Allergy;
import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.dto.user.UserRegisterRequest;
import com.ssafy.nomnom.model.dto.user.UserUpdateRequest;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final AllergyDao allergyDao;

	public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, AllergyDao allergyDao) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
		this.allergyDao = allergyDao;
	}

	// 회원가입
	@Override
	@Transactional
	public int registerUserWithAllergies(UserRegisterRequest request) {
		// 1. 유저 객체 생성
		User user = new User();
		user.setUserId(request.getUserId());
		user.setUserEmail(request.getUserEmail());
		String encodedPw = passwordEncoder.encode(request.getUserPw());
		user.setUserPw(encodedPw);
		user.setUserBirthday(request.getUserBirthday());
		user.setUserGender(request.getUserGender());
		user.setUserHeight(request.getUserHeight());
		user.setUserWeight(request.getUserWeight());
		user.setUserRole("USER");

		// 2. 유저 등록
		int result = userDao.insertUser(user);
		if (result == 0)
			return 0;

		// 3. 방금 등록한 유저 다시 조회해서 user_no 확보
		User insertedUser = userDao.getUserByEmail(user.getUserEmail());
		int userNo = insertedUser.getUserNo();

		// 4. user_allergy 테이블에 등록
		if (request.getAllergyIds() != null) {
			for (Integer allergyId : request.getAllergyIds()) {
				userDao.insertUserAllergy(userNo, allergyId);
			}
		}

		return 1;
	}

	// 로그인
	@Override
	public User loginByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	// 회원정보 수정(마이페이지에서)
	@Override
	@Transactional
	public int updateUserProfile(UserUpdateRequest request) {
		User user = new User();
		user.setUserNo(request.getUserNo());
		user.setUserPw(request.getUserPw());
		user.setUserBirthday(request.getUserBirthday());
		user.setUserGender(request.getUserGender());
		user.setUserHeight(request.getUserHeight());
		user.setUserWeight(request.getUserWeight());

		int result = userDao.updateUser(user);

		if (request.getAllergyIds() != null) {
			userDao.deleteUserAllergies(user.getUserNo());
			for (Integer allergyId : request.getAllergyIds()) {
				userDao.insertUserAllergy(user.getUserNo(), allergyId);
			}
		}

		return result;
	}

	// 회원탈퇴
	@Override
	public int deleteUserAccount(User user) {
		return userDao.deleteUser(user);
	}

	// 소셜 로그인 시 사용자 등록용
	@Override
	public int registerUser(User user) {
		// 누락 방지용 기본값 세팅
		if (user.getUserRole() == null) {
			user.setUserRole("USER");
		}
		return userDao.insertUser(user);
	}

	// 소셜 로그인 후 프로필 추가용
	@Override
	@Transactional
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	// 알러지 조회
	@Override
	public List<Allergy> getAllAllergies() {
		return allergyDao.findAll();
	}
}
