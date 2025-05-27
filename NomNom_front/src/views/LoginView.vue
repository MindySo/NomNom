<template>
  <div class="div">
    <div class="left-column">
      <div class="content">
        <div class="text">
          <div class="div2">로그인</div>
          <div class="div3 center">계정에 접근하기 위해서 로그인하세요.</div>
        </div>

        <div class="form">
          <!-- Email -->
          <div :class="['input', { 'input-focused': isEmailFocused || email }]">
            <input
              type="email"
              v-model="email"
              @focus="isEmailFocused = true"
              @blur="isEmailFocused = false"
              class="form-input"
              required
            />
            <label
              class="floating-label"
              :class="{ active: isEmailFocused || email }"
              >이메일</label
            >
          </div>
          <div v-if="emailError" class="error-message">{{ emailError }}</div>

          <!-- Password -->
          <div
            :class="[
              'input2',
              { 'input-focused': isPasswordFocused || password },
            ]"
          >
            <input
              type="password"
              v-model="password"
              @focus="isPasswordFocused = true"
              @blur="isPasswordFocused = false"
              class="form-input"
              required
            />
            <label
              class="floating-label"
              :class="{ active: isPasswordFocused || password }"
              >비밀번호</label
            >
            <img class="eye-off" src="@/assets/images/auth/eye-off0.svg" />
          </div>

          <div class="keep">
            <input type="checkbox" />
            <div class="div7">로그인 유지</div>
          </div>

          <div class="button" @click="handleLogin">
            <div class="div8">로그인</div>
          </div>
          <div v-if="loginError" class="error-message">{{ loginError }}</div>

          <div class="div9">또는</div>

          <div class="button2" @click="handleGoogleLogin">
            <div class="google">Google로 시작하기</div>
            <div class="plus">
              <img class="group" src="@/assets/images/auth/group0.svg" />
            </div>
          </div>
        </div>

        <div class="frame-5">
          <div class="nom-nom">NomNom에 처음이신가요?</div>
          <div class="div10" @click="goToSignup">회원가입하기</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/authStore';

const router = useRouter();

const authStore = useAuthStore();

const email = ref('');
const password = ref('');
const isEmailFocused = ref(false);
const isPasswordFocused = ref(false);
const emailError = ref('');
const loginError = ref('');

// 이메일 유효성 검사
const validateEmail = (value) => {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(value);
};

// 이메일 입력 변화 감지하여 에러 처리
watch(email, (newVal) => {
  if (!newVal || validateEmail(newVal)) {
    emailError.value = '';
  } else {
    emailError.value = '올바른 이메일 형식을 입력해주세요.';
  }
});

// 로그인 요청
const handleLogin = async () => {
  loginError.value = '';
  emailError.value = '';

  if (!validateEmail(email.value)) {
    emailError.value = '올바른 이메일 형식을 입력해주세요.';
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/api/auth/login', {
      email: email.value,
      password: password.value,
    });
    console.log(sessionStorage.getItem('accessToken'));
    const token = response.data.token;
    const user = response.data.user;
    sessionStorage.setItem('accessToken', token);
    sessionStorage.setItem('user', JSON.stringify(user));
    authStore.setLogin(token, user.userNo); // pinia authStore에 토큰과 유저넘버 저장
    router.push('/main');
  } catch (error) {
    loginError.value =
      '로그인에 실패했습니다. 이메일과 비밀번호를 확인해주세요.';
  }
};

// 구글 로그인 버튼 클릭 시 처리
const handleGoogleLogin = () => {
  // Only triggered when Google login button is clicked
  window.location.href = 'http://localhost:8080/oauth2/authorization/google';
};

// 회원가입 이동
const goToSignup = () => {
  router.push('/signup'); // '/signup' 페이지가 존재해야 함
};
</script>

<style scoped>
@import '@/assets/css/vars.css';
@import '@/assets/css/auth/Login.css';

.text .div3.center {
  text-align: center;
}

.input,
.input2 {
  position: relative;
  border: 1px solid #d9d9d9;
  border-radius: 10px;
  padding: 16px;
  margin-bottom: 16px;
  transition: border 0.3s;
}

.input-focused {
  border-color: #73a107 !important;
}

.form-input {
  all: unset;
  width: 100%;
  font-size: 16px;
}

.floating-label {
  position: absolute;
  left: 16px;
  top: 50%;
  transform: translateY(-50%);
  color: #aaa;
  font-size: 16px;
  pointer-events: none;
  transition: 0.2s ease all;
}

.floating-label.active {
  top: -10px;
  left: 12px;
  font-size: 12px;
  background: white;
  padding: 0 4px;
  color: #73a107;
}

.error-message {
  color: #e74c3c;
  font-size: 14px;
  margin-top: -10px;
  margin-bottom: 12px;
}

/* 버튼 hover 시 커서 변경 */
.button,
.button2,
.div10 {
  cursor: pointer;
}
</style>
