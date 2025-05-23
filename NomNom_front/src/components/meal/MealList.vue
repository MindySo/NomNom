<script setup>
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();
const meals = ref([]);
const dayReport = ref([]);
const weeklyReport = ref([]);
const monthlyReport = ref([]);
const userNutriStandard = ref([]);

// 선택일자 식단 불러오기 ///////////////////////////////////////////////////////////////////
async function requestMealList(userNo, mealRegDate) {
  const { data } = await axios.get("http://localhost:8080/api/meal", {
    params: { userNo, mealRegDate },
  });
  meals.value = data;
}

// 사용자 맞춤 영양소 권장량 불러오기 ///////////////////////////////////////////////////////////////////
async function requestNutriStandard(userNo) {
  const { data } = await axios.get("http://localhost:8080/api/meal/report/nutriStandard", {
    params: { userNo },
  });
  userNutriStandard.value = data;
}

// 식사 시간 한글 변환
function convertMealTime(time) {
  if (time === "BREAKFAST") return "아침";
  if (time === "LUNCH") return "점심";
  if (time === "DINNER") return "저녁";
  return time;
}

// 날짜 포맷 (YYYY-MM-DD -> M월 D일)
function formatDate(date) {
  if (!date) return "";
  const d = new Date(date);
  return `${d.getMonth() + 1}월 ${d.getDate()}일`;
}

// 더미 이미지 경로 지정
function getImagePath(index) {
  return new URL(`@/assets/images/mealList/place-image-here${index % 3}.png`, import.meta.url).href;
}

import defaultImage from "@/assets/images/global/food_default.png";
function getImage(meal) {
  return meal.fileList?.[0]?.attachmentName || defaultImage;
}
////////////////////////////////////////////////////////////////////////

// 일간 영양 분석 불러오기 //////////////////////////////////////////////////////////////////////
async function requestDayReport(userNo, mealRegDate) {
  const { data } = await axios.get("http://localhost:8080/api/meal/report/daily", {
    params: { userNo, mealRegDate },
  });
  dayReport.value = data;

  drawDoughnutCharts(calorieChart.value, dayReport.value.energy, " Kcal", calorieGoal, "#EF5E5E");
  drawDoughnutCharts(waterChart.value, dayReport.value.water, " ml", waterGoal, "#9cd9ff");

  drawBarCharts(dailyCarbChart.value, "탄수화물", dayReport.value.carbohydrate, carbGoal, "#a8e063");
  drawBarCharts(dailyProteinChart.value, "단백질", dayReport.value.protein, proteinGoal, "#ffa751");
  drawBarCharts(dailyFatChart.value, "지방", dayReport.value.totalFattyAcids, fatGoal, "#f9d423");
}

//////////////////////////////////////////////////////////////////////

// 주간 리포트 불러오기 //////////////////////////////////////////////////////////////////////
async function requestweeklyReport(userNo) {
  const { data } = await axios.get("http://localhost:8080/api/meal/report/weekly", {
    params: { userNo },
  });
  weeklyReport.value = data;
  drawWeeklyChart(weeklyReport.value.dailySumList);
  drawDoughnutCharts(breakfastChart.value, weeklyReport.value.avgBreakfastEnergy, " Kcal", calorieGoal * 0.25, "#a8e063");
  drawDoughnutCharts(lunchChart.value, weeklyReport.value.avgLunchEnergy, " kcal", calorieGoal * 0.35, "#ffa751");
  drawDoughnutCharts(dinnerChart.value, weeklyReport.value.avgDinnerEnergy, "Kcal", calorieGoal * 0.3, "#f9d423");

  drawBarCharts(weeklyEnergyChart.value, "칼로리", weeklyReport.value.avgWeeklyEnergy, calorieGoal, "#EF5E5E");
  drawBarCharts(weeklyCarbChart.value, "탄수화물", weeklyReport.value.avgWeeklycarbohydrate, carbGoal, "#a8e063");
  drawBarCharts(weeklyProteinChart.value, "단백질", weeklyReport.value.avgWeeklyProtein, proteinGoal, "#ffa751");
  drawBarCharts(weeklyFatChart.value, "지방", weeklyReport.value.avgWeeklyTotalFattyAcids, fatGoal, "#f9d423");
  drawBarCharts(weeklyWaterChart.value, "수분", weeklyReport.value.avgWeeklyWater, waterGoal, "#9cd9ff");
}

