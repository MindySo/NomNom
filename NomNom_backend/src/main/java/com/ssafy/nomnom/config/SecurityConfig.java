package com.ssafy.nomnom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.nomnom.model.dao.UserDao;
import com.ssafy.nomnom.model.service.CustomUserDetailsService;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

/**
 * Spring Security 설정 클래스 - JWT 인증 설정 - OAuth2 로그인 성공 핸들러 연결
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetailsService userDetailsService;
	private final UserDao userDao; // ✅ UserDao 주입 (소셜로그인 핸들러에 넘김)

	public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userDetailsService,
			UserDao userDao) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
		this.userDao = userDao;
	}

	// 원 코드
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//				// ✅ 기본 인증 비활성화
//				.httpBasic().disable()
//				.formLogin().disable()
//				// ✅ CSRF 비활성화 (REST API용)
//				.csrf().disable()
//				// ✅ CORS 허용
//				.cors().and()
//
//				// ✅ 요청 인증 규칙 설정
//			    .authorizeHttpRequests()
//			    .requestMatchers("/api/auth/**", "/login/**", "/oauth2/**").permitAll() // ✅ 꼭 이거 추가!
//			    .requestMatchers("/api/boards/search").permitAll()
//			    .anyRequest().authenticated().and()
//
//				// ✅ 소셜 로그인 설정
//				.oauth2Login().successHandler(new OAuth2LoginSuccessHandler(jwtTokenProvider, userDao)) // 직접 생성자 주입
//				.and()
//
//				// ✅ 세션 비활성화 (JWT 방식)
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//				// ✅ JWT 인증 필터 등록
//				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService),
//						UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}

	@Bean
<<<<<<< HEAD
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .cors().and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/user").permitAll() // 회원가입
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/**", "/login/**", "/oauth2/**").permitAll() // ✅ 로그인 허용
                .requestMatchers("/api/boards/**").permitAll()
                .requestMatchers("/api/mission/**").permitAll()
                .requestMatchers("/api/meal/**").permitAll()
                .requestMatchers("/api/comments/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
//            .oauth2Login().successHandler(new OAuth2LoginSuccessHandler(jwtTokenProvider, userDao))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService),
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	
=======
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic().disable().formLogin().disable().csrf().disable().cors().and()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/user").permitAll() // 회원가입
						.requestMatchers("/api/auth/login").permitAll()
						.requestMatchers("/api/auth/**", "/login/**", "/oauth2/**").permitAll() // ✅ 로그인 허용
						.requestMatchers("/api/boards/**").permitAll().requestMatchers("/api/mission/**").permitAll()
						.requestMatchers("/api/meal/**").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
						.permitAll().anyRequest().authenticated())
				.oauth2Login().successHandler(new OAuth2LoginSuccessHandler(jwtTokenProvider, userDao)).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
>>>>>>> df4fb0b8e8dca16871042601ed97fb7d56c091b1

	// ✅ 비밀번호 암호화 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}