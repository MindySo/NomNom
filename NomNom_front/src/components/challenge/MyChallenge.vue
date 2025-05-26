<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";

const events = [
  {
    title: "하루짜리",
    start: "2025-05-28",
    end: "2025-05-28",
  },
  {
    title: "여러날짜 이벤트",
    start: "2025-05-29",
    end: "2025-06-02",
  },
];

const calendarOptions = {
  plugins: [dayGridPlugin],
  initialView: "dayGridMonth",
  events: events,
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/challenge/MyChallenge.css";
</style>

<template>
  <!-- FullCalendar 위치 옮기기 -->
  <FullCalendar :options="calendarOptions" />
</template>

<!-- 
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
          <img class="icon-nav-calendar-dots" src="@/assets/images/challenge/MyChallenge/icon-nav-calendar-dots0.svg" />
          <div class="text">
            <div class="label">식단 기록</div>
          </div>
        </div>
        <div class="button-nav2">
          <img class="icon-nav-bowl-food" src="@/assets/images/challenge/MyChallenge/icon-nav-bowl-food0.svg" />
          <div class="text2">
            <div class="label2">즐겨찾기</div>
          </div>
          <div class="icon">
            <img class="icon-caret-down" src="@/assets/images/challenge/MyChallenge/icon-caret-down0.svg" />
          </div>
        </div>
        <div class="button-nav2">
          <img class="icon-nav-notebook" src="@/assets/images/challenge/MyChallenge/icon-nav-notebook0.svg" />
          <div class="text2">
            <div class="label2">커뮤니티</div>
          </div>
        </div>
        <div class="button-nav2">
          <img class="icon-nav-chart-line-up" src="@/assets/images/challenge/MyChallenge/icon-nav-chart-line-up0.svg" />
          <div class="text2">
            <div class="label2">챌린지</div>
          </div>
        </div>
        <div class="button-nav2">
          <img class="icon-nav-heartbeat" src="@/assets/images/challenge/MyChallenge/icon-nav-heartbeat0.svg" />
          <div class="text2">
            <div class="label2">마이페이지</div>
          </div>
        </div>
        <div class="button-nav3">
          <img class="icon-nav-sign-out" src="@/assets/images/challenge/MyChallenge/icon-nav-sign-out0.svg" />
          <div class="text3">
            <div class="label2">로그아웃</div>
          </div>
        </div>
      </div>
    </div>
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
              <div class="calendar">
                <div class="month-year-line">
                  <img class="chevron-left" src="@/assets/images/challenge/MyChallenge/chevron-left0.svg" />
                  <div class="month-year">
                    <div class="year">
                      <div class="year2">2025</div>
                      <div class="div2">년</div>
                    </div>
                    <div class="month">
                      <div class="month2">05</div>
                      <div class="div2">월</div>
                    </div>
                    <img class="vector" src="@/assets/images/challenge/MyChallenge/vector0.svg" />
                  </div>
                  <img class="chevron-right" src="@/assets/images/challenge/MyChallenge/chevron-right0.svg" />
                </div>
                <div class="day-date">
                  <div class="day-line">
                    <div class="day">
                      <div class="div3">일</div>
                    </div>
                    <div class="day">
                      <div class="div4">월</div>
                    </div>
                    <div class="day">
                      <div class="div4">화</div>
                    </div>
                    <div class="day">
                      <div class="div4">수</div>
                    </div>
                    <div class="day">
                      <div class="div4">목</div>
                    </div>
                    <div class="day">
                      <div class="div4">금</div>
                    </div>
                    <div class="day">
                      <div class="div5">토</div>
                    </div>
                  </div>
                  <div class="date-line">
                    <div class="date-number-line-04">
                      <div class="date">
                        <div class="_1">26</div>
                      </div>
                      <div class="date">
                        <div class="_1">27</div>
                      </div>
                      <div class="date">
                        <div class="_1">28</div>
                      </div>
                      <div class="date">
                        <div class="_1">29</div>
                      </div>
                      <div class="date">
                        <div class="_1">30</div>
                      </div>
                      <div class="date">
                        <div class="_1">31</div>
                      </div>
                      <div class="date">
                        <div class="_12">1</div>
                      </div>
                    </div>
                    <div class="date-number-line-03">
                      <div class="date">
                        <div class="_13">2</div>
                      </div>
                      <div class="date">
                        <div class="_14">3</div>
                      </div>
                      <div class="date">
                        <div class="_14">4</div>
                      </div>
                      <div class="date">
                        <div class="_14">5</div>
                      </div>
                      <div class="date">
                        <div class="_14">6</div>
                      </div>
                      <div class="date">
                        <div class="_14">7</div>
                      </div>
                      <div class="date">
                        <div class="_12">8</div>
                      </div>
                    </div>
                    <div class="date-number-line-02">
                      <div class="date">
                        <div class="_13">9</div>
                      </div>
                      <div class="date">
                        <div class="_14">10</div>
                      </div>
                      <div class="date">
                        <div class="_14">11</div>
                      </div>
                      <div class="date">
                        <div class="_14">12</div>
                      </div>
                      <div class="date">
                        <div class="_14">13</div>
                      </div>
                      <div class="date">
                        <div class="_14">14</div>
                      </div>
                      <div class="date">
                        <div class="_12">15</div>
                      </div>
                    </div>
                    <div class="date-number-line-01">
                      <div class="date">
                        <div class="_13">16</div>
                      </div>
                      <div class="date">
                        <div class="_14">17</div>
                      </div>
                      <div class="date">
                        <div class="_14">18</div>
                      </div>
                      <div class="date">
                        <div class="_14">19</div>
                      </div>
                      <div class="date">
                        <div class="_14">20</div>
                      </div>
                      <div class="date">
                        <div class="_14">21</div>
                      </div>
                      <div class="date">
                        <div class="_12">22</div>
                      </div>
                    </div>
                    <div class="date-number-line-05">
                      <div class="date">
                        <div class="_13">23</div>
                      </div>
                      <div class="date">
                        <div class="_14">24</div>
                      </div>
                      <div class="date">
                        <div class="_14">25</div>
                      </div>
                      <div class="date">
                        <div class="_14">26</div>
                      </div>
                      <div class="date">
                        <div class="_14">27</div>
                      </div>
                      <div class="date">
                        <div class="_14">28</div>
                      </div>
                      <div class="date">
                        <div class="_12">29</div>
                      </div>
                    </div>
                    <div class="date-number-line-06">
                      <div class="date">
                        <div class="_13">30</div>
                      </div>
                      <div class="date">
                        <div class="_1">1</div>
                      </div>
                      <div class="date">
                        <div class="_1">2</div>
                      </div>
                      <div class="date">
                        <div class="_1">3</div>
                      </div>
                      <div class="date">
                        <div class="_1">4</div>
                      </div>
                      <div class="date">
                        <div class="_1">5</div>
                      </div>
                      <div class="date">
                        <div class="_1">6</div>
                      </div>
                    </div>
                  </div>
                  <div class="rectangle-11"></div>
                  <div class="rectangle-12"></div>
                  <div class="rectangle-13"></div>
                  <div class="rectangle-14"></div>
                </div>
                <div class="rectangle-10"></div>
                <div class="rectangle-112"></div>
                <div class="rectangle-122"></div>
                <img class="rectangle-132" src="@/assets/images/challenge/MyChallenge/rectangle-131.svg" />
              </div>
            </div>
            <div class="frame-122">
              <div class="widget-popular-menu">
                <div class="header-section">
                  <div class="div-title">
                    <div class="title4">진행중인 챌린지</div>
                  </div>
                </div>
                <div class="scroll-div">
                  <div class="list-menu">
                    <div class="card-popular-menu">
                      <div class="info">
                        <div class="image">
                          <img
                            class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                            src="@/assets/images/challenge/MyChallenge/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style0.png"
                          />
                        </div>
                        <div class="frame-123">
                          <div class="title5">2025년 05월 19일 시작</div>
                          <div class="title6">한끼 채소 먹기 챌린지</div>
                        </div>
                      </div>
                      <div class="frame-124">
                        <div class="button-more">
                          <div class="title7">+ 2일차 도전중!</div>
                        </div>
                      </div>
                    </div>
                    <div class="card-popular-menu2">
                      <div class="info">
                        <div class="image">
                          <img class="_3-d-lettuce" src="@/assets/images/challenge/MyChallenge/_3-d-lettuce0.png" />
                        </div>
                        <div class="frame-123">
                          <div class="title5">2025년 05월 19일 시작</div>
                          <div class="title6">저염식 먹기 챌린지</div>
                        </div>
                      </div>
                      <div class="button-more">
                        <div class="title7">+ 16일차 도전중!</div>
                      </div>
                    </div>
                    <div class="card-popular-menu2">
                      <div class="info">
                        <div class="image">
                          <img class="icon-special-fish" src="@/assets/images/challenge/MyChallenge/icon-special-fish0.svg" />
                        </div>
                        <div class="frame-123">
                          <div class="title5">2025년 05월 19일 시작</div>
                          <div class="title6">고단백으로 먹기 챌린지</div>
                        </div>
                      </div>
                      <div class="button-more">
                        <div class="title7">+ 22일차 도전중!</div>
                      </div>
                    </div>
                  </div>
                  <div class="menu-nav3">
                    <div class="button-nav6"></div>
                    <div class="button-nav7"></div>
                  </div>
                </div>
              </div>
              <div class="widget-popular-menu">
                <div class="header-section">
                  <div class="div-title">
                    <div class="title4">달성한 챌린지</div>
                  </div>
                </div>
                <div class="scroll-div">
                  <div class="list-menu">
                    <div class="card-popular-menu">
                      <div class="info">
                        <div class="image">
                          <img
                            class="carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style"
                            src="@/assets/images/challenge/MyChallenge/carrot-vegetables-autumn-easter-3-d-vector-icon-cartoon-minimal-style1.png"
                          />
                        </div>
                        <div class="frame-123">
                          <div class="title5">2025년 05월 19일</div>
                          <div class="title6">한끼 채소 먹기 챌린지 달성!</div>
                        </div>
                      </div>
                      <div class="frame-124">
                        <div class="button-more2">
                          <img class="icon-check" src="@/assets/images/challenge/MyChallenge/icon-check0.svg" />
                          <div class="title8">총 2회 달성</div>
                        </div>
                        <div class="button-more3">
                          <div class="title7">+ 15 포인트</div>
                        </div>
                      </div>
                    </div>
                    <div class="card-popular-menu">
                      <div class="info">
                        <div class="image">
                          <img class="_3-d-lettuce" src="@/assets/images/challenge/MyChallenge/_3-d-lettuce1.png" />
                        </div>
                        <div class="frame-123">
                          <div class="title5">2025년 05월 19일</div>
                          <div class="title6">저염식 먹기 챌린지 달성!</div>
                        </div>
                      </div>
                      <div class="frame-124">
                        <div class="button-more2">
                          <img class="icon-check2" src="@/assets/images/challenge/MyChallenge/icon-check1.svg" />
                          <div class="title8">총 1회 달성</div>
                        </div>
                        <div class="button-more3">
                          <div class="title7">+ 15 포인트</div>
                        </div>
                      </div>
                    </div>
                    <div class="card-popular-menu">
                      <div class="info">
                        <div class="image">
                          <img class="icon-special-fish2" src="@/assets/images/challenge/MyChallenge/icon-special-fish1.svg" />
                        </div>
                        <div class="frame-123">
                          <div class="title5">2025년 05월 19일</div>
                          <div class="title6">고단백으로 먹기 챌린지 달성!</div>
                        </div>
                      </div>
                      <div class="frame-124">
                        <div class="button-more2">
                          <img class="icon-check3" src="@/assets/images/challenge/MyChallenge/icon-check2.svg" />
                          <div class="title8">총 5회 달성</div>
                        </div>
                        <div class="button-more3">
                          <div class="title7">+ 15 포인트</div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="menu-nav3">
                    <div class="button-nav8"></div>
                    <div class="button-nav7"></div>
                  </div>
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
</template> -->
