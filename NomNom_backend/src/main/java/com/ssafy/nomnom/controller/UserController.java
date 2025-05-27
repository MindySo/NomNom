package com.ssafy.nomnom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nomnom.model.dto.Allergy;
import com.ssafy.nomnom.model.dto.user.CustomUserDetails;
import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.dto.user.UserProfileSetupRequest;
import com.ssafy.nomnom.model.dto.user.UserRegisterRequest;
import com.ssafy.nomnom.model.dto.user.UserUpdateRequest;
import com.ssafy.nomnom.model.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 회원가입
	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest request) {
		int result = userService.registerUserWithAllergies(request);
		if (result > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
		}
	}

//	//이메일 중복 확인 
//	@GetMapping("/check-email")
//	public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
//	    boolean exists = userService.existsByEmail(email);
//	    return ResponseEntity.ok(!exists); // true = 사용 가능
//	}
//
//	//닉네임 중복 확인 
//	@GetMapping("/check-nickname")
//	public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
//	    boolean exists = userService.existsByNickname(nickname);
//	    return ResponseEntity.ok(!exists); // true = 사용 가능
//	}

	// 마이페이지 조회
	@GetMapping("/me")
	public ResponseEntity<?> getMyPage(@AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername(); // 로그인한 사용자 이메일 추출

		User user = userService.loginByEmail(email); // 이미 있는 서비스 메서드 사용

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보를 찾을 수 없습니다.");
		}

		return ResponseEntity.ok(user); // 마이페이지 정보 반환
	}

	// 회원정보 수정
	@PutMapping("/{userNo}")
	public ResponseEntity<String> updateUserProfile(@PathVariable int userNo, @RequestBody UserUpdateRequest request,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null || userDetails.getUserNo() != userNo) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("본인만 수정할 수 있습니다.");
		}

		request.setUserNo(userNo);
		int result = userService.updateUserProfile(request);

		return result > 0 ? ResponseEntity.ok("회원정보가 수정되었습니다.")
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원정보 수정 실패");
	}

	// 회원탈퇴
	@DeleteMapping
	public ResponseEntity<String> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
		}

		String email = userDetails.getUsername();
		User user = userService.loginByEmail(email);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
		}

		int result = userService.deleteUserAccount(user);
		if (result > 0) {
			return ResponseEntity.ok("회원탈퇴 완료");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 실패");
		}
	}

	// 프로필 설정 API 추가
	@PostMapping("/profile/setup")
	public ResponseEntity<String> setupUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
			@RequestBody UserProfileSetupRequest request) {
		if (userDetails == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
		}

		if (request.getUserBirthday() == null) {
			return ResponseEntity.badRequest().body("생년월일은 필수 항목입니다.");
		}

		User user = new User();
		user.setUserNo(userDetails.getUserNo());
		user.setUserBirthday(request.getUserBirthday());
		user.setUserGender(request.getUserGender());
		user.setUserHeight(request.getUserHeight());
		user.setUserWeight(request.getUserWeight());

		int result = userService.updateUser(user);
		return result > 0 ? ResponseEntity.ok("추가 정보 저장 완료")
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 정보 저장 실패");
	}

	// 알레르기 전체 조회
	@GetMapping("/allergies")
	public ResponseEntity<List<Allergy>> getAllAllergies() {
		List<Allergy> allergies = userService.getAllAllergies(); // 서비스에서 가져오기
		return ResponseEntity.ok(allergies);
	}

	// UserService.java (인터페이스)에는 이미 updateUser(User user) 존재함

	// UserServiceImpl.java 내부에도 updateUser(User user) 호출 방식은 그대로 사용 가능

}
