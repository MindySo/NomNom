<script setup>
import { ref, onMounted, computed, watch, nextTick } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

import WeeklyReport from '@/components/meal/WeeklyReport.vue';
import MonthlyReport from '@/components/meal/MonthlyReport.vue';
import RegisterMealModal from '@/components/meal/RegisterMealModal.vue';
import RegisterWaterModal from '@/components/meal/RegisterWaterModal.vue';

// 날짜 선택 ///////////////////////////////////////////////////////////////////////////////
const selectedDate = ref('');

// selectedDate.value = new Date().toISOString().split('T')[0];

// Pinia : 사용자 맞춤 영양소 권장량 store ///////////////////////////////////////////////////
import { userNutritionStore } from '@/stores/meal/nutritionStore';
const nutritionStore = userNutritionStore();

// Pinia : 식단 정보 및 일간, 주간, 월간 리포트 정보 store ////////////////////////////////////
import { useReportStore } from '@/stores/meal/mealReportStore';
const reportStore = useReportStore();

// Pinia : 차트 함수 store //////////////////////////////////////////////////////////////////
import { useChartRender } from '@/stores/meal/chartRenderStore';
const chartRender = useChartRender();

// 날짜 포맷 표시: 2024. 06. 24 (월)
function formatDateForDisplay(isoDate) {
  if (!isoDate) return '';
  const date = new Date(isoDate);
  const weekDay = ['일', '월', '화', '수', '목', '금', '토'][date.getDay()];
  return `${date.getFullYear()}. ${String(date.getMonth() + 1).padStart(
    2,
    '0'
  )}. ${String(date.getDate()).padStart(2, '0')} (${weekDay})`;
}

// 날짜 +- 1
function changeDate(val) {
  const date = new Date(selectedDate.value); // 문자열을 Date 객체로 변환
  date.setDate(date.getDate() + val); // 날짜 조작
  selectedDate.value = date.toISOString().slice(0, 10); // yyyy-MM-dd 형식으로 다시 저장

  console.log('changeDate', selectedDate.value);
}

// 날짜 변경 추적 ///////////////////////////////////////////////////////////////////////////
watch(selectedDate, async (newDate, oldDate) => {
  console.log('날짜 변경됨:', oldDate, '→', newDate);
  await nextTick();
  fetchAllReports();
});

const userNo = 1;
// 값 순서대로 가져오기 //////////////////////////////////////////////////////////////////////
async function fetchAllReports() {
  console.log('MealList-fetchAllReports' + selectedDate);

  console.time('fetchNutriStandard');
  await nutritionStore.fetchNutriStandard(userNo);
  console.timeEnd('fetchNutriStandard');

  console.time('fetchMeals');
  await reportStore.fetchMeals(userNo, selectedDate.value);
  console.timeEnd('fetchMeals');

  await Promise.all([
    reportStore.fetchDayReport(userNo, selectedDate.value),
    reportStore.fetchWeeklyReport(userNo),
    reportStore.fetchMonthlyReport(userNo),
  ]);

  console.time('requestDayReport');
  requestDayReport(selectedDate.value);
  console.timeEnd('requestDayReport');
}

// onMounted //////////////////////////////////////////////////////////////////////////////
onMounted(async () => {
  selectedDate.value = new Date().toISOString().split('T')[0];
  // await nextTick();
  // fetchAllReports();
});

// 식단 등록 시 데이터 다시 받아옴 ////////////////////////////////////////////////////////////
const onWaterOrMealRegistered = async () => {
  window.location.reload();
};

const requestMealDetail = (id) => {
  // detail 페이지의 라우터 정보를 줘야 함.
  // replace, push를 통해서 이동해야 함, 함수를 가지고 있는 객체를 얻어와야 함
  // router.push({path: '/board2/detail'});
  // router.push({name: 'boardDetail', query: {id} });
  // router.push({name: 'boardDetail', params: {id} });
};

// 주간, 월간 리포트 탭 전환
const selectedTab = ref('weekly');

// '아침', '점심', '저녁', '간식' 태그 별 색상 변환
function getMealCategoryClass(time) {
  switch (time) {
    case 'BREAKFAST':
      return 'badge-breakfast';
    case 'LUNCH':
      return 'badge-lunch';
    case 'DINNER':
      return 'badge-dinner';
    case 'SNACK':
      return 'badge-snack';
    default:
      return '';
  }
}

