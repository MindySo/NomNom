<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import ChallengeCal from "@/components/challenge/ChallengeCal.vue";
import axios from "axios";
const router = useRouter();

const inProgressChallengeList = ref([]);
const completedChallengeList = ref([]);

const formatDate = (dateStr) => {
  const date = new Date(dateStr);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  return `${year}년 ${month}월 ${day}일`;
};

const fetchInProgressChallenges = async () => {
  try {
    const { data } = await axios.get("http://localhost:8080/api/mission/challenge/inprogress", { params: { userNo: 1 } });
    inProgressChallengeList.value = data;
  } catch (error) {
    console.error("챌린지 목록 불러오기 실패:", error);
  }
};

const fetchCompletedChallenges = async () => {
  try {
    const { data } = await axios.get("http://localhost:8080/api/mission/challenge/completed", { params: { userNo: 1 } });
    completedChallengeList.value = data;
  } catch (error) {
    console.error("챌린지 목록 불러오기 실패:", error);
  }
};

onMounted(() => {
  fetchInProgressChallenges();
  fetchCompletedChallenges();
});

const challengeCalRef = ref(null); // ChallengeCal 인스턴스를 가리킴

function onCardClick(challengeNo) {
  if (challengeCalRef.value) {
    challengeCalRef.value.highlightChallengeEvent(challengeNo); // 자식에게 전달
  }
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/challenge/MyChallenge.css";
.temp {
  width: 1000px;
}
</style>

<template>
  <!-- frame-11 -->
  <div class="frame-12">
    <div class="calender-frame">
      <ChallengeCal ref="challengeCalRef" />
    </div>
    <div class="frame-122">
      <div class="widget-popular-menu">
        <div class="header-section">
          <div class="div-title">
            <div class="title4">진행중인 챌린지</div>
          </div>
        </div>
        <div class="list-menu">
          <div class="card-popular-menu" v-for="(challenge, index) in inProgressChallengeList" :key="challenge.challengeNo" @click="onCardClick(challenge.challengeNo)">
            <div class="info">
              <div class="image">
                <img class="challenge-image" :src="challenge.imageUrl" :alt="challenge.missionName" />
              </div>
              <div class="frame-123">
                <div class="title5">{{ formatDate(challenge.challengeStartDate) }} 시작</div>
                <div class="title6">{{ challenge.missionName }}</div>
              </div>
            </div>
            <div class="frame-124">
              <div class="button-more">
                <div class="title7">+ {{ challenge.challengeCurrentStreak }}일차 도전중!</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="widget-popular-menu">
        <div class="header-section">
          <div class="div-title">
            <div class="title4">달성한 챌린지</div>
          </div>
        </div>
        <div class="list-menu">
          <div class="card-popular-menu" v-for="(challenge, index) in completedChallengeList" :key="challenge.challengeNo" @click="onCardClick(challenge.challengeNo)">
            <div class="info">
              <div class="image">
                <img class="challenge-image" :src="challenge.imageUrl" :alt="challenge.missionName" />
              </div>
              <div class="frame-123">
                <div class="title5">{{ formatDate(challenge.challengeStartDate) }} 시작</div>
                <div class="title6">{{ challenge.missionName }}</div>
              </div>
            </div>
            <div class="frame-124">
              <div class="button-more2">
                <div class="button-more2-title">+ 15 포인트</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
