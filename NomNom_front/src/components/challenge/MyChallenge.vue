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
  <div class="div">
    <div class="body">
      <div class="header2">
        <div class="title">
          <div class="title2">다양한 챌린지에 도전하고</div>
          <div class="title3">여러분의 건강을 지키세요!</div>
          <div class="sub-title">어쩌고저쩌고 저쩌구저쩌구 그래서이래서 저렇게이렇게</div>
        </div>
      </div>
      <div class="content">
        <div class="menu-nav2">
          <div class="button-nav4">
            <div class="label3">나의 챌린지</div>
          </div>
          <div class="button-nav5">
            <div class="label4">새로운 챌린지 도전하기</div>
          </div>
        </div>
        <div class="frame-11">
          <div class="frame-12">
            <div class="calender-frame">
              <ChallengeCal />
            </div>
            <div class="frame-122">
              <div class="widget-popular-menu">
                <div class="header-section">
                  <div class="div-title">
                    <div class="title4">진행중인 챌린지</div>
                  </div>
                </div>
                <!-- <div class="scroll-div"> -->
                <div class="list-menu">
                  <div class="card-popular-menu" v-for="(challenge, index) in inProgressChallengeList" :key="challenge.challengeNo">
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
                    <!-- </div> -->
                  </div>
                </div>
              </div>
              <div class="widget-popular-menu">
                <div class="header-section">
                  <div class="div-title">
                    <div class="title4">달성한 챌린지</div>
                  </div>
                </div>
                <!-- <div class="scroll-div"> -->
                <div class="list-menu">
                  <div class="card-popular-menu" v-for="(challenge, index) in completedChallengeList" :key="challenge.challengeNo">
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
                  <!-- </div> -->
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
        <div class="social-media">
          <img class="facebook-logo" src="@/assets/images/challenge/MyChallenge/facebook-logo0.svg" />
          <img class="twitter-logo" src="@/assets/images/challenge/MyChallenge/twitter-logo0.svg" />
          <img class="instagram-logo" src="@/assets/images/challenge/MyChallenge/instagram-logo0.svg" />
          <img class="youtube-logo" src="@/assets/images/challenge/MyChallenge/youtube-logo0.svg" />
          <img class="linkedin-logo" src="@/assets/images/challenge/MyChallenge/linkedin-logo0.svg" />
        </div>
      </div>
    </div>
  </div>
</template>