//////////////////////////////////////////////////////////////////////

// 월간 리포트 불러오기 //////////////////////////////////////////////////////////////////////
async function requestmonthlyReport(userNo) {
  const { data } = await axios.get("http://localhost:8080/api/meal/report/monthly", {
    params: { userNo },
  });
  monthlyReport.value = data;
  drawMonthlyChart(monthlyReport.value.weekdayReportList);
  drawDoughnutCharts(breakfastCntChart.value, monthlyReport.value.breakfastCount, " 회", 30, "#a8e063");
  drawDoughnutCharts(lunchCntChart.value, monthlyReport.value.lunchCount, " 회", 30, "#ffa751");
  drawDoughnutCharts(dinnerCntChart.value, monthlyReport.value.dinnerCount, " 회", 30, "#f9d423");

  drawBarCharts(monthlyCarbChart.value, "탄수화물", monthlyReport.value.avgMonthlycarbohydrate, carbGoal, "#a8e063");
  drawBarCharts(monthlyProteinChart.value, "단백질", monthlyReport.value.avgMonthlyProtein, proteinGoal, "#a8e063");
  drawBarCharts(monthlyFatChart.value, "지방", monthlyReport.value.avgMonthlyTotalFattyAcids, fatGoal, "#ffa751");
  drawBarCharts(monthlySodiumChart.value, "나트륨", monthlyReport.value.avgMonthlySodium, sodiumGoal, "#9CBEFF");
  drawBarCharts(monthlySugarChart.value, "당", monthlyReport.value.avgMonthlySugar, sugarGoal, "#FFC0F1");
}

//////////////////////////////////////////////////////////////////////

// chart.js //////////////////////////////////////////////////////////////////////
import { Chart, DoughnutController, BarController, ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend } from "chart.js";

Chart.register(DoughnutController, BarController, ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const calorieChart = ref(null);
const waterChart = ref(null);
const dailyCarbChart = ref(null);
const dailyProteinChart = ref(null);
const dailyFatChart = ref(null);
const weeklyChartCanvas = ref(null);
const monthlyChartCanvas = ref(null);
const breakfastChart = ref(null);
const lunchChart = ref(null);
const dinnerChart = ref(null);
const breakfastCntChart = ref(null);
const lunchCntChart = ref(null);
const dinnerCntChart = ref(null);

const weeklyEnergyChart = ref(null);
const weeklyCarbChart = ref(null);
const weeklyProteinChart = ref(null);
const weeklyFatChart = ref(null);
const weeklyWaterChart = ref(null);

const monthlyCarbChart = ref(null);
const monthlyProteinChart = ref(null);
const monthlyFatChart = ref(null);
const monthlySodiumChart = ref(null);
const monthlySugarChart = ref(null);

// 영양소 기준 데이터
console.log(userNutriStandard.value);
const calorieGoal = computed(() => userNutriStandard.value.energy || 0);
const waterGoal = computed(() => userNutriStandard.value.water || 0);
const carbGoal = computed(() => userNutriStandard.value.carbohydrate || 0);
const proteinGoal = computed(() => userNutriStandard.value.protein || 0);
const fatGoal = computed(() => userNutriStandard.value.totalFattyAcids || 0);
const sodiumGoal = computed(() => userNutriStandard.value.sodium || 0);
const sugarGoal = computed(() => userNutriStandard.value.sugar || 0);

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
      labels: ["섭취"],
    };
  } else {
    return {
      datasets: [
        {
          data: [value, goal - value],
          backgroundColor: [color, "#e0e0e0"],
        },
      ],
      labels: ["섭취", "남은"],
    };
  }
};

