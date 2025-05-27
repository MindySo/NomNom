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

const startChallenge = async (missionNo) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/mission/challenge",
      null, // POST 바디가 필요 없다면 null
      {
        params: {
          userNo: 1,
          missionNo: missionNo,
        },
      }
    );

    alert("챌린지를 시작했어요!");

    // 필요하다면 챌린지 목록 다시 불러오기
    // await fetchInProgressChallenges();
  } catch (error) {
    console.error("챌린지 시작 실패:", error);
    alert("챌린지를 시작하는 데 실패했어요.");
  }
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
  <div class="cards">
    <div v-for="mission in missionList" :key="mission.missionNo" :class="mission.inProgress ? 'card-popular-menu2' : 'card-popular-menu'" @click="openModal(mission)">
      <div class="image">
        <img class="mission-image" :src="mission.imageUrl" :alt="mission.missionName" />
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

  <!-- 챌린지 상세 모달 -->
  <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
    <div class="modal-body">
      <!-- 이미지 -->
      <div class="modal-image">
        <img class="modal-carrot" :src="selectedMission?.imageUrl" :alt="selectedMission?.missionName" />
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
        <button class="modal-button-more2" :disabled="selectedMission?.inProgress" @click="startChallenge(selectedMission?.missionNo)">
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
