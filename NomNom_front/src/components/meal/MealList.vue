<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();
const meals = ref([]);
const dayReport = ref([]);
const weeklyReport = ref([]);
const monthlyReport = ref([]);

// 선택일자 식단 불러오기 ///////////////////////////////////////////////////////////////////
async function requestMealList(userNo, mealRegDate) {
  const { data } = await axios.get('http://localhost:8080/api/meal', {
    params: { userNo, mealRegDate },
  });
  meals.value = data;
}

// 식사 시간 한글 변환
function convertMealTime(time) {
  if (time === 'BREAKFAST') return '아침';
  if (time === 'LUNCH') return '점심';
  if (time === 'DINNER') return '저녁';
  return time;
}

// 날짜 포맷 (YYYY-MM-DD -> M월 D일)
function formatDate(date) {
  if (!date) return '';
  const d = new Date(date);
  return `${d.getMonth() + 1}월 ${d.getDate()}일`;
}

// 더미 이미지 경로 지정
function getImagePath(index) {
  return new URL(
    `@/assets/images/mealList/place-image-here${index % 3}.png`,
    import.meta.url
  ).href;
}

import defaultImage from '@/assets/images/global/food_default.png';
function getImage(meal) {
  return meal.fileList?.[0]?.attachmentName || defaultImage;
}
////////////////////////////////////////////////////////////////////////

// 일간 영양 분석 불러오기 //////////////////////////////////////////////////////////////////////
async function requestDayReport(userNo, mealRegDate) {
  const { data } = await axios.get(
    'http://localhost:8080/api/meal/report/daily',
    {
      params: { userNo, mealRegDate },
    }
  );
  dayReport.value = data;
  drawDailyCharts(data.energy, data.water);
}

//////////////////////////////////////////////////////////////////////

// 주간 리포트 불러오기 //////////////////////////////////////////////////////////////////////
async function requestweeklyReport(userNo) {
  const { data } = await axios.get(
    'http://localhost:8080/api/meal/report/weekly',
    {
      params: { userNo },
    }
  );
  weeklyReport.value = data;
  // drawWeeklyChart(weeklyReport.value.dailySumList);
  drawNormalizedWeeklyChart(weeklyReport.value.dailySumList);
}
``;

//////////////////////////////////////////////////////////////////////

// 월간 리포트 불러오기 //////////////////////////////////////////////////////////////////////
async function requestmonthlyReport(userNo) {
  const { data } = await axios.get(
    'http://localhost:8080/api/meal/report/monthly',
    {
      params: { userNo },
    }
  );
  monthlyReport.value = data;
}

//////////////////////////////////////////////////////////////////////

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
const carbChart = ref(null);
const proteinChart = ref(null);
const fatChart = ref(null);
const weeklyChartCanvas = ref(null);

// 샘플 데이터
const calorie = 2008.1;
const water = 1638.8;
const carb = 367.5;
const protein = 48.5;
const fat = 32.6;

const calorieGoal = 2500;
const waterGoal = 2000;
const carbGoal = 400;
const proteinGoal = 60;
const fatGoal = 50;

// 일간 : 도넛
const createDoughnutData = (value, goal, color) => {
  if (value >= goal) {
    return {
      datasets: [
        {
          data: [1],
          backgroundColor: [color],
        },
      ],
      labels: ['섭취'],
    };
  } else {
    return {
      datasets: [
        {
          data: [value, goal - value],
          backgroundColor: [color, '#e0e0e0'],
        },
      ],
      labels: ['섭취', '남은'],
    };
  }
};

// 일간 : 바
const createBarData = (label, value, goal, color) => {
  if (value >= goal) {
    return {
      labels: [''],
      datasets: [
        {
          label: '섭취량',
          data: [value],
          backgroundColor: color,
          borderRadius: 2,
          barThickness: 8,
        },
      ],
    };
  } else {
    return {
      labels: [''],
      datasets: [
        {
          label: '섭취량',
          data: [value],
          backgroundColor: color,
          borderRadius: 2,
          barThickness: 8,
        },
        {
          label: '남은양',
          data: [goal - value],
          backgroundColor: '#e0e0e0',
          borderRadius: 2,
          barThickness: 8,
        },
      ],
    };
  }
};

// 주간 : 바
// function drawWeeklyChart(dailySumList) {
//   const ctx = weeklyChartCanvas.value;
//   if (!ctx) return;