// 일간 : 바
const createBarData = (label, value, goal, color) => {
  if (value >= goal) {
    return {
      labels: [""],
      datasets: [
        {
          label: "섭취량",
          data: [value],
          backgroundColor: color,
          borderRadius: 2,
          barThickness: 8,
        },
      ],
    };
  } else {
    return {
      labels: [""],
      datasets: [
        {
          label: "섭취량",
          data: [value],
          backgroundColor: color,
          borderRadius: 2,
          barThickness: 8,
        },
        {
          label: "남은양",
          data: [goal - value],
          backgroundColor: "#e0e0e0",
          borderRadius: 2,
          barThickness: 8,
        },
      ],
    };
  }
};

// 주간 : 그룹 바
function drawWeeklyChart(dailySumList) {
  const ctx = weeklyChartCanvas.value;
  if (!ctx) return;

  ctx.width = ctx.offsetWidth;
  ctx.height = 150;

  new Chart(ctx, {
    type: "bar",
    data: createGroupBarData(dailySumList, "weekly"),
    options: groupChartoptions,
  });
}

// 월간 : 그룹 바
function drawMonthlyChart(dailySumList) {
  const ctx = monthlyChartCanvas.value;
  if (!ctx) return;

  ctx.width = ctx.offsetWidth;
  ctx.height = 150;

  new Chart(ctx, {
    type: "bar",
    data: createGroupBarData(dailySumList, "monthly"),
    options: groupChartoptions,
  });
}

