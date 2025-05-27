<template>
  <div class="frame-21">
    <div class="navbar">
      <div class="menu-nav">
        <RouterLink
          :to="{ path: '/community', query: { boardType: 'FREE' } }"
          class="button-nav"
          :class="{ active: route.query.boardType === 'FREE' }"
        >
          <img class="icon-nav-person-simple-tai-chi" src="@/assets/images/community/icon-nav-person-simple-tai-chi0.svg" />
          <div class="text">
            <div class="label">자유 게시판</div>
          </div>
        </RouterLink>
        <RouterLink
          :to="{ path: '/community', query: { boardType: 'TIP' } }"
          class="button-nav"
          :class="{ active: route.query.boardType === 'TIP' }"
        >
          <img class="icon-nav-notebook" src="@/assets/images/community/icon-nav-notebook0.svg" />
          <div class="text">
            <div class="label">식단 꿀팁</div>
          </div>
        </RouterLink>
        <RouterLink
          :to="{ path: '/community', query: { boardType: 'REVIEW' } }"
          class="button-nav"
          :class="{ active: route.query.boardType === 'REVIEW' }"
        >
          <img class="icon-nav-chart-line-up" src="@/assets/images/community/icon-nav-chart-line-up0.svg" />
          <div class="text">
            <div class="label">챌린지 후기</div>
          </div>
        </RouterLink>
      </div>

      <div class="promotional-banner">
        <div class="image">
          <img class="_3-d-lettuce" src="@/assets/images/community/_3-d-lettuce0.png" />
          <img class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style" src="@/assets/images/community/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style0.png" />
        </div>
        <div class="text2">
          <div class="label2">
            <span>
              <span class="label-2-span">식사마다</span>
              <span class="label-2-span2">야채</span>
              <span class="label-2-span"><br />챙겨먹고</span>
              <span class="label-2-span3">챌린지</span>
              <span class="label-2-span">에 도전하세요!</span>
            </span>
          </div>
        </div>
        <div class="button">
          <div class="text3">
            <div class="label3">지금 도전하기</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
const route = useRoute()
</script>

<style scoped>
@import '@/assets/css/vars.css';
@import '@/assets/css/community/Sidebar.css';

.button-nav,
.button {
  cursor: pointer;
}

.button-nav.active {
  background-color: #c2e66e;
}

.button-nav.active .label {
  color: var(--black); /* 커뮤니티 탭 활성화 시 라벨 색 */
}
</style>