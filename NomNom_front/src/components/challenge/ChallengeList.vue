<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();

const missionList = ref([]);
const selectedMission = ref(null);
const showModal = ref(false);

const openModal = (mission) => {
  selectedMission.value = mission;
  showModal.value = true;
};

const closeModal = () => {
  showModal.value = false;
  selectedMission.value = null;
};

onMounted(async () => {
  const { data } = await axios.get("http://localhost:8080/api/mission/list", {
    params: { userNo: 1 },
  });
  missionList.value = data;
});

onMounted(async () => {
  const response = await axios.get("http://localhost:8080/api/mission/list", {
    params: { userNo: 1 },
  });
  missionList.value = response.data;
  console.log(missionList);
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/challenge/ChallengeList.css";
</style>

<template>
  <div class="board-header">
    <RouterLink :to="{ name: 'myChallenge' }">챌린지</RouterLink>
  </div>
  <!-- ////////////////////////////// -->
  <div class="div">
    <div class="content">
      <div class="header2">
        <div class="title">
          <div class="title2">다양한 챌린지에 도전하고</div>
          <div class="title3">여러분의 건강을 지키세요!</div>
          <div class="sub-title">어쩌고저쩌고 저쩌구저쩌구 그래서이래서 저렇게이렇게</div>
        </div>
      </div>
      <div class="frame-11">
        <div class="menu-nav2">
          <div class="button-nav4">
            <div class="label3">나의 챌린지</div>
          </div>
          <div class="button-nav5">
            <div class="label4">새로운 챌린지 도전하기</div>
          </div>
        </div>
        <div class="frame-112">
          <div class="cards">
            <div v-for="mission in missionList" :key="mission.missionNo" :class="mission.inProgress ? 'card-popular-menu2' : 'card-popular-menu'" @click="openModal(mission)">
              <div class="image">
                <img class="mission-image" src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png" />
              </div>
              <div class="frame-113">
                <div v-if="mission.inProgress" class="button-more2">
                  <div class="title6">참여중</div>
                </div>
                <div v-else-if="mission.completedCount > 0" class="button-more3">
                  <img class="icon-check" src="@/assets/images/challenge/ChallengeList/icon-check1.svg" />
                  <div class="title7">{{ mission.completedCount }}회</div>
                </div>
                <div class="title4">
                  {{ mission.missionName }}
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">{{ mission.challengeDuration }}일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
          </div>
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

  <!-- 챌린지 상세 모달 -->
  <!-- 모달 전체 -->
  <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
    <div class="modal-body">
      <!-- 이미지 -->
      <div class="modal-image">
        <img class="modal-carrot" src="@/assets/images/challenge/ChallengeList/blur-carrot.png" />
      </div>

      <!-- 텍스트 정보 -->
      <div class="modal-header">
        <div class="modal-title">
          {{ selectedMission?.missionName }}
        </div>
        <div class="modal-sub-title">
          {{ selectedMission?.missionDescription }}
        </div>
        <div class="modal-frame-11">
          <div class="modal-title2">{{ selectedMission?.challengeDuration }}일 연속으로 성공하면</div>
          <div class="modal-button-more">
            <div class="modal-title3">+ 15 포인트</div>
          </div>
        </div>
        <button class="modal-button-more2" :disabled="selectedMission?.inProgress">
          <div class="modal-text">
            <div class="modal-label">
              {{ selectedMission?.inProgress ? "지금 참여 중인 챌린지예요." : "오늘부터 챌린지 시작하기!" }}
            </div>
          </div>
        </button>
      </div>
    </div>
  </div>
</template>