// 그룹화된 바 옵션
const groupChartoptions = {
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    x: {
      stacked: true,
      grid: { display: false },
      ticks: {
        color: "#444",
        font: { size: 11 },
        callback: function (val, index) {
          return this.getLabelForValue(val).replaceAll("-", ".");
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

// 그룹화된 바 그래프
function createGroupBarData(valueList, type) {
  let labels = "";
  if (type === "weekly") {
    labels = valueList.map((item) => item.reportDate);
  } else if (type === "monthly") {
    const weekdayMap = {
      Monday: "월",
      Tuesday: "화",
      Wednesday: "수",
      Thursday: "목",
      Friday: "금",
      Saturday: "토",
      Sunday: "일",
    };

    labels = valueList.map((item) => weekdayMap[item.reportWeekday] || item.reportWeekday);
  }

  const maxGoals = {
    carbohydrate: 400,
    protein: 60,
    fat: 50,
    water: 2000,
  };

  const carbData = valueList.map((item) => Math.min(100, (item.carbohydrate / maxGoals.carbohydrate) * 100));
  const proteinData = valueList.map((item) => Math.min(100, (item.protein / maxGoals.protein) * 100));
  const fatData = valueList.map((item) => Math.min(100, (item.totalFattyAcids / maxGoals.fat) * 100));
  const waterData = valueList.map((item) => Math.min(100, (item.water / maxGoals.water) * 100));

  const gray = "#eeeeee";

  return {
    labels,
    datasets: [
      {
        label: "탄수화물",
        data: carbData,
        stack: "carb",
        backgroundColor: "#a8e063",
        borderRadius: 2,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      {
        label: "단백질",
        data: proteinData,
        stack: "protein",
        backgroundColor: "#ffa751",
        borderRadius: 2,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      {
        label: "지방",
        data: fatData,
        stack: "fat",
        backgroundColor: "#f9d423",
        borderRadius: 2,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      {
        label: "수분",
        data: waterData,
        stack: "water",
        backgroundColor: "#69b7f0",
        borderRadius: 2,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      // 남은 양 (회색)
      {
        label: "남은 탄수화물",
        data: carbData.map((v) => 100 - v),
        stack: "carb",
        backgroundColor: gray,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      {
        label: "남은 단백질",
        data: proteinData.map((v) => 100 - v),
        stack: "protein",
        backgroundColor: gray,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      {
        label: "남은 지방",
        data: fatData.map((v) => 100 - v),
        stack: "fat",
        backgroundColor: gray,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
      {
        label: "남은 수분",
        data: waterData.map((v) => 100 - v),
        stack: "water",
        backgroundColor: gray,
        categoryPercentage: 0.6,
        barPercentage: 0.3,
      },
    ],
  };
}

// 도넛 그래프 중앙에 글씨
const centerTextPlugin = (val) => ({
  id: "centerText",
  afterDraw(chart) {
    const { width, height, ctx } = chart;
    ctx.save();
    const text = `${val}`;
    ctx.font = "bold 14px Arial";
    ctx.textBaseline = "middle";
    ctx.textAlign = "center";
    ctx.fillStyle = "#1c1c1c";
    ctx.fillText(text, width / 2, height / 2);
    ctx.restore();
  },
});

// 도넛 그래프 그리기
function drawDoughnutCharts(chart, value, unit, valueGoal, color) {
  const canvas = chart;
  canvas.height = 150;
  new Chart(canvas, {
    type: "doughnut",
    data: createDoughnutData(value, valueGoal, color),
    options: {
      plugins: {
        tooltip: { enabled: false },
        legend: { display: false },
      },
      cutout: "70%",
    },
    plugins: [centerTextPlugin(value + unit)],
  });
}

// 바 그래프 그리기(차트, "영양소", 값, 목표값, 색상)
function drawBarCharts(chart, label, value, valueGoal, color) {
  const canvas = chart;
  canvas.height = 8;

  new Chart(canvas, {
    type: "bar",
    data: createBarData(label, value, valueGoal, color),
    options: {
      responsive: false,
      maintainAspectRatio: false,
      indexAxis: "y",
      scales: {
        x: {
          stacked: true,
          max: valueGoal,
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
}
/////////////////////////

//////////////////////////////////////////////////////////////////////

// 실행 (테스트용으로 userNo=1, date는 하드코딩)
onMounted(() => {
  requestNutriStandard(1);
  requestMealList(1, "2025-05-21");
  requestDayReport(1, "2025-05-21");
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
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/MealList.css";
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
.weekly-canvas-div {
  width: 100%;
  height: 150px;
}
.weekly-canvas {
  width: 100%;
  height: 100%;
}
.monthly-canvas-div {
  width: 100%;
  height: 150px;
}
.monthly-canvas {
  width: 100% !important;
  height: 100% !important;
}
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
            <img class="icon-nav-calendar-dots" src="@/assets/images/mealList/icon-nav-calendar-dots0.svg" />
            <div class="text">
              <div class="label">식단 기록</div>
            </div>
          </div>
          <div class="button-nav2">
            <img class="icon-nav-bowl-food" src="@/assets/images/mealList/icon-nav-bowl-food0.svg" />
            <div class="text2">
              <div class="label2">즐겨찾기</div>
            </div>
            <div class="icon">
              <img class="icon-caret-down" src="@/assets/images/mealList/icon-caret-down0.svg" />
            </div>
          </div>
          <div class="button-nav2">
            <img class="icon-nav-notebook" src="@/assets/images/mealList/icon-nav-notebook0.svg" />
            <div class="text2">
              <div class="label2">커뮤니티</div>
            </div>
          </div>
          <div class="button-nav2">
            <img class="icon-nav-chart-line-up" src="@/assets/images/mealList/icon-nav-chart-line-up0.svg" />
            <div class="text2">
              <div class="label2">챌린지</div>
            </div>
          </div>
          <div class="button-nav2">
            <img class="icon-nav-heartbeat" src="@/assets/images/mealList/icon-nav-heartbeat0.svg" />
            <div class="text2">
              <div class="label2">마이페이지</div>
            </div>
          </div>
          <div class="button-nav3">
            <img class="icon-nav-sign-out" src="@/assets/images/mealList/icon-nav-sign-out0.svg" />
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
                <img class="chevron-left" src="@/assets/images/mealList/chevron-left0.svg" />
                <div class="text4">
                  <img class="vector" src="@/assets/images/mealList/vector0.svg" />
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
                <img class="chevron-right" src="@/assets/images/mealList/chevron-right0.svg" />
              </div>
              <div class="frame-17">
                <div class="button2">
                  <div class="icon-left">
                    <img class="icon-special-drop" src="@/assets/images/mealList/icon-special-drop0.svg" />
                  </div>
                  <div class="text5">
                    <div class="label">물 기록하기</div>
                  </div>
                </div>
                <div class="button3">
                  <div class="icon-left">
                    <img class="icon-nav-fork-knife" src="@/assets/images/mealList/icon-nav-fork-knife0.svg" />
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
                          <img class="icon-special-drop2" src="@/assets/images/mealList/icon-special-drop1.svg" />
                          <div class="value">
                            <div class="amount">{{ dayReport.water }}ml</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <!-- 식단 리스트 -->
                  <div v-for="(meal, index) in meals" :key="meal.mealNo" class="card-list-all-menu2">
                    <div class="image">
                      <img class="place-image-here" :src="meal.fileList[0] ? meal.fileList[0].attachmentName : defaultImage" />
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
                          <img class="icon-special-fire" src="@/assets/images/mealList/icon-special-fire0.svg" />
                          <div class="value2">
                            <div class="amount">{{ meal.energy }} kcal</div>
                          </div>
                        </div>

                        <img class="separator" src="@/assets/images/mealList/separator0.svg" />

                        <div class="info-carbs">
                          <img class="icon-special-bread" src="@/assets/images/mealList/icon-special-bread0.svg" />
                          <div class="value">
                            <div class="amount">탄수화물</div>
                            <div class="unit">{{ meal.carbohydrate }}g</div>
                          </div>
                        </div>

                        <img class="separator2" src="@/assets/images/mealList/separator1.svg" />

                        <div class="info-protein">
                          <img class="icon-special-fish" src="@/assets/images/mealList/icon-special-fish0.svg" />
                          <div class="value">
                            <div class="amount">단백질</div>
                            <div class="unit">{{ meal.protein }}g</div>
                          </div>
                        </div>

                        <img class="separator3" src="@/assets/images/mealList/separator2.svg" />

                        <div class="info-fats">
                          <img class="icon-special-drop3" src="@/assets/images/mealList/icon-special-drop2.svg" />
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
                      <canvas class="chart-doughnut" ref="calorieChart"></canvas>
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
                            <div class="title6">{{ dayReport.carbohydrate }} g</div>
                          </div>
                          <div class="menu-nav3">
                            <canvas class="chart-bar" ref="dailyCarbChart"></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">당류</div>
                            <div class="title7">{{ dayReport.sugar }} g</div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">식이섬유</div>
                            <div class="title7">{{ dayReport.dietaryFiber }} g</div>
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
                            <canvas class="chart-bar" ref="dailyProteinChart"></canvas>
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
                            <div class="title6">{{ dayReport.totalFattyAcids }} g</div>
                          </div>
                          <div class="menu-nav3">
                            <canvas class="chart-bar" ref="dailyFatChart"></canvas>
                          </div>
                        </div>
                        <div class="frame-134">
                          <div class="frame-135">
                            <div class="title7">포화지방</div>
                            <div class="title7">{{ dayReport.saturatedFats }} g</div>
                          </div>
                          <div class="frame-143">
                            <div class="title7">불포화지방</div>
                            <div class="title7">{{ dayReport.unsaturatedFats }} g</div>
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
                            <div class="title7">{{ dayReport.vitaminACarotene }} µg</div>
                          </div>
                          <div class="frame-18">
                            <div class="title7">비타민 B1 티아민</div>
                            <div class="title7">{{ dayReport.vitaminB1 }} mg</div>
                          </div>
                          <div class="frame-192">
                            <div class="title7">비타민 B2 리보플라빈</div>
                            <div class="title7">{{ dayReport.vitaminB2 }} mg</div>
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
                            <div class="title7">{{ dayReport.vitaminC }} mg</div>
                          </div>
                          <div class="frame-164">
                            <div class="title7">비타민 D</div>
                            <div class="title7">{{ dayReport.vitaminD }} µg</div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-144">
                        <div class="frame-174">
                          <div class="title8">콜레스테롤</div>
                          <div class="frame-137">
                            <div class="frame-135">
                              <div class="title7"></div>
                              <div class="title7">{{ dayReport.cholesterol }} mg</div>
                            </div>
                          </div>
                        </div>
                        <div class="frame-182">
                          <div class="title8">무기질</div>
                          <div class="frame-137">
                            <div class="frame-135">
                              <div class="title7">나트륨</div>
                              <div class="title7">{{ dayReport.sodium }} mg</div>
                            </div>
                            <div class="frame-143">
                              <div class="title7">칼슘</div>
                              <div class="title7">{{ dayReport.calcium }} mg</div>
                            </div>
                            <div class="frame-152">
                              <div class="title7">철</div>
                              <div class="title7">{{ dayReport.iron }} mg</div>
                            </div>
                            <div class="frame-164">
                              <div class="title7">인</div>
                              <div class="title7">{{ dayReport.phosphorus }} mg</div>
                            </div>
                            <div class="frame-175">
                              <div class="title7">칼륨</div>
                              <div class="title7">{{ dayReport.potassium }} mg</div>
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

                  <!-- 최근 7일 주요 영양소 기록 -->
                  <div class="weekly-canvas-div">
                    <canvas class="weekly-canvas" ref="weeklyChartCanvas"></canvas>
                  </div>
                </div>
                <div class="frame-167">
                  <!-- 끼니별 칼로리 분석 -->
                  <div class="frame-168">
                    <div class="title2">끼니별 칼로리 분석</div>
                    <div class="frame-169">
                      <canvas class="chart-doughnut" ref="breakfastChart"></canvas>
                      <canvas class="chart-doughnut" ref="lunchChart"></canvas>
                      <canvas class="chart-doughnut" ref="dinnerChart"></canvas>
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
                          <canvas class="chart-bar" ref="weeklyEnergyChart"></canvas>
                          <div class="frame-1612">
                            <div class="title12">{{ weeklyReport.avgWeeklyEnergy }} g</div>
                            <div class="title13">/</div>
                            <div class="title13">{{ calorieGoal }} g</div>
                          </div>
                        </div>
                      </div>
                      <div class="group-16">
                        <div class="frame-146">
                          <div class="title5">탄수화물</div>
                          <canvas class="chart-bar" ref="weeklyCarbChart"></canvas>
                          <div class="frame-1612">
                            <div class="title12">{{ weeklyReport.avgWeeklycarbohydrate }} g</div>
                            <div class="title13">/</div>
                            <div class="title13">{{ carbGoal }} g</div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-147">
                        <div class="title5">단백질</div>
                        <canvas class="chart-bar" ref="weeklyProteinChart"></canvas>
                        <div class="frame-1612">
                          <div class="title12">{{ weeklyReport.avgWeeklyProtein }} g</div>
                          <div class="title13">/</div>
                          <div class="title13">{{ proteinGoal }} g</div>
                        </div>
                      </div>
                      <div class="frame-154">
                        <div class="title5">지방</div>
                        <canvas class="chart-bar" ref="weeklyFatChart"></canvas>
                        <div class="frame-1612">
                          <div class="title12">{{ weeklyReport.avgWeeklyTotalFattyAcids }} g</div>
                          <div class="title13">/</div>
                          <div class="title13">{{ fatGoal }} g</div>
                        </div>
                      </div>
                      <div class="frame-1613">
                        <div class="title5">수분</div>
                        <canvas class="chart-bar" ref="weeklyWaterChart"></canvas>
                        <div class="frame-1612">
                          <div class="title12">{{ weeklyReport.avgWeeklyWater }} g</div>
                          <div class="title13">/</div>
                          <div class="title13">{{ waterGoal }} g</div>
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
                        <div class="title15">{{ monthlyReport.monthlyScore }}</div>
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

                    <!-- 최근 7일 주요 영양소 기록 -->
                    <div class="monthly-canvas-div">
                      <canvas class="monthly-canvas" ref="monthlyChartCanvas"></canvas>
                    </div>
                  </div>
                </div>
                <div class="frame-167">
                  <div class="frame-168">
                    <div class="title2">끼니별 식사 횟수</div>
                    <div class="frame-169">
                      <canvas class="chart-doughnut" ref="breakfastCntChart"></canvas>
                      <canvas class="chart-doughnut" ref="lunchCntChart"></canvas>
                      <canvas class="chart-doughnut" ref="dinnerCntChart"></canvas>
                    </div>
                  </div>
                  <div class="frame-1610"></div>
                  <div class="frame-1710">
                    <div class="title2">월 평균 영양성분 섭취량 평가</div>
                    <div class="frame-1611">
                      <div class="group-16">
                        <div class="frame-145">
                          <div class="title5">탄수화물</div>
                          <canvas class="chart-bar" ref="monthlyCarbChart"></canvas>
                          <div class="title20">미달</div>
                          <div class="frame-1614">
                            <div class="title21">{{ monthlyReport.avgMonthlycarbohydrate }} g</div>
                            <div class="title22">/</div>
                            <div class="title22">{{ carbGoal }} g</div>
                          </div>
                        </div>
                      </div>
                      <div class="frame-147">
                        <div class="title5">단백질</div>
                        <canvas class="chart-bar" ref="monthlyProteinChart"></canvas>
                        <div class="title23">적정</div>
                        <div class="frame-1614">
                          <div class="title21">{{ monthlyReport.avgMonthlyProtein }} g</div>
                          <div class="title22">/</div>
                          <div class="title22">{{ proteinGoal }} g</div>
                        </div>
                      </div>
                      <div class="frame-154">
                        <div class="title5">지방</div>
                        <canvas class="chart-bar" ref="monthlyFatChart"></canvas>
                        <div class="title23">적정</div>
                        <div class="frame-1614">
                          <div class="title21">{{ monthlyReport.avgMonthlyTotalFattyAcids }} g</div>
                          <div class="title22">/</div>
                          <div class="title22">{{ fatGoal }} g</div>
                        </div>
                      </div>
                      <div class="frame-1613">
                        <div class="title5">나트륨</div>
                        <canvas class="chart-bar" ref="monthlySodiumChart"></canvas>
                        <div class="title24">초과</div>
                        <div class="frame-1614">
                          <div class="title21">{{ monthlyReport.avgMonthlySodium }} g</div>
                          <div class="title22">/</div>
                          <div class="title22">{{ sodiumGoal }} g</div>
                        </div>
                      </div>
                      <div class="frame-148">
                        <div class="title5">당</div>
                        <canvas class="chart-bar" ref="monthlySugarChart"></canvas>
                        <div class="title23">적정</div>
                        <div class="frame-1614">
                          <div class="title21">{{ monthlyReport.avgMonthlySugar }} g</div>
                          <div class="title22">/</div>
                          <div class="title22">{{ sugarGoal }} g</div>
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
            <div class="copyright-2024-peterdraw">Copyright © 2024 Peterdraw</div>
            <div class="links">
              <div class="privacy-policy">Privacy Policy</div>
              <div class="term-and-conditions">Term and conditions</div>
              <div class="contact">Contact</div>
            </div>
          </div>
          <div class="social-media">
            <img class="facebook-logo" src="@/assets/images/mealList/facebook-logo0.svg" />
            <img class="twitter-logo" src="@/assets/images/mealList/twitter-logo0.svg" />
            <img class="instagram-logo" src="@/assets/images/mealList/instagram-logo0.svg" />
            <img class="youtube-logo" src="@/assets/images/mealList/youtube-logo0.svg" />
            <img class="linkedin-logo" src="@/assets/images/mealList/linkedin-logo0.svg" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
