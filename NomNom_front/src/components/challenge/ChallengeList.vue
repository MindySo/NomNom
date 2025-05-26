<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
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
    <div class="navbar">
      <div class="header">
        <div class="logo">
          <div class="symbol">
            <div class="bowl"></div>
            <div class="bowl2"></div>
          </div>
          <div class="nutrigo">
            <span>
              <span class="nutrigo-span">NomNom</span>
              <span class="nutrigo-span2">
                <br />
                냠냠코치
              </span>
            </span>
          </div>
        </div>
      </div>
      <div class="menu-nav">
        <div class="button-nav">
          <div class="text">
            <div class="label">식단 기록</div>
          </div>
        </div>
        <div class="button-nav2">
          <div class="text2">
            <div class="label2">즐겨찾기</div>
          </div>
          <div class="icon"></div>
        </div>
        <div class="button-nav2">
          <div class="text2">
            <div class="label2">커뮤니티</div>
          </div>
        </div>
        <div class="button-nav2">
          <div class="text2">
            <div class="label2">챌린지</div>
          </div>
        </div>
        <div class="button-nav2">
          <div class="text2">
            <div class="label2">마이페이지</div>
          </div>
        </div>
        <div class="button-nav3">
          <div class="text3">
            <div class="label2">로그아웃</div>
          </div>
        </div>
      </div>
    </div>
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
            <div class="card-popular-menu">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu2">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="button-more2">
                  <div class="title6">참여중</div>
                </div>
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="button-more3">
                  <img class="icon-check" src="@/assets/images/challenge/ChallengeList/icon-check1.svg" />
                  <div class="title7">2회</div>
                </div>
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="button-more3">
                  <img class="icon-check2" src="@/assets/images/challenge/ChallengeList/icon-check1.svg" />
                  <div class="title7">2회</div>
                </div>
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu2">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="button-more2">
                  <div class="title6">참여중</div>
                </div>
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
                <div class="button-more">
                  <div class="title6">+ 15 포인트</div>
                </div>
              </div>
            </div>
            <div class="card-popular-menu">
              <div class="image">
                <img
                  class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                  src="@/assets/images/challenge/ChallengeList/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style.png"
                />
              </div>
              <div class="frame-113">
                <div class="title4">
                  하루 한끼
                  <br />
                  생 채소 먹기
                </div>
              </div>
              <div class="frame-114">
                <div class="title5">30일 성공하면</div>
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
  <div class="modal">
    <div class="modal-body">
      <div class="modal-image">
        <img class="modal-carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style" src="@/assets/images/challenge/ChallengeList/blur-carrot.png" />
      </div>
      <div class="modal-header">
        <div class="modal-title">
          하루 한끼
          <br />
          생 채소 먹기
        </div>
        <div class="modal-sub-title">
          하루에 한 끼는 꼭 생 채소를 먹어보아요.
          <br />
          식단 등록에서 사진을 첨부하면
          <br />
          냠냠코치가 체크하고 미션 기록을 적립해요.
        </div>
        <div class="modal-frame-11">
          <div class="modal-title2">30일 연속으로 성공하면</div>
          <div class="modal-button-more">
            <div class="modal-title3">+ 15 포인트</div>
          </div>
        </div>
        <div class="modal-button-more2">
          <div class="modal-text">
            <div class="modal-label">오늘부터 챌린지 시작하기!</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