// 식사 시간 한글 변환
function convertMealTime(time) {
  if (time === 'BREAKFAST') return '아침';
  if (time === 'LUNCH') return '점심';
  if (time === 'DINNER') return '저녁';
  return time;
}

// // 날짜 포맷 (YYYY-MM-DD -> M월 D일)
// function formatDate(date) {
//   if (!date) return '';
//   const d = new Date(date);
//   return `${d.getMonth() + 1}월 ${d.getDate()}일`;
// }

// 더미 이미지 경로 지정
function getImagePath(index) {
  return new URL(
    `@/assets/images/meal/mealList/place-image-here${index % 3}.png`,
    import.meta.url
  ).href;
}

// 기본이미지 지정
import defaultImage from '@/assets/images/global/food_default.png';
function getImage(meal) {
  return meal.fileList?.[0]?.attachmentName || defaultImage;
}

// 일간 영양 분석 //////////////////////////////////////////////////////////////////////
async function requestDayReport() {
  chartRender.drawDoughnutCharts(
    calorieChart.value,
    reportStore.dayReport.energy,
    ' Kcal',
    nutritionStore.calorieGoal,
    '#EF5E5E'
  );
  chartRender.drawDoughnutCharts(
    waterChart.value,
    reportStore.dayReport.water,
    ' ml',
    nutritionStore.waterGoal,
    '#9cd9ff'
  );

  chartRender.drawBarCharts(
    dailyCarbChart.value,
    '탄수화물',
    reportStore.dayReport.carbohydrate,
    nutritionStore.carbGoal,
    '#a8e063'
  );
  chartRender.drawBarCharts(
    dailyProteinChart.value,
    '단백질',
    reportStore.dayReport.protein,
    nutritionStore.proteinGoal,
    '#ffa751'
  );
  chartRender.drawBarCharts(
    dailyFatChart.value,
    '지방',
    reportStore.dayReport.totalFattyAcids,
    nutritionStore.fatGoal,
    '#f9d423'
  );
}

// chart.js //////////////////////////////////////////////////////////////////////
import {
  Chart,
  DoughnutController,
  BarController,
  ArcElement,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend,
} from 'chart.js';
import MealDetailModal from './MealDetailModal.vue';

Chart.register(
  DoughnutController,
  BarController,
  ArcElement,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend
);

const calorieChart = ref(null);
const waterChart = ref(null);
const dailyCarbChart = ref(null);
const dailyProteinChart = ref(null);
const dailyFatChart = ref(null);