//   const data = createWeeklyBarData(dailySumList);

//   new Chart(ctx, {
//     type: 'bar',
//     data,
//     options: {
//       responsive: true,
//       maintainAspectRatio: false,
//       scales: {
//         x: {
//           stacked: false,
//           grid: { display: false },
//         },
//         y: {
//           stacked: false,
//           beginAtZero: true,
//         },
//       },
//       plugins: {
//         legend: { display: true },
//         tooltip: { enabled: true },
//       },
//     },
//   });
// }
// function createWeeklyBarData(dailySumList) {
//   const labels = dailySumList.map((item) => item.reportDate.slice(5)); // "MM-DD" 포맷

//   const carbData = dailySumList.map((item) => item.carbohydrate);
//   const proteinData = dailySumList.map((item) => item.protein);
//   const fatData = dailySumList.map((item) => item.totalFattyAcids);
//   const waterData = dailySumList.map((item) => item.water);

//   return {
//     labels,
//     datasets: [
//       {
//         label: '탄수화물',
//         data: carbData,
//         backgroundColor: '#a8e063',
//       },
//       {
//         label: '단백질',
//         data: proteinData,
//         backgroundColor: '#ffa751',
//       },
//       {
//         label: '지방',
//         data: fatData,
//         backgroundColor: '#f9d423',
//       },
//       {
//         label: '수분',
//         data: waterData,
//         backgroundColor: '#69b7f0',
//       },
//     ],
//   };
// }
function drawNormalizedWeeklyChart(dailySumList) {
  const ctx = weeklyChartCanvas.value;
  if (!ctx) return;

  new Chart(ctx, {
    type: 'bar',
    data: createNormalizedWeeklyBarData(dailySumList),
    weeklyChartoptions,
  });
}
const weeklyChartoptions = {
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: {
      stacked: true,
      grid: { display: false },
      ticks: {
        color: '#444',
        font: { size: 11 },
        callback: function (val, index) {
          return this.getLabelForValue(val).replaceAll('-', '.');
        },
      },
    },
    y: {
      stacked: true,
      display: false, // 눈금 없애기
      grid: { display: false },
      ticks: { display: false },
      max: 100, // 비율 고정
    },
  },
  plugins: {
    legend: { display: false },
    tooltip: { enabled: false },
  },
};
function createNormalizedWeeklyBarData(dailySumList) {
  const labels = dailySumList.map((item) => item.reportDate);

  const maxGoals = {
    carbohydrate: 400,
    protein: 60,
    fat: 50,
    water: 2000,
  };

  const carbData = dailySumList.map((item) =>
    Math.min(100, (item.carbohydrate / maxGoals.carbohydrate) * 100)
  );
  const proteinData = dailySumList.map((item) =>
    Math.min(100, (item.protein / maxGoals.protein) * 100)
  );
  const fatData = dailySumList.map((item) =>
    Math.min(100, (item.totalFattyAcids / maxGoals.fat) * 100)
  );
  const waterData = dailySumList.map((item) =>
    Math.min(100, (item.water / maxGoals.water) * 100)
  );

  const gray = '#eeeeee';

  return {
    labels,
    datasets: [
      {
        label: '탄수화물',
        data: carbData,
        stack: 'carb',
        backgroundColor: '#a8e063',
        borderRadius: 2,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      {
        label: '단백질',
        data: proteinData,
        stack: 'protein',
        backgroundColor: '#ffa751',
        borderRadius: 2,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      {
        label: '지방',
        data: fatData,
        stack: 'fat',
        backgroundColor: '#f9d423',
        borderRadius: 2,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      {
        label: '수분',
        data: waterData,
        stack: 'water',
        backgroundColor: '#69b7f0',
        borderRadius: 2,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      // 남은 양 (회색)
      {
        label: '남은 탄수화물',
        data: carbData.map((v) => 100 - v),
        stack: 'carb',
        backgroundColor: gray,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      {
        label: '남은 단백질',
        data: proteinData.map((v) => 100 - v),
        stack: 'protein',
        backgroundColor: gray,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      {
        label: '남은 지방',
        data: fatData.map((v) => 100 - v),
        stack: 'fat',
        backgroundColor: gray,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
      {
        label: '남은 수분',
        data: waterData.map((v) => 100 - v),
        stack: 'water',
        backgroundColor: gray,
        // barThickness: 8,
        categoryPercentage: 0.8,
        barPercentage: 0.4,
      },
    ],
  };
}

// 도넛 그래프 중앙에 글씨
const centerTextPlugin = (val) => ({
  id: 'centerText',
  beforeDraw(chart) {
    const { width, height, ctx } = chart;
    const text = `${val}`;
    ctx.restore();
    ctx.font = 'bold 16px Arial';
    ctx.textBaseline = 'middle';
    ctx.textAlign = 'center';
    ctx.fillStyle = '#1c1c1c';
    ctx.fillText(text, width / 2, height / 2);
    ctx.save();
  },
});

// 도넛 그래프 그리기
function drawDailyCharts(energy, water) {
  const canvas1 = calorieChart.value;
  canvas1.height = 150;
  new Chart(canvas1, {
    type: 'doughnut',
    data: createDoughnutData(energy, calorieGoal, '#f5c542'),
    options: {
      plugins: {
        tooltip: { enabled: false },
        legend: { display: false },
      },
      cutout: '70%',
    },
    plugins: [centerTextPlugin(`${energy} Kcal`)],
  });

  const canvas2 = waterChart.value;
  canvas2.height = 150;
  new Chart(canvas2, {
    type: 'doughnut',
    data: createDoughnutData(water, waterGoal, '#9cd9ff'),
    options: {
      plugins: {
        tooltip: { enabled: false },
        legend: { display: false },
      },
      cutout: '70%',
    },
    plugins: [centerTextPlugin(`${water} ml`)],
  });
}
/////////////////////////

// 생성
onMounted(() => {
  const carbcanvas = carbChart.value;
  carbcanvas.height = 8;
  const procanvas = proteinChart.value;
  procanvas.height = 8;
  const fatcanvas = fatChart.value;
  fatcanvas.height = 8;

  const toPercent = (value, goal) => Math.min(100, (value / goal) * 100);
  const toRemaining = (value, goal) =>
    Math.max(0, 100 - toPercent(value, goal));

  new Chart(carbChart.value, {
    type: 'bar',
    data: createBarData('탄수화물', carb, carbGoal, '#a8e063'),
    options: {
      responsive: false,
      maintainAspectRatio: false,
      indexAxis: 'y',
      scales: {
        x: {
          stacked: true,
          max: carbGoal,
          display: false,
          grid: { display: false },
          ticks: { display: false },
        },
        y: {
          stacked: true,
          display: false,
          grid: { display: false },
          ticks: { display: false },
        },
      },
      plugins: {
        legend: { display: false },
        tooltip: { enabled: false },
      },
    },
  });

  new Chart(proteinChart.value, {
    type: 'bar',
    data: createBarData('단백질', protein, proteinGoal, '#ffa751'),
    options: {
      responsive: false,
      maintainAspectRatio: false,
      indexAxis: 'y',
      scales: {
        x: {
          stacked: true,
          max: proteinGoal,
          display: false,
          grid: { display: false },
          ticks: { display: false },
        },
        y: {
          stacked: true,
          display: false,
          grid: { display: false },
          ticks: { display: false },
        },
      },
      plugins: {
        legend: { display: false },
        tooltip: { enabled: false },
      },
    },
  });

  new Chart(fatChart.value, {
    type: 'bar',
    data: createBarData('지방', fat, fatGoal, '#f9d423'),
    options: {
      responsive: false,
      maintainAspectRatio: false,
      indexAxis: 'y',
      scales: {
        x: {
          stacked: true,
          max: fatGoal,
          display: false,
          grid: { display: false },
          ticks: { display: false },
        },
        y: {
          stacked: true,
          display: false,
          grid: { display: false },
          ticks: { display: false },
        },
      },
      plugins: {
        legend: { display: false },
        tooltip: { enabled: false },
      },
    },
  });
});

//////////////////////////////////////////////////////////////////////

// 실행 (테스트용으로 userNo=1, date는 하드코딩)
onMounted(() => {
  requestMealList(1, '2025-05-21');
  requestDayReport(1, '2025-05-21');
  requestweeklyReport(1);
  requestmonthlyReport(1);
});

const requestMealDetail = (id) => {
  // detail 페이지의 라우터 정보를 줘야 함.
  // replace, push를 통해서 이동해야 함, 함수를 가지고 있는 객체를 얻어와야 함
  // router.push({path: '/board2/detail'});
  // router.push({name: 'boardDetail', query: {id} });
  // router.push({name: 'boardDetail', params: {id} });
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
@import '@/assets/css/vars.css';
</style>

<style scoped>
@import '@/assets/css/MealList.css';
.chart-doughnut {
  max-height: 150px;
  max-width: 150px;
  margin-bottom: 1rem;
}
.chart-bar {
  width: 143px;
  height: 5px;
  border-radius: 10;
  display: block;
}
</style>

<template>
  <div>
    <canvas class="chart-doughnut" ref="calorieChart"></canvas>
    <canvas class="chart-doughnut" ref="waterChart"></canvas>
    <canvas class="chart-bar" ref="barChart"></canvas>
  </div>

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
            <img
              class="icon-nav-calendar-dots"
              src="@/assets/images/mealList/icon-nav-calendar-dots0.svg"
            />
            <div class="text">
              <div class="label">식단 기록</div>
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-bowl-food"
              src="@/assets/images/mealList/icon-nav-bowl-food0.svg"
            />
            <div class="text2">
              <div class="label2">즐겨찾기</div>
            </div>
            <div class="icon">
              <img
                class="icon-caret-down"
                src="@/assets/images/mealList/icon-caret-down0.svg"
              />
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-notebook"
              src="@/assets/images/mealList/icon-nav-notebook0.svg"
            />
            <div class="text2">
              <div class="label2">커뮤니티</div>
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-chart-line-up"
              src="@/assets/images/mealList/icon-nav-chart-line-up0.svg"
            />
            <div class="text2">
              <div class="label2">챌린지</div>
            </div>
          </div>
          <div class="button-nav2">
            <img
              class="icon-nav-heartbeat"
              src="@/assets/images/mealList/icon-nav-heartbeat0.svg"
            />
            <div class="text2">
              <div class="label2">마이페이지</div>
            </div>
          </div>
          <div class="button-nav3">
            <img
              class="icon-nav-sign-out"
              src="@/assets/images/mealList/icon-nav-sign-out0.svg"
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
              <div class="frame-13">
                <img
                  class="chevron-left"
                  src="@/assets/images/mealList/chevron-left0.svg"
                />
                <div class="text4">
                  <img
                    class="vector"
                    src="@/assets/images/mealList/vector0.svg"
                  />
                  <div class="day">
                    <div class="year">
                      <div class="year2">2024</div>
                      <div class="div2">년</div>
                    </div>
                    <div class="month">
                      <div class="month2">06</div>
                      <div class="div2">월</div>
                    </div>
                    <div class="day2">
                      <div class="day3">24</div>
                      <div class="div2">일</div>
                    </div>
                    <div class="day2">
                      <div class="div2">(</div>
                      <div class="div2">수</div>
                      <div class="div2">)</div>
                    </div>
                  </div>
                </div>
                <img
                  class="chevron-right"
                  src="@/assets/images/mealList/chevron-right0.svg"
                />
              </div>
              <div class="frame-17">
                <div class="button2">
                  <div class="icon-left">
                    <img
                      class="icon-special-drop"
                      src="@/assets/images/mealList/icon-special-drop0.svg"
                    />
                  </div>
                  <div class="text5">
                    <div class="label">물 기록하기</div>
                  </div>
                </div>
                <div class="button3">
                  <div class="icon-left">
                    <img
                      class="icon-nav-fork-knife"
                      src="@/assets/images/mealList/icon-nav-fork-knife0.svg"
                    />
                  </div>
                  <div class="text5">
                    <div class="label">식단 추가하기</div>
                  </div>
                </div>
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
                            src="@/assets/images/mealList/icon-special-drop1.svg"
                          />
                          <div class="value">
                            <div class="amount">{{ dayReport.water }}ml</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <!-- 식단 리스트 -->
                  <div
                    v-for="(meal, index) in meals"
                    :key="meal.mealNo"
                    class="card-list-all-menu2"
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
                          <div class="badge-meal-category">
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
                            src="@/assets/images/mealList/icon-special-fire0.svg"
                          />
                          <div class="value2">
                            <div class="amount">{{ meal.energy }} kcal</div>
                          </div>
                        </div>

                        <img
                          class="separator"
                          src="@/assets/images/mealList/separator0.svg"
                        />

                        <div class="info-carbs">
                          <img
                            class="icon-special-bread"
                            src="@/assets/images/mealList/icon-special-bread0.svg"
                          />
                          <div class="value">
                            <div class="amount">탄수화물</div>
                            <div class="unit">{{ meal.carbohydrate }}g</div>
                          </div>
                        </div>

                        <img
                          class="separator2"
                          src="@/assets/images/mealList/separator1.svg"
                        />

                        <div class="info-protein">
                          <img
                            class="icon-special-fish"
                            src="@/assets/images/mealList/icon-special-fish0.svg"
                          />
                          <div class="value">
                            <div class="amount">단백질</div>
                            <div class="unit">{{ meal.protein }}g</div>
                          </div>
                        </div>

                        <img
                          class="separator3"
                          src="@/assets/images/mealList/separator2.svg"
                        />

                        <div class="info-fats">
                          <img
                            class="icon-special-drop3"
                            src="@/assets/images/mealList/icon-special-drop2.svg"
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
                    </div>
                    <div class="group-17">
                      <canvas class="chart-doughnut" ref="waterChart"></canvas>
                    </div>
                  </div>
                  <div class="frame-162">
                    <div class="frame-133">
                      <div class="frame-14">
                        <div class="group-15">
                          <div class="frame-142">
                            <div class="title5">탄수화물</div>
                            <div class="title6">
                              {{ dayReport.carbohydrate }} g
                            </div>
                          </div>
                          <div class="menu-nav3">
                            <canvas class="chart-bar" ref="carbChart"></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">당류</div>
                            <div class="title7">{{ dayReport.sugar }} g</div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">식이섬유</div>
                            <div class="title7">
                              {{ dayReport.dietaryFiber }} g
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-14">
                        <div class="group-15">
                          <div class="frame-142">
                            <div class="title5">단백질</div>
                            <div class="title6">{{ dayReport.protein }} g</div>
                          </div>
                          <div class="menu-nav3">
                            <canvas
                              class="chart-bar"
                              ref="proteinChart"
                            ></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">회분</div>
                            <div class="title7">{{ dayReport.ash }} g</div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-163">
                        <div class="group-15">
                          <div class="frame-142">
                            <div class="title5">지방</div>
                            <div class="title6">
                              {{ dayReport.totalFattyAcids }} g
                            </div>
                          </div>
                          <div class="menu-nav3">
                            <canvas class="chart-bar" ref="fatChart"></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">포화지방</div>
                            <div class="title7">
                              {{ dayReport.saturatedFats }} g
                            </div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">불포화지방</div>
                            <div class="title7">
                              {{ dayReport.unsaturatedFats }} g
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
                              {{ dayReport.vitaminACarotene }} µg
                            </div>
                          </div>
                          <div class="frame-18">
                            <div class="title7">비타민 B1 티아민</div>
                            <div class="title7">
                              {{ dayReport.vitaminB1 }} mg
                            </div>
                          </div>
                          <div class="frame-192">
                            <div class="title7">비타민 B2 리보플라빈</div>
                            <div class="title7">
                              {{ dayReport.vitaminB2 }} mg
                            </div>
                          </div>
                          <div class="frame-20">
                            <div class="title7">비타민 B3 니아신</div>
                            <div class="title7">{{ dayReport.niacin }} mg</div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">엽산</div>
                            <div class="title7">{{ dayReport.folate }} µg</div>
                          </div>
                          <div class="frame-152">
                            <div class="title7">비타민 C</div>
                            <div class="title7">
                              {{ dayReport.vitaminC }} mg
                            </div>
                          </div>
                          <div class="frame-164">
                            <div class="title7">비타민 D</div>
                            <div class="title7">
                              {{ dayReport.vitaminD }} µg
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
                                {{ dayReport.cholesterol }} mg
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
                                {{ dayReport.sodium }} mg
                              </div>
                            </div>
                            <div class="frame-143">
                              <div class="title7">칼슘</div>
                              <div class="title7">
                                {{ dayReport.calcium }} mg
                              </div>
                            </div>
                            <div class="frame-152">
                              <div class="title7">철</div>
                              <div class="title7">{{ dayReport.iron }} mg</div>
                            </div>
                            <div class="frame-164">
                              <div class="title7">인</div>
                              <div class="title7">
                                {{ dayReport.phosphorus }} mg
                              </div>
                            </div>
                            <div class="frame-175">
                              <div class="title7">칼륨</div>
                              <div class="title7">
                                {{ dayReport.potassium }} mg
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
              <div class="button-nav6">
                <div class="label4">주간 리포트</div>
              </div>
              <div class="button-nav9">
                <div class="label5">월간 리포트</div>
              </div>
            </div>
            <div class="frame-11">
              <div class="div4">
                <div class="calender-frame">
                  <div class="frame-164">
                    <div class="title2">최근 7일 주요 영양소 기록</div>
                    <div class="frame-165">
                      <div class="frame-166">
                        <div class="button-nav10"></div>
                        <div class="amount">탄수화물</div>
                      </div>
                      <div class="frame-176">
                        <div class="button-nav11"></div>
                        <div class="amount">단백질</div>
                      </div>
                      <div class="frame-183">
                        <div class="button-nav12"></div>
                        <div class="amount">지방</div>
                      </div>
                      <div class="frame-193">
                        <div class="button-nav13"></div>
                        <div class="amount">수분</div>
                      </div>
                    </div>
                  </div>
                  <div style="width: 100%; height: 300px">
                    <canvas ref="weeklyChartCanvas"></canvas>
                  </div>
                  <div class="day-date">
                    <div class="date-number-line-04">
                      <div class="frame-194">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-202">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-212">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-22">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-23">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-24">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-25">
                        <div class="frame-184">
                          <div class="date">
                            <div class="frame-21">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav14"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-153">
                              <div class="menu-nav5">
                                <div class="button-nav5"></div>
                                <div class="button-nav15"></div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav16"></div>
                                </div>
                              </div>
                            </div>
                            <div class="frame-177">
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav17"></div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="line"></div>
                    <div class="day-line">
                      <div class="frame-178">
                        <div class="day4">
                          <div class="_25-06-18">25.06.18</div>
                        </div>
                        <div class="day4">
                          <div class="_25-06-19">25.06.19</div>
                        </div>
                        <div class="day4">
                          <div class="_25-06-20">25.06.20</div>
                        </div>
                        <div class="day4">
                          <div class="_25-06-21">25.06.21</div>
                        </div>
                        <div class="day4">
                          <div class="_25-06-22">25.06.22</div>
                        </div>
                        <div class="day4">
                          <div class="_25-06-23">25.06.23</div>
                        </div>
                        <div class="day4">
                          <div class="_25-06-24">25.06.24</div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <!-- 주간 통계 찍어보기 -->
                  <div
                    v-for="(day, index) in weeklyReport.dailySumList"
                    :key="index"
                  >
                    <p>
                      {{ day.reportDate }} : {{ day.carbohydrate }}g,
                      {{ day.protein }}g
                    </p>
                  </div>
                </div>
                <div class="frame-167">
                  <!-- 끼니별 칼로리 분석 -->
                  <div class="frame-168">
                    <div class="title2">끼니별 칼로리 분석(웰스토리 카피)</div>
                    <div class="frame-169">
                      <div
                        class="frame-179"
                        style="
                          background: url(frame-1721.png) center;
                          background-size: cover;
                          background-repeat: no-repeat;
                        "
                      >
                        <div class="title9">아침</div>
                        <div>
                          <p>{{ weeklyReport.avgBreakfastEnergy }}</p>
                        </div>
                      </div>
                      <div
                        class="frame-185"
                        style="
                          background: url(frame-1810.png) center;
                          background-size: cover;
                          background-repeat: no-repeat;
                        "
                      >
                        <div class="title10">점심</div>
                        <div>
                          <p>{{ weeklyReport.avgLunchEnergy }}</p>
                        </div>
                      </div>
                      <div
                        class="frame-186"
                        style="
                          background: url(frame-1811.png) center;
                          background-size: cover;
                          background-repeat: no-repeat;
                        "
                      >
                        <div class="title11">저녁</div>
                        <div>
                          <p>{{ weeklyReport.avgDinnerEnergy }}</p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="frame-1610"></div>
                  <!-- 주간 영양소 평균 -->
                  <div class="frame-1710">
                    <div class="title2">주간 영양소 평균</div>
                    <div class="frame-1611">
                      <div class="group-152">
                        <div class="frame-145">
                          <div class="title5">칼로리</div>
                          <div class="menu-nav6">
                            <div class="button-nav18"></div>
                            <div class="button-nav5"></div>
                          </div>
                          <div class="frame-1612">
                            <div class="title12">
                              {{ weeklyReport.avgWeeklyEnergy }} g
                            </div>
                            <div class="title13">/</div>
                            <div class="title13">13g</div>
                          </div>
                        </div>
                      </div>
                      <div class="group-16">
                        <div class="frame-146">
                          <div class="title5">탄수화물</div>
                          <div class="menu-nav6">
                            <div class="button-nav19"></div>
                            <div class="button-nav5"></div>
                          </div>
                          <div class="frame-1612">
                            <div class="title12">
                              {{ weeklyReport.avgWeeklycarbohydrate }} g
                            </div>
                            <div class="title13">/</div>
                            <div class="title13">13g</div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-147">
                        <div class="title5">단백질</div>
                        <div class="menu-nav6">
                          <div class="button-nav20"></div>
                          <div class="button-nav5"></div>
                        </div>
                        <div class="frame-1612">
                          <div class="title12">
                            {{ weeklyReport.avgWeeklyProtein }} g
                          </div>
                          <div class="title13">/</div>
                          <div class="title13">13g</div>
                        </div>
                      </div>
                      <div class="frame-154">
                        <div class="title5">지방</div>
                        <div class="menu-nav6">
                          <div class="button-nav8"></div>
                          <div class="button-nav21"></div>
                        </div>
                        <div class="frame-1612">
                          <div class="title12">
                            {{ weeklyReport.avgWeeklyTotalFattyAcids }} g
                          </div>
                          <div class="title13">/</div>
                          <div class="title13">13g</div>
                        </div>
                      </div>
                      <div class="frame-1613">
                        <div class="title5">수분</div>
                        <div class="menu-nav6">
                          <div class="button-nav22"></div>
                          <div class="button-nav5"></div>
                        </div>
                        <div class="frame-1612">
                          <div class="title12">
                            {{ weeklyReport.avgWeeklyWater }} g
                          </div>
                          <div class="title13">/</div>
                          <div class="title13">13g</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="div5">
                <div class="calender-frame2">
                  <div class="frame-203">
                    <div class="title2">월간 식단 점수</div>
                    <div class="title14">
                      {{ monthlyReport.reportStartDate }} ~
                      {{ monthlyReport.reportEndDate }}
                    </div>
                    <div class="frame-204">
                      <div class="frame-205">
                        <div class="title15">89</div>
                        <div class="title16">점</div>
                      </div>
                    </div>
                  </div>
                  <div class="frame-1610"></div>
                  <div class="frame-206">
                    <div class="frame-164">
                      <div class="title2">요일별 영양소 섭취 평균</div>
                      <div class="frame-165">
                        <div class="frame-166">
                          <div class="button-nav10"></div>
                          <div class="amount">탄수화물</div>
                        </div>
                        <div class="frame-176">
                          <div class="button-nav11"></div>
                          <div class="amount">단백질</div>
                        </div>
                        <div class="frame-183">
                          <div class="button-nav12"></div>
                          <div class="amount">지방</div>
                        </div>
                        <div class="frame-193">
                          <div class="button-nav13"></div>
                          <div class="amount">수분</div>
                        </div>
                      </div>
                    </div>
                    <div class="day-date">
                      <div class="date-number-line-04">
                        <div class="frame-194">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-202">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-212">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-22">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-23">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-24">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-25">
                          <div class="frame-184">
                            <div class="date">
                              <div class="frame-21">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav14"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-153">
                                <div class="menu-nav5">
                                  <div class="button-nav5"></div>
                                  <div class="button-nav15"></div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav16"></div>
                                  </div>
                                </div>
                              </div>
                              <div class="frame-177">
                                <div class="frame-153">
                                  <div class="menu-nav5">
                                    <div class="button-nav5"></div>
                                    <div class="button-nav17"></div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="line"></div>
                      <div class="day-line">
                        <div class="frame-178">
                          <div class="day4">
                            <div class="div6">월</div>
                          </div>
                          <div class="day4">
                            <div class="div7">화</div>
                          </div>
                          <div class="day4">
                            <div class="div7">수</div>
                          </div>
                          <div class="day4">
                            <div class="div7">목</div>
                          </div>
                          <div class="day4">
                            <div class="div7">금</div>
                          </div>
                          <div class="day4">
                            <div class="div7">토</div>
                          </div>
                          <div class="day4">
                            <div class="div6">일</div>
                          </div>
                        </div>
                      </div>
                      <!-- 주간 통계 찍어보기 -->
                      <div
                        v-for="(
                          weekDay, index
                        ) in monthlyReport.weekdayReportList"
                        :key="index"
                      >
                        <p>
                          {{ weekDay.reportWeekday }} : {{ weekDay.reportDate }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="frame-167">
                  <div class="frame-168">
                    <div class="title2">끼니별 식사 횟수</div>
                    <div class="frame-169">
                      <div
                        class="frame-179"
                        style="
                          background: url(frame-1739.png) center;
                          background-size: cover;
                          background-repeat: no-repeat;
                        "
                      >
                        <div class="title17">
                          아침
                          <br />
                          9회
                        </div>
                      </div>
                      <div
                        class="frame-185"
                        style="
                          background: url(frame-1820.png) center;
                          background-size: cover;
                          background-repeat: no-repeat;
                        "
                      >
                        <div class="title18">
                          점심
                          <br />
                          30회
                        </div>
                      </div>
                      <div
                        class="frame-186"
                        style="
                          background: url(frame-1821.png) center;
                          background-size: cover;
                          background-repeat: no-repeat;
                        "
                      >
                        <div class="title19">
                          저녁
                          <br />
                          27회
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="frame-1610"></div>
                  <div class="frame-1710">
                    <div class="title2">월 평균 영양성분 섭취량 평가</div>
                    <div class="frame-1611">
                      <div class="group-16">
                        <div class="frame-145">
                          <div class="title5">탄수화물</div>
                          <div class="menu-nav6">
                            <div class="button-nav19"></div>
                            <div class="button-nav5"></div>
                          </div>
                          <div class="title20">미달</div>
                          <div class="frame-1614">
                            <div class="title21">13g</div>
                            <div class="title22">/</div>
                            <div class="title22">13g</div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-147">
                        <div class="title5">단백질</div>
                        <div class="menu-nav6">
                          <div class="button-nav20"></div>
                          <div class="button-nav5"></div>
                        </div>
                        <div class="title23">적정</div>
                        <div class="frame-1614">
                          <div class="title21">13g</div>
                          <div class="title22">/</div>
                          <div class="title22">13g</div>
                        </div>
                      </div>
                      <div class="frame-154">
                        <div class="title5">지방</div>
                        <div class="menu-nav6">
                          <div class="button-nav8"></div>
                          <div class="button-nav21"></div>
                        </div>
                        <div class="title23">적정</div>
                        <div class="frame-1614">
                          <div class="title21">13g</div>
                          <div class="title22">/</div>
                          <div class="title22">13g</div>
                        </div>
                      </div>
                      <div class="frame-1613">
                        <div class="title5">나트륨</div>
                        <div class="menu-nav7">
                          <div class="button-nav23"></div>
                          <div class="button-nav5"></div>
                        </div>
                        <div class="title24">초과</div>
                        <div class="frame-1614">
                          <div class="title21">13g</div>
                          <div class="title22">/</div>
                          <div class="title22">13g</div>
                        </div>
                      </div>
                      <div class="frame-148">
                        <div class="title5">당</div>
                        <div class="menu-nav6">
                          <div class="button-nav24"></div>
                          <div class="button-nav5"></div>
                        </div>
                        <div class="title23">적정</div>
                        <div class="frame-1614">
                          <div class="title21">13g</div>
                          <div class="title22">/</div>
                          <div class="title22">13g</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
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
              src="@/assets/images/mealList/facebook-logo0.svg"
            />
            <img
              class="twitter-logo"
              src="@/assets/images/mealList/twitter-logo0.svg"
            />
            <img
              class="instagram-logo"
              src="@/assets/images/mealList/instagram-logo0.svg"
            />
            <img
              class="youtube-logo"
              src="@/assets/images/mealList/youtube-logo0.svg"
            />
            <img
              class="linkedin-logo"
              src="@/assets/images/mealList/linkedin-logo0.svg"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
