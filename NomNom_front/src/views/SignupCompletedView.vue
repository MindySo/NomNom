

<template>
  <div class="div">
    <div class="left-column">
      <div class="top">
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
      <div class="content">
        <div class="frame-25">
          <img class="party-popper-1" src="@/assets/images/auth/party-popper-10.png" />
          <div class="div2">회원가입 완료!</div>
          <div class="frame-12">
            <div class="complete-text">
              NomNom과 함께<br />
              건강한 식습관 여정을 시작해볼까요?
            </div>
          </div>
          <div class="button" @click="goToLogin">
            <div class="div3">로그인하러 가기</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

//로그인 이동
const router = useRouter()
const goToLogin = () => {
  router.push('/login')
}
</script>

<style>
@import '@/assets/css/vars.css';
@import '@/assets/css/auth/SignupCompleted.css';

.button {
  cursor: pointer;
}
</style>