// 식단 추가 모달 /////////////////////////////////
const showMealModal = ref(false);
const showWaterModal = ref(false);
function openMealModal() {
  showMealModal.value = true;
}
function openWaterModal() {
  showWaterModal.value = true;
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
@import '@/assets/css/vars.css';
</style>

<style scoped>
@import '@/assets/css/meal/MealList.css';
</style>

<template>
  <div id="list">
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
            s
            <img
              class="icon-nav-calendar-dots"
              src="@/assets/images/meal/mealList/icon-nav-calendar-dots0.svg"
            />
            <div class="text">
              <div class="label">식단 기록</div>
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-bowl-food"
              src="@/assets/images/meal/mealList/icon-nav-bowl-food0.svg"
            />
            <div class="text2">
              <div class="label2">즐겨찾기</div>
            </div>
            <div class="icon">
              <img
                class="icon-caret-down"
                src="@/assets/images/meal/mealList/icon-caret-down0.svg"
              />
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-notebook"
              src="@/assets/images/meal/mealList/icon-nav-notebook0.svg"
            />
            <div class="text2">
              <div class="label2">커뮤니티</div>
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-chart-line-up"
              src="@/assets/images/meal/mealList/icon-nav-chart-line-up0.svg"
            />
            <div class="text2">
              <div class="label2">챌린지</div>
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-heartbeat"
              src="@/assets/images/meal/mealList/icon-nav-heartbeat0.svg"
            />
            <div class="text2">
              <div class="label2">마이페이지</div>
            </div>
          </div>
          <div class="button-nav3">
            <img
              class="icon-nav-sign-out"
              src="@/assets/images/meal/mealList/icon-nav-sign-out0.svg"
            />
            <div class="text3">
              <div class="label2">로그아웃</div>
            </div>
          </div>
        </div>
      </div>
      <div class="content">
        <div class="header2">
          <div class="title">나의 식단 기록</div>
        </div>
        <div class="body">
          <div class="widget-all-menu">
            <div class="date-schedulecnt">
              <div class="button"></div>

              <!-- 날짜 선택 -->
              <div class="frame-13">
                <img
                  class="chevron-left"
                  src="@/assets/images/meal/mealList/chevron-left0.svg"
                  @click="changeDate(-1)"
                />

                <input type="date" v-model="selectedDate" class="date-input" />

                <img
                  class="chevron-right"
                  src="@/assets/images/meal/mealList/chevron-right0.svg"
                  @click="changeDate(1)"
                />
              </div>
              <div class="frame-17">
                <button class="button2" @click="openWaterModal">
                  <div class="icon-left">
                    <img
                      class="icon-special-drop"
                      src="@/assets/images/meal/mealList/icon-special-drop0.svg"
                    />
                  </div>
                  <div class="text5">
                    <div class="label">물 기록하기</div>
                  </div>
                </button>
                <button class="button3" @click="openMealModal">
                  <div class="icon-left">
                    <img
                      class="icon-nav-fork-knife"
                      src="@/assets/images/meal/mealList/icon-nav-fork-knife0.svg"
                    />
                  </div>
                  <div class="text5">
                    <div class="label">식단 추가하기</div>
                  </div>
                </button>
              </div>
            </div>
            <div class="frame-132">
              <div class="frame-172">
                <!-- 오늘 식단 리스트 -->
                <div class="list-menu">
                  <!-- 섭취한 수분 총량 -->
                  <div class="card-list-all-menu">
                    <div class="main-content">
                      <div class="name">섭취한 수분 총량</div>
                      <div class="nutrition-info">
                        <div class="info-cal">
                          <img
                            class="icon-special-drop2"
                            src="@/assets/images/meal/mealList/icon-special-drop1.svg"
                          />
                          <div class="value">
                            <div class="amount">
                              {{ reportStore.dayReport.water }}ml
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <!-- 식단 리스트 -->
                  <div
                    v-for="(meal, index) in reportStore.meals"
                    :key="meal.mealNo"
                    class="card-list-all-menu2"
                    @click="openMealDetailModal"
                  >
                    <div class="image">
                      <img
                        class="place-image-here"
                        :src="
                          meal.fileList[0]
                            ? meal.fileList[0].attachmentName
                            : defaultImage
                        "
                      />
                    </div>
                    <div class="main-content2">
                      <div class="head-info">
                        <div class="badges-info">
                          <div
                            :class="[
                              'badge-meal-category',
                              getMealCategoryClass(meal.mealTime),
                            ]"
                          >
                            <div class="category">
                              {{ convertMealTime(meal.mealTime) }}
                            </div>
                          </div>
                        </div>
                        <div class="frame-1">
                          <div class="action">
                            <div class="button-picker">
                              <div class="text5">
                                <div class="label3">수정</div>
                              </div>
                            </div>
                          </div>
                          <div class="action">
                            <div class="button-picker2">
                              <div class="text5">
                                <div class="label3">삭제</div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="name2">{{ meal.mealTitle }}</div>

                      <div class="nutrition-info2">
                        <div class="info-cal">
                          <img
                            class="icon-special-fire"
                            src="@/assets/images/meal/mealList/icon-special-fire0.svg"
                          />
                          <div class="value2">
                            <div class="amount">{{ meal.energy }} kcal</div>
                          </div>
                        </div>

                        <img
                          class="separator"
                          src="@/assets/images/meal/mealList/separator0.svg"
                        />

                        <div class="info-carbs">
                          <img
                            class="icon-special-bread"
                            src="@/assets/images/meal/mealList/icon-special-bread0.svg"
                          />
                          <div class="value">
                            <div class="amount">탄수화물</div>
                            <div class="unit">{{ meal.carbohydrate }}g</div>
                          </div>
                        </div>

                        <img
                          class="separator2"
                          src="@/assets/images/meal/mealList/separator1.svg"
                        />

                        <div class="info-protein">
                          <img
                            class="icon-special-fish"
                            src="@/assets/images/meal/mealList/icon-special-fish0.svg"
                          />
                          <div class="value">
                            <div class="amount">단백질</div>
                            <div class="unit">{{ meal.protein }}g</div>
                          </div>
                        </div>

                        <img
                          class="separator3"
                          src="@/assets/images/meal/mealList/separator2.svg"
                        />

                        <div class="info-fats">
                          <img
                            class="icon-special-drop3"
                            src="@/assets/images/meal/mealList/icon-special-drop2.svg"
                          />
                          <div class="value">
                            <div class="amount">지방</div>
                            <div class="unit">{{ meal.totalFattyAcids }}g</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="day-frame">
                <!-- 일간 영양 분석 -->
                <div class="frame-173">
                  <div class="title2">영양 분석</div>
                </div>
                <div class="div3">
                  <div class="frame-16">
                    <div class="group-18">
                      <canvas
                        class="chart-doughnut"
                        ref="calorieChart"
                      ></canvas>
                      <div class="chart-doughnut-name">칼로리</div>
                    </div>
                    <div class="group-18">
                      <canvas class="chart-doughnut" ref="waterChart"></canvas>
                      <div class="chart-doughnut-name">물</div>
                    </div>
                  </div>
                  <div class="frame-162">
                    <div class="frame-133">
                      <div class="frame-14">
                        <div class="group-15">
                          <div class="frame-142">
                            <div class="title5">탄수화물</div>
                            <div class="title6">
                              {{ reportStore.dayReport.carbohydrate }} g
                            </div>
                          </div>
                          <div class="menu-nav3">
                            <canvas
                              class="chart-bar"
                              ref="dailyCarbChart"
                              style="width: 100%"
                            ></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">당류</div>
                            <div class="title7">
                              {{ reportStore.dayReport.sugar }} g
                            </div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">식이섬유</div>
                            <div class="title7">
                              {{ reportStore.dayReport.dietaryFiber }} g
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-14">
                        <div class="group-15">
                          <div class="frame-142">
                            <div class="title5">단백질</div>
                            <div class="title6">
                              {{ reportStore.dayReport.protein }} g
                            </div>
                          </div>
                          <div class="menu-nav3">
                            <canvas
                              class="chart-bar"
                              ref="dailyProteinChart"
                              style="width: 100%"
                            ></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">회분</div>
                            <div class="title7">
                              {{ reportStore.dayReport.ash }} g
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-163">
                        <div class="group-15">
                          <div class="frame-142">
                            <div class="title5">지방</div>
                            <div class="title6">
                              {{ reportStore.dayReport.totalFattyAcids }} g
                            </div>
                          </div>
                          <div class="menu-nav3">
                            <canvas
                              class="chart-bar"
                              ref="dailyFatChart"
                              style="width: 100%"
                            ></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">포화지방</div>
                            <div class="title7">
                              {{ reportStore.dayReport.saturatedFats }} g
                            </div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">불포화지방</div>
                            <div class="title7">
                              {{ reportStore.dayReport.unsaturatedFats }} g
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="frame-136">
                      <div class="frame-19">
                        <div class="title8">비타민</div>
                        <div class="frame-137">
                          <div class="frame-135">
                            <div class="title7">비타민A</div>
                            <div class="title7">
                              {{ reportStore.dayReport.vitaminACarotene }} µg
                            </div>
                          </div>
                          <div class="frame-18">
                            <div class="title7">비타민 B1 티아민</div>
                            <div class="title7">
                              {{ reportStore.dayReport.vitaminB1 }} mg
                            </div>
                          </div>
                          <div class="frame-192">
                            <div class="title7">비타민 B2 리보플라빈</div>
                            <div class="title7">
                              {{ reportStore.dayReport.vitaminB2 }} mg
                            </div>
                          </div>
                          <div class="frame-20">
                            <div class="title7">비타민 B3 니아신</div>
                            <div class="title7">
                              {{ reportStore.dayReport.niacin }} mg
                            </div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">엽산</div>
                            <div class="title7">
                              {{ reportStore.dayReport.folate }} µg
                            </div>
                          </div>
                          <div class="frame-152">
                            <div class="title7">비타민 C</div>
                            <div class="title7">
                              {{ reportStore.dayReport.vitaminC }} mg
                            </div>
                          </div>
                          <div class="frame-164">
                            <div class="title7">비타민 D</div>
                            <div class="title7">
                              {{ reportStore.dayReport.vitaminD }} µg
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-144">
                        <div class="frame-174">
                          <div class="title8">콜레스테롤</div>
                          <div class="frame-137">
                            <div class="frame-135">
                              <div class="title7"></div>
                              <div class="title7">
                                {{ reportStore.dayReport.cholesterol }} mg
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-182">
                          <div class="title8">무기질</div>
                          <div class="frame-137">
                            <div class="frame-135">
                              <div class="title7">나트륨</div>
                              <div class="title7">
                                {{ reportStore.dayReport.sodium }} mg
                              </div>
                            </div>
                            <div class="frame-143">
                              <div class="title7">칼슘</div>
                              <div class="title7">
                                {{ reportStore.dayReport.calcium }} mg
                              </div>
                            </div>
                            <div class="frame-152">
                              <div class="title7">철</div>
                              <div class="title7">
                                {{ reportStore.dayReport.iron }} mg
                              </div>
                            </div>
                            <div class="frame-164">
                              <div class="title7">인</div>
                              <div class="title7">
                                {{ reportStore.dayReport.phosphorus }} mg
                              </div>
                            </div>
                            <div class="frame-175">
                              <div class="title7">칼륨</div>
                              <div class="title7">
                                {{ reportStore.dayReport.potassium }} mg
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="content2">
            <div class="menu-nav4">
              <!-- 주간 리포트 버튼 -->
              <button
                :class="
                  selectedTab === 'weekly' ? 'button-nav6' : 'button-nav9'
                "
                @click="selectedTab = 'weekly'"
              >
                <div :class="selectedTab === 'weekly' ? 'label4' : 'label5'">
                  주간 리포트
                </div>
              </button>

              <!-- 월간 리포트 버튼 -->
              <button
                :class="
                  selectedTab === 'monthly' ? 'button-nav6' : 'button-nav9'
                "
                @click="selectedTab = 'monthly'"
              >
                <div :class="selectedTab === 'monthly' ? 'label4' : 'label5'">
                  월간 리포트
                </div>
              </button>
            </div>
            <div class="frame-11">
              <div class="weekly-summary" v-if="selectedTab === 'weekly'">
                <WeeklyReport />
              </div>

              <div
                class="monthly-summary"
                v-else-if="selectedTab === 'monthly'"
              >
                <MonthlyReport />
              </div>
            </div>
          </div>
        </div>
        <div class="section-footer">
          <div class="legal-information">
            <div class="copyright-2024-peterdraw">
              Copyright © 2024 Peterdraw
            </div>
            <div class="links">
              <div class="privacy-policy">Privacy Policy</div>
              <div class="term-and-conditions">Term and conditions</div>
              <div class="contact">Contact</div>
            </div>
          </div>
          <div class="social-media">
            <img
              class="facebook-logo"
              src="@/assets/images/meal/mealList/facebook-logo0.svg"
            />
            <img
              class="twitter-logo"
              src="@/assets/images/meal/mealList/twitter-logo0.svg"
            />
            <img
              class="instagram-logo"
              src="@/assets/images/meal/mealList/instagram-logo0.svg"
            />
            <img
              class="youtube-logo"
              src="@/assets/images/meal/mealList/youtube-logo0.svg"
            />
            <img
              class="linkedin-logo"
              src="@/assets/images/meal/mealList/linkedin-logo0.svg"
            />
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 식단 추가 모달 -->
  <RegisterMealModal
    :show="showMealModal"
    @close="showMealModal = false"
    @mealRegistered="onWaterOrMealRegistered"
  />

  <!-- 물 추가 모달 -->
  <RegisterWaterModal
    :show="showWaterModal"
    @close="showWaterModal = false"
    @waterRegistered="onWaterOrMealRegistered"
  />

  <!-- 상세보기 모달 -->
  <MealDetailModal
    :show="showMealDetailModal"
    @close="showMealDetailModal = false"
  />
</template>
