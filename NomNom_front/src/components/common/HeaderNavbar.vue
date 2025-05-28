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

    <nav class="menu-nav" v-if="!hideNavbarRoutes.includes(route.path)">
      <div class="group-21">
        <div class="button-nav3" @click="router.push('/meal')" :class="{ active: route.path.startsWith('/meal') }">
          <img class="icon-nav" src="@/assets/images/global/CalendarDots.png" />
          <div class="text2">
            <span class="label3">식단 기록</span>
          </div>
        </div>
      </div>

      <div class="group-21">
        <div class="button-nav3" @click="router.push('/community')" :class="{ active: route.path.startsWith('/community') }">
          <img class="icon-nav" src="@/assets/images/global/Notebook.svg" />
          <div class="text2">
            <span class="label3">커뮤니티</span>
          </div>
        </div>
      </div>

      <div class="group-21">
        <div class="button-nav3" @click="router.push('/challenge')" :class="{ active: route.path.startsWith('/challenge') }">
          <img class="icon-nav" src="@/assets/images/global/icon-nav-chart-line-up0.svg" />
          <div class="text2">
            <span class="label3">챌린지</span>
          </div>
        </div>
      </div>

      <div v-if="isLoggedIn" class="if-login">
        <div class="group-21">
          <div class="button-nav3" @click="router.push('/mypage')" :class="{ active: route.path.startsWith('/mypage') }">
            <img class="icon-nav" src="@/assets/images/global/icon-nav-heartbeat0.svg" />
            <div class="text2">
              <span class="label3">마이페이지</span>
            </div>
          </div>
        </div>
        <div class="button-nav4" @click="handleLogout()">
          <img class="icon-nav" src="@/assets/images/global/icon-nav-sign-out0.svg" />
          <div>
            <span class="label3">로그아웃</span>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="button-nav4" @click="router.push('/login')">
          <img class="icon-nav" src="@/assets/images/global/icon-nav-sign-out0.svg" />
          <div class="text2">
            <span class="label3">로그인</span>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();

import { useAuthStore } from "@/stores/authStore";
const authStore = useAuthStore();

const hideNavbarRoutes = ["/login", "/signup"];

const isLoggedIn = computed(() => authStore.isLoggedIn);

onMounted(() => {
  authStore.checkAuth();
});

function handleLogout() {
  sessionStorage.removeItem("accessToken");
  if (!authStore.checkAuth()) {
    alert("로그아웃되었습니다.");
    router.push("/challenge");
  }
}
</script>

<style scoped>
@import "@/assets/css/common/Navbar.css";
@import "@/assets/css/vars.css";
</style>
