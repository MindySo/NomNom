<template>
  <div class="div" style="display: flex; justify-content: center; align-items: center; min-height: 100vh; padding: 16px;">
    <div class="left-column" style="width: 100%; max-width: 480px;">
      <div class="content">
        <div class="group-11">
          <div class="div2">회원가입</div>
          <div class="div3">
            선택 정보는 개인 맞춤형 서비스에만 활용되며,<br />
            입력한 정보는 공개되지 않습니다.
          </div>
        </div>

        <div :class="['input', { 'input-focused': isHeightFocused || height }]">
          <input
            type="text"
            v-model="height"
            @focus="isHeightFocused = true"
            @blur="isHeightFocused = false"
            class="form-input"
            required
          />
          <label class="floating-label" :class="{ active: isHeightFocused || height }">키 (cm)</label>
        </div>

        <div :class="['input', { 'input-focused': isWeightFocused || weight }]">
          <input
            type="text"
            v-model="weight"
            @focus="isWeightFocused = true"
            @blur="isWeightFocused = false"
            class="form-input"
            required
          />
          <label class="floating-label" :class="{ active: isWeightFocused || weight }">몸무게 (kg)</label>
        </div>

        <div class="frame-14">
          <div class="frame-142">
            <div class="div4" :class="{ 'highlight-label': gender }">성별</div>
            <div class="input2" :class="{ selected: gender === 'FEMALE' }" @click="gender = 'FEMALE'">
              <div class="div5">여성</div>
            </div>
          </div>
          <div class="frame-15">
            <div class="div6"> </div>
            <div class="input3" :class="{ selected: gender === 'MALE' }" @click="gender = 'MALE'">
              <div class="div5">남성</div>
            </div>
          </div>
        </div>

        <div :class="['select-wrapper', { 'select-focused': selectedAllergy !== '' }]">
          <div class="div4" :class="{ 'highlight-label': selectedAllergy !== '' }">알레르기</div>
          <select v-model="selectedAllergy" class="select-input">
            <option disabled value="">선택하세요</option>
            <option v-for="item in allergyList" :key="item.allergyNo" :value="item.allergyNo">
              {{ item.name }}
            </option>
          </select>
        </div>

        <div class="frame-12">
          <div class="button" @click="skip">
            <div class="div8">건너뛰기</div>
          </div>
          <div class="button" @click="submit">
            <div class="div9">회원가입</div>
          </div>
        </div>

        <div class="div10">
          회원가입 시 개인정보 수집・이용 및 이용약관에 동의하는 것으로 간주됩니다.
        </div>

        <div class="or">
          <img class="vector-1" src="@/assets/images/auth/vector-10.svg" />
          <div class="div11">또는</div>
          <img class="vector-2" src="@/assets/images/auth/vector-20.svg" />
        </div>

        <div class="button2">
          <div class="google">Google로 계속하기</div>
          <div class="plus">
            <img class="group" src="@/assets/images/auth/group0.svg" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import '@/assets/css/auth/SignupProfile.css'

const router = useRouter()
const height = ref('')
const weight = ref('')
const gender = ref('')
const selectedAllergy = ref('')

const isHeightFocused = ref(false)
const isWeightFocused = ref(false)

const allergyList = ref([])

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/user/allergies')
    console.log('알레르기 목록:', response.data)
    allergyList.value = response.data
  } catch (error) {
    console.error('알레르기 목록을 불러오지 못했습니다:', error)
  }
})

const skip = () => {
  router.push('/signup/completed')
}

const submit = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/user', {
      userId: localStorage.getItem('userId'),
      userEmail: localStorage.getItem('userEmail'),
      userPw: localStorage.getItem('userPw'),
      userBirthday: localStorage.getItem('userBirthday'),
      userGender: gender.value,
      userHeight: height.value,
      userWeight: weight.value
    })
    console.log('회원가입 완료:', response.data)
    router.push('/signup/completed')
  } catch (error) {
    console.error('회원가입 실패:', error)
    alert('회원가입 중 문제가 발생했습니다.')
  }
}
</script>

<style>
@import '@/assets/css/vars.css';
@import '@/assets/css/auth/SignupProfile.css';
</style>

<style >
.select-wrapper {
  margin-bottom: 16px;
}
.select-focused .select-input {
  border-color: #73a107 !important;
}
.highlight-label {
  color: #73a107;
  font-weight: 600;
}
</style>
<style scoped>
.input {
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

.highlight-label {
  color: #73a107;
  font-weight: 600;
}

.select-input {
  width: 368px;
  padding: 16px;
  border: 1.5px solid #d9d9d9;
  border-radius: 10px;
  font-size: 18px;
  font-family: "Poppins-Regular", sans-serif;
  color: #232323;
  background-color: #ffffff;
  margin-bottom: 16px;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-image: url('@/assets/images/auth/icon-caret-up0.svg');
  background-repeat: no-repeat;
  background-position: right 16px center;
  background-size: 16px;
  cursor: pointer;
  transition: border-color 0.3s ease;
}

.select-input:focus {
  outline: none;
  border-color: #73a107;
}
</style>
<style>
.button,
.button2,
.google {
  cursor: pointer;
}
</style>