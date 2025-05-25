package com.ssafy.nomnom.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.nomnom.model.service.CustomUserDetailsService;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//JWT 인증 필터: 매 요청마다 한번 실행됨 
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetailsService userDetailsService;

	// JwtTokenProvider와 사용자 정보 조회용 서비스 주입
	public JwtAuthenticationFilter(JwtTokenProvider provider, CustomUserDetailsService service) {
		this.jwtTokenProvider = provider;
		this.userDetailsService = service;
	}

	// 요청이 들어올 때마다 JWT를 확인하고, 인증 정보를 SecurityContext에 설정
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Authorization 헤더에서 Bearer 토큰 추출
		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);

			if (jwtTokenProvider.validateToken(token)) {
				try {
					// 토큰에서 사용자 이메일 추출
					String email = jwtTokenProvider.getEmailFromToken(token);

					// 이메일로 사용자 정보 조회
					UserDetails userDetails = userDetailsService.loadUserByUsername(email);

					// 인증 객체 생성 및 SecurityContext에 설정
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(authToken);
				} catch (Exception e) {
					// 사용자 인증 실패 처리
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().write("{\"error\": \"User authentication failed: " + e.getMessage() + "\"}");
					return;
				}
			} else {
				// 토큰 유효성 검증 실패 처리
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write("{\"error\": \"Invalid or expired JWT token\"}");
				return;
			}
		}

		// 다음 필터(혹은 컨트롤러)로 계속 요청 전달
		filterChain.doFilter(request, response);
	}
}
