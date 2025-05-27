<template>
  <div class="div">
    <div class="left-column">
      <div class="top">
        <div class="header">
          <div class="logo">
            <div class="symbol">
              <div class="bowl"></div>
              <div class="bowl2"></div>
            </div>
            <div class="nutrigo">
              <span>
                <span class="nutrigo-span">NomNom</span>
                <span class="nutrigo-span2"><br />냠냠코치</span>
              </span>
            </div>
          </div>
        </div>
      </div>

      <div class="content">
        <div class="text">
          <div class="div2">회원가입</div>
          <div class="div3 center">NomNom에 회원가입하고 건강하게 식단 관리하세요.</div>
        </div>

        <div class="form">
         <!-- 닉네임 -->
          <div :class="['input2', { 'input-focused': isNicknameFocused || nickname }]">
            <input
              type="text"
              v-model="nickname"
              @focus="isNicknameFocused = true"
              @blur="isNicknameFocused = false"
              class="form-input"
              required
            />
            <label class="floating-label" :class="{ active: isNicknameFocused || nickname }">닉네임</label>

          </div>

            <!-- 생년월일 -->
          <div :class="['input2', { 'input-focused': isBirthdayFocused || birthday }]">
            <input
              type="text"
              v-model="birthday"
              @focus="isBirthdayFocused = true"
              @blur="isBirthdayFocused = false"
              class="form-input"
              required
            />
            <label class="floating-label" :class="{ active: isBirthdayFocused || birthday }">생년월일</label>
          </div>
          <div v-if="birthdayError" class="error-message">{{ birthdayError }}</div>


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
            <label class="floating-label" :class="{ active: isEmailFocused || email }">이메일</label>
          </div>
          <div v-if="emailError" class="error-message">{{ emailError }}</div>

          <!-- Password -->
          <div :class="['input2', { 'input-focused': isPasswordFocused || password }]">
            <input
              :type="isPasswordVisible ? 'text' : 'password'"
              v-model="password"
              @focus="isPasswordFocused = true"
              @blur="isPasswordFocused = false"
              class="form-input"
              required
            />
            <label class="floating-label" :class="{ active: isPasswordFocused || password }">비밀번호</label>
            <img
              class="eye-off"
              src="@/assets/images/auth/eye-off0.svg"
              @click="isPasswordVisible = !isPasswordVisible"
            />
          </div>

        <!-- Password 확인-->
          <div :class="['input2', { 'input-focused': isConfirmFocused || confirmPassword }]">
            <input
              :type="isConfirmVisible ? 'text' : 'password'"
              v-model="confirmPassword"
              @focus="isConfirmFocused = true"
              @blur="isConfirmFocused = false"
              class="form-input"
              required
            />
            <label class="floating-label" :class="{ active: isConfirmFocused || confirmPassword }">비밀번호 확인</label>
            <img
              class="eye-off"
              src="@/assets/images/auth/eye-off0.svg"
              @click="isConfirmVisible = !isConfirmVisible"
            />
          </div>
          <div v-if="passwordMatchError" class="error-message">{{ passwordMatchError }}</div>
          <div v-if="nicknameError" class="error-message">{{ nicknameError }}</div>

          <div class="button" :class="{ disabled: !isFormValid }" @click="handleSignup">
            <div class="div8">회원가입하기</div>
          </div>

          <div class="or">
            <img class="vector-1" src="@/assets/images/auth/vector-10.svg" />
            <div class="div9">또는</div>
            <img class="vector-2" src="@/assets/images/auth/vector-20.svg" />
          </div>

          <div class="button2" @click="handleGoogleLogin">
            <div class="google">Google로 시작하기</div>
            <div class="plus">
              <img class="group" src="@/assets/images/auth/group0.svg" />
            </div>
          </div>
        </div>

        <div class="frame-5">
          <div class="nom-nom">가입된 계정이 있나요?</div>
          <div class="div10" @click="goToLogin">로그인하기</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, computed } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()

const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const nickname = ref('')
const birthday = ref('')
const isEmailFocused = ref(false)
const isPasswordFocused = ref(false)
const isConfirmFocused = ref(false)
const isPasswordVisible = ref(false)
const isConfirmVisible = ref(false)
const isNicknameFocused = ref(false)
const isBirthdayFocused = ref(false)
const emailError = ref('')

const birthdayError = ref('')

const nicknameError = ref('')


// 이메일 유효성 검사
const validateEmail = (value) => {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return regex.test(value)
}

watch(email, (newVal) => {
  if (!newVal || validateEmail(newVal)) {
    emailError.value = ''
  } else {
    emailError.value = '올바른 이메일 형식을 입력해주세요.'
  }
})

const validateBirthday = (value) => {
  const regex = /^\d{4}-\d{2}-\d{2}$/
  if (!regex.test(value)) return false
  const [year, month, day] = value.split('-').map(Number)
  const date = new Date(year, month - 1, day)
  return (
    date.getFullYear() === year &&
    date.getMonth() === month - 1 &&
    date.getDate() === day
  )
}

watch(birthday, (newVal) => {
  if (!newVal || validateBirthday(newVal)) {
    birthdayError.value = ''
  } else {
    birthdayError.value = '올바른 생년월일 형식(yyyy-mm-dd)과 날짜를 입력해주세요.'
  }
})

const passwordMatchError = ref('');

watch([password, confirmPassword], ([newPw, newConfirm]) => {
  if (newConfirm && newPw !== newConfirm) {
    passwordMatchError.value = '비밀번호가 일치하지 않습니다.';
  } else {
    passwordMatchError.value = '';
  }
});

// 회원가입 요청
const handleSignup = async () => {
  emailError.value = ''
  birthdayError.value = ''
  nicknameError.value = ''

  if (!validateEmail(email.value)) {
    emailError.value = '올바른 이메일 형식을 입력해주세요.'
    return
  }

  if (!validateBirthday(birthday.value)) {
    birthdayError.value = '생년월일은 yyyy-mm-dd 형식이어야 합니다.'
    return
  }

  try {
    await axios.post('http://localhost:8080/api/user', {
      userEmail: email.value,
      userPw: password.value,
      userId: nickname.value,
      userBirthday: birthday.value
    })
    router.push('/signup/completed')
  } catch (error) {
    if (error.response?.status === 409) {
      emailError.value = '이미 등록된 이메일입니다.'
    } else {
      alert('회원가입 중 오류가 발생했습니다.')
    }
  }
}

// 구글 로그인 버튼 클릭 시 처리
const handleGoogleLogin = () => {
  window.location.href = 'http://localhost:8080/login/oauth2/code/google'
}
// 선택정보 이동
// const goToSignupProfile = () => {
//   if (isFormValid.value) {
//     router.push('/signup/profile');
//   }
// }

//회원가입 완료 
const goSignup = () => {
  router.push('/signup/completed') 
}
// 로그인 이동
const goToLogin = () => {
  router.push('/login') 
}

const isFormValid = computed(() => {
  return (
    nickname.value &&
    birthday.value &&
    email.value &&
    password.value &&
    confirmPassword.value &&
    password.value === confirmPassword.value
  )
})
</script>

<style>
@import '@/assets/css/vars.css';
@import '@/assets/css/auth/Login.css';
</style>

<style>
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

.button.disabled {
  background: #ccc;
  cursor: not-allowed;
  pointer-events: none;
}
</style>
