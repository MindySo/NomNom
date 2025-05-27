<template>
  <header class="navbar">
    <div class="header">
      <div class="logo" @click="router.push('/')">
        <div class="symbol">
          <div class="bowl"></div>
          <div class="bowl2"></div>
        </div>
        <div class="nutrigo">
          <div class="nutrigo-column">
            <span class="nutrigo-span">NomNom</span>
            <span class="nutrigo-span2">냠냠코치</span>
          </div>
        </div>
      </div>
    </div>

    <nav class="menu-nav">
      <div class="group-21">
        <div class="button-nav" @click="router.push('/meal')" :class="{ active: route.path.startsWith('/meal') }">
          <img class="icon-nav-calendar-dots" src="@/assets/images/global/icon-nav-calendar-dots0.svg" />
          <div class="text">
            <span class="label">식단 기록</span>
          </div>
        </div>
      </div>

      <div class="button-nav2" @click="router.push('/community')" :class="{ active: route.path.startsWith('/community') }">
        <img class="icon-nav-notebook" src="@/assets/images/global/icon-nav-notebook0.svg" />
        <div class="text2">
          <span class="label2">커뮤니티</span>
        </div>
      </div>

      <div class="button-nav3" @click="router.push('/challenge')" :class="{ active: route.path.startsWith('/challenge') }">
        <img class="icon-nav-chart-line-up" src="@/assets/images/global/icon-nav-chart-line-up0.svg" />
        <div class="text2">
          <span class="label3">챌린지</span>
        </div>
      </div>

      <div class="button-nav3" @click="router.push('/mypage')" :class="{ active: route.path.startsWith('/mypage') }">
        <img class="icon-nav-heartbeat" src="@/assets/images/global/icon-nav-heartbeat0.svg" />
        <div class="text2">
          <span class="label3">마이페이지</span>
        </div>
      </div>

      <div class="button-nav4">
        <img class="icon-nav-sign-out" src="@/assets/images/global/icon-nav-sign-out0.svg" />
        <div class="text3">
          <span class="label">로그아웃</span>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
// 별도 로직 없음. 필요시 emit 또는 router 연결 예정
</script>

<style scoped>
@import '@/assets/css/common/Navbar.css';
@import '@/assets/css/vars.css';
.nutrigo-column {
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 1.2;
}
.active {
  background-color: #c2e66e;
  border-radius: 14px;
}
.button-nav,
.button-nav2,
.button-nav3,
.button-nav4,
.logo {
  cursor: pointer;
}
</style>