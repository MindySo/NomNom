<script setup>
import { ref, onMounted, computed, nextTick } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

// 날짜 선택
const selectedDate = ref('');

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

// Pinia : 사용자 맞춤 영양소 권장량 store ///////////////////////////////////////////////////
import { userNutritionStore } from '@/stores/meal/nutritionStore';
const nutritionStore = userNutritionStore();

// Pinia : 식단 정보 및 일간, 주간, 월간 리포트 정보 store ////////////////////////////////////
import { useReportStore } from '@/stores/meal/mealReportStore';
const reportStore = useReportStore();

// Pinia : 차트 함수 store //////////////////////////////////////////////////////////////////
import { useChartRender } from '@/stores/meal/chartRenderStore';
const chartRender = useChartRender();

const userNo = 1;

// 값 순서대로 가져오기 //////////////////////////////////////////////////////////////////////
async function fetchAllReports() {
  selectedDate.value = new Date().toISOString().split('T')[0];
  console.log('WeeklyReport-fetchAllReports' + selectedDate);
  await nextTick();
  await nutritionStore.fetchNutriStandard(userNo);
  await reportStore.fetchMeals(userNo, selectedDate.value);
  await reportStore.fetchDayReport(userNo, selectedDate.value);
  await reportStore.fetchWeeklyReport(userNo);

  requestWeeklyReport();
}

// onMounted //////////////////////////////////////////////////////////////////////////////
onMounted(async () => {
  await nextTick();
  fetchAllReports();
});

const weeklyChartCanvas = ref(null);
const breakfastChart = ref(null);
const lunchChart = ref(null);
const dinnerChart = ref(null);
const weeklyEnergyChart = ref(null);
const weeklyCarbChart = ref(null);
const weeklyProteinChart = ref(null);
const weeklyFatChart = ref(null);
const weeklyWaterChart = ref(null);

async function requestWeeklyReport() {
  chartRender.drawWeeklyChart(
    weeklyChartCanvas,
    reportStore.weeklyReport.dailySumList
  );

  chartRender.drawDoughnutCharts(
    breakfastChart.value,
    reportStore.weeklyReport.avgBreakfastEnergy,
    ' Kcal',
    nutritionStore.calorieGoal * 0.25,
    '#a8e063'
  );

  chartRender.drawDoughnutCharts(
    lunchChart.value,
    reportStore.weeklyReport.avgLunchEnergy,
    ' kcal',
    nutritionStore.calorieGoal * 0.35,
    '#ffa751'
  );

  chartRender.drawDoughnutCharts(
    dinnerChart.value,
    reportStore.weeklyReport.avgDinnerEnergy,
    'Kcal',
    nutritionStore.calorieGoal * 0.3,
    '#f9d423'
  );

  chartRender.drawBarCharts(
    weeklyEnergyChart.value,
    '칼로리',
    reportStore.weeklyReport.avgWeeklyEnergy,
    nutritionStore.calorieGoal,
    '#EF5E5E'
  );

  chartRender.drawBarCharts(
    weeklyCarbChart.value,
    '탄수화물',
    reportStore.weeklyReport.avgWeeklycarbohydrate,
    nutritionStore.carbGoal,
    '#a8e063'
  );

  chartRender.drawBarCharts(
    weeklyProteinChart.value,
    '단백질',
    reportStore.weeklyReport.avgWeeklyProtein,
    nutritionStore.proteinGoal,
    '#ffa751'
  );

  chartRender.drawBarCharts(
    weeklyFatChart.value,
    '지방',
    reportStore.weeklyReport.avgWeeklyTotalFattyAcids,
    nutritionStore.fatGoal,
    '#f9d423'
  );

  chartRender.drawBarCharts(
    weeklyWaterChart.value,
    '수분',
    reportStore.weeklyReport.avgWeeklyWater,
    nutritionStore.waterGoal,
    '#9cd9ff'
  );
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
  <div class="weekly-summary">
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
          <div class="group-18">
            <canvas class="chart-doughnut" ref="breakfastChart"></canvas>
            <div class="chart-doughnut-name">아침</div>
          </div>
          <div class="group-18">
            <canvas class="chart-doughnut" ref="lunchChart"></canvas>
            <div class="chart-doughnut-name">점심</div>
          </div>
          <div class="group-18">
            <canvas class="chart-doughnut" ref="dinnerChart"></canvas>
            <div class="chart-doughnut-name">저녁</div>
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
              <canvas class="chart-bar" ref="weeklyEnergyChart"></canvas>
              <div class="frame-1612">
                <div class="title12">
                  {{ reportStore.weeklyReport.avgWeeklyEnergy }} g
                </div>
                <div class="title13">/</div>
                <div class="title13">{{ nutritionStore.calorieGoal }} g</div>
              </div>
            </div>
          </div>
          <div class="group-16">
            <div class="frame-146">
              <div class="title5">탄수화물</div>
              <canvas class="chart-bar" ref="weeklyCarbChart"></canvas>
              <div class="frame-1612">
                <div class="title12">
                  {{ reportStore.weeklyReport.avgWeeklycarbohydrate }}
                  g
                </div>
                <div class="title13">/</div>
                <div class="title13">{{ nutritionStore.carbGoal }} g</div>
              </div>
            </div>
          </div>
          <div class="frame-147">
            <div class="title5">단백질</div>
            <canvas class="chart-bar" ref="weeklyProteinChart"></canvas>
            <div class="frame-1612">
              <div class="title12">
                {{ reportStore.weeklyReport.avgWeeklyProtein }} g
              </div>
              <div class="title13">/</div>
              <div class="title13">{{ nutritionStore.proteinGoal }} g</div>
            </div>
          </div>
          <div class="frame-154">
            <div class="title5">지방</div>
            <canvas class="chart-bar" ref="weeklyFatChart"></canvas>
            <div class="frame-1612">
              <div class="title12">
                {{ reportStore.weeklyReport.avgWeeklyTotalFattyAcids }}
                g
              </div>
              <div class="title13">/</div>
              <div class="title13">{{ nutritionStore.fatGoal }} g</div>
            </div>
          </div>
          <div class="frame-1613">
            <div class="title5">수분</div>
            <canvas class="chart-bar" ref="weeklyWaterChart"></canvas>
            <div class="frame-1612">
              <div class="title12">
                {{ reportStore.weeklyReport.avgWeeklyWater }} g
              </div>
              <div class="title13">/</div>
              <div class="title13">{{ nutritionStore.waterGoal }} g</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
