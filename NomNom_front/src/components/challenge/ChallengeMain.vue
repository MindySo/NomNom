<script setup>
import { ref } from "vue";
import MyChallenge from "@/components/challenge/MyChallenge.vue";
import ChallengeList from "@/components/challenge/ChallengeList.vue";

const activeTab = ref("new");
const setTab = (tab) => {
  activeTab.value = tab;
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/challenge/ChallengeList.css";
</style>

<template>
  <div class="div">
    <div class="content">
      <div class="header2">
        <div class="title">
          <div class="title2">다양한 챌린지에 도전하고</div>
          <div class="title3">여러분의 건강을 지키세요!</div>
          <div class="sub-title">작은 실천이 쌓이면 건강한 습관이 됩니다. 오늘부터 함께 시작해요!</div>
        </div>
      </div>
      <div class="frame-11">
        <div class="menu-nav2">
          <div class="button-nav4" :class="{ active: activeTab === 'my' }" @click="setTab('my')">
            <div class="label3">나의 챌린지</div>
          </div>
          <div class="button-nav4" :class="{ active: activeTab === 'new' }" @click="setTab('new')">
            <div class="label3">새로운 챌린지 도전하기</div>
          </div>
        </div>
        <div class="frame-112">
          <MyChallenge v-if="activeTab === 'my'" />
          <ChallengeList v-if="activeTab === 'new'" />
        </div>
      </div>
      <div class="section-footer">
        <div class="legal-information">
          <div class="copyright-2024-peterdraw">Copyright © 2024 Peterdraw</div>
          <div class="links">
            <div class="privacy-policy">Privacy Policy</div>
            <div class="term-and-conditions">Term and conditions</div>
            <div class="contact">Contact</div>
          </div>
        </div>
        <div class="social-media"></div>
      </div>
    </div>
  </div>
</template>
