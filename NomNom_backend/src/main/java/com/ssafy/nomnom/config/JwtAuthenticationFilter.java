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
					String email = jwtTokenProvider.getEmailFromToken(token);
					UserDetails userDetails = userDetailsService.loadUserByUsername(email);

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(authToken);
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("User authentication failed: " + e.getMessage());
					return;
				}
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Invalid or expired JWT token");
				return;
			}
		}

		// 다음 필터(혹은 컨트롤러)로 계속 요청 전달
		filterChain.doFilter(request, response);
	}
}
