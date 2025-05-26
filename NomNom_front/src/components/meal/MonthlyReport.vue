<script setup>
import { ref, onMounted, computed, nextTick } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

// 날짜 선택
const selectedDate = ref('');
// selectedDate.value = new Date().toISOString().split('T')[0];

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
  console.log('MonthlyReport-fetchAllReports' + selectedDate);
  await nextTick();
  await nutritionStore.fetchNutriStandard(userNo);
  await reportStore.fetchMeals(userNo, selectedDate.value);
  await reportStore.fetchDayReport(userNo, selectedDate.value);
  await reportStore.fetchMonthlyReport(userNo);

  requestMonthlyReport();
}

// onMounted //////////////////////////////////////////////////////////////////////////////
onMounted(async () => {
  await nextTick();
  fetchAllReports();
});

const monthlyChartCanvas = ref(null);
const breakfastCntChart = ref(null);
const lunchCntChart = ref(null);
const dinnerCntChart = ref(null);
const monthlyCarbChart = ref(null);
const monthlyProteinChart = ref(null);
const monthlyFatChart = ref(null);
const monthlySodiumChart = ref(null);
const monthlySugarChart = ref(null);

async function requestMonthlyReport() {
  chartRender.drawMonthlyChart(
    monthlyChartCanvas.value,
    reportStore.monthlyReport.weekdayReportList
  );

  chartRender.drawDoughnutCharts(
    breakfastCntChart.value,
    reportStore.monthlyReport.breakfastCount,
    ' 회',
    30,
    '#a8e063'
  );

  chartRender.drawDoughnutCharts(
    lunchCntChart.value,
    reportStore.monthlyReport.lunchCount,
    ' 회',
    30,
    '#ffa751'
  );

  chartRender.drawDoughnutCharts(
    dinnerCntChart.value,
    reportStore.monthlyReport.dinnerCount,
    ' 회',
    30,
    '#f9d423'
  );

  chartRender.drawBarCharts(
    monthlyCarbChart.value,
    '탄수화물',
    reportStore.monthlyReport.avgMonthlycarbohydrate,
    nutritionStore.carbGoal,
    '#a8e063'
  );

  chartRender.drawBarCharts(
    monthlyProteinChart.value,
    '단백질',
    reportStore.monthlyReport.avgMonthlyProtein,
    nutritionStore.proteinGoal,
    '#ffa751'
  );

  chartRender.drawBarCharts(
    monthlyFatChart.value,
    '지방',
    reportStore.monthlyReport.avgMonthlyTotalFattyAcids,
    nutritionStore.fatGoal,
    '#f9d423'
  );

  chartRender.drawBarCharts(
    monthlySodiumChart.value,
    '나트륨',
    reportStore.monthlyReport.avgMonthlySodium,
    nutritionStore.sodiumGoal,
    '#9CBEFF'
  );

  chartRender.drawBarCharts(
    monthlySugarChart.value,
    '당',
    reportStore.monthlyReport.avgMonthlySugar,
    nutritionStore.sugarGoal,
    '#FFC0F1'
  );
}

// 월 평균 영양성분 섭취량 평가 '미달', '적정', '초과' 판정
function getNutritionStatus(value, standard, type) {
  const [low, high] = getRange(standard, type);

  if (type === 'sugar') {
    return value <= standard ? '적정' : '초과';
  }

  if (value < low) return '미달';
  if (value > high) return '초과';
  return '적정';
}

// 월 평균 영양성분 섭취량 평가 '미달', '적정', '초과' 색상 변환
function getNutritionClass(value, standard, type) {
  const [low, high] = getRange(standard, type);

  if (type === 'sugar') {
    return value <= standard ? 'green-text' : 'red-text';
  }

  if (value < low) return 'yellow-text';
  if (value > high) return 'red-text';
  return 'green-text';
}

// 월 평균 영양성분 섭취량 평가 '미달', '적정', '초과' 기준 반환
function getRange(standard, type) {
  switch (type) {
    case 'carbohydrate':
    case 'protein':
    case 'fat':
      return [standard * 0.85, standard * 1.15];
    case 'sodium':
      return [standard * 0.8, standard * 1.2];
    case 'sugar': // sugar는 상한만 비교
      return [0, standard];
    default:
      return [0, 0];
  }
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
  <div class="monthly-summary">
    <div class="calender-frame2">
      <div class="frame-203">
        <div class="title2">월간 식단 점수</div>
        <div class="title14">
          {{ reportStore.monthlyReport.reportStartDate }} ~
          {{ reportStore.monthlyReport.reportEndDate }}
        </div>
        <div class="frame-204">
          <div class="frame-205">
            <div class="title15">
              {{ reportStore.monthlyReport.monthlyScore }}
            </div>
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
          <div class="group-18">
            <canvas class="chart-doughnut" ref="breakfastCntChart"></canvas>
            <div class="chart-doughnut-name">아침</div>
          </div>
          <div class="group-18">
            <canvas class="chart-doughnut" ref="lunchCntChart"></canvas>
            <div class="chart-doughnut-name">점심</div>
          </div>
          <div class="group-18">
            <canvas class="chart-doughnut" ref="dinnerCntChart"></canvas>
            <div class="chart-doughnut-name">저녁</div>
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
              <canvas class="chart-bar" ref="monthlyCarbChart"></canvas>
              <div
                :class="[
                  'title20',
                  getNutritionClass(
                    reportStore.monthlyReport.avgMonthlycarbohydrate,
                    nutritionStore.carbGoal,
                    'carbohydrate'
                  ),
                ]"
              >
                {{
                  getNutritionStatus(
                    reportStore.monthlyReport.avgMonthlycarbohydrate,
                    nutritionStore.carbGoal,
                    'carbohydrate'
                  )
                }}
              </div>
              <div class="frame-1614">
                <div class="title21">
                  {{ reportStore.monthlyReport.avgMonthlycarbohydrate }}
                  g
                </div>
                <div class="title22">/</div>
                <div class="title22">
                  {{ nutritionStore.carbGoal }} g (±15%)
                </div>
              </div>
            </div>
          </div>
          <div class="frame-147">
            <div class="title5">단백질</div>
            <canvas class="chart-bar" ref="monthlyProteinChart"></canvas>
            <div
              :class="[
                'title20',
                getNutritionClass(
                  reportStore.monthlyReport.avgMonthlyProtein,
                  nutritionStore.proteinGoal,
                  'protein'
                ),
              ]"
            >
              {{
                getNutritionStatus(
                  reportStore.monthlyReport.avgMonthlyProtein,
                  nutritionStore.proteinGoal,
                  'protein'
                )
              }}
            </div>
            <div class="frame-1614">
              <div class="title21">
                {{ reportStore.monthlyReport.avgMonthlyProtein }} g
              </div>
              <div class="title22">/</div>
              <div class="title22">
                {{ nutritionStore.proteinGoal }} g (±15%)
              </div>
            </div>
          </div>
          <div class="frame-154">
            <div class="title5">지방</div>
            <canvas class="chart-bar" ref="monthlyFatChart"></canvas>
            <div
              :class="[
                'title20',
                getNutritionClass(
                  reportStore.monthlyReport.avgMonthlyTotalFattyAcids,
                  nutritionStore.fatGoal,
                  'fat'
                ),
              ]"
            >
              {{
                getNutritionStatus(
                  reportStore.monthlyReport.avgMonthlyTotalFattyAcids,
                  nutritionStore.fatGoal,
                  'fat'
                )
              }}
            </div>
            <div class="frame-1614">
              <div class="title21">
                {{ reportStore.monthlyReport.avgMonthlyTotalFattyAcids }}
                g
              </div>
              <div class="title22">/</div>
              <div class="title22">{{ nutritionStore.fatGoal }} g (±15%)</div>
            </div>
          </div>
          <div class="frame-1613">
            <div class="title5">나트륨</div>
            <canvas class="chart-bar" ref="monthlySodiumChart"></canvas>
            <div
              :class="[
                'title20',
                getNutritionClass(
                  reportStore.monthlyReport.avgMonthlySodium,
                  nutritionStore.sodiumGoal,
                  'sodium'
                ),
              ]"
            >
              {{
                getNutritionStatus(
                  reportStore.monthlyReport.avgMonthlySodium,
                  nutritionStore.sodiumGoal,
                  'sodium'
                )
              }}
            </div>
            <div class="frame-1614">
              <div class="title21">
                {{ reportStore.monthlyReport.avgMonthlySodium }} mg
              </div>
              <div class="title22">/</div>
              <div class="title22">
                {{ nutritionStore.sodiumGoal }} mg (±20%)
              </div>
            </div>
          </div>
          <div class="frame-148">
            <div class="title5">당</div>
            <canvas class="chart-bar" ref="monthlySugarChart"></canvas>
            <div
              :class="[
                'title20',
                getNutritionClass(
                  reportStore.monthlyReport.avgMonthlySugar,
                  nutritionStore.sugarGoal,
                  'sugar'
                ),
              ]"
            >
              {{
                getNutritionStatus(
                  reportStore.monthlyReport.avgMonthlySugar,
                  nutritionStore.sugarGoal,
                  'sugar'
                )
              }}
            </div>
            <div class="frame-1614">
              <div class="title21">
                {{ reportStore.monthlyReport.avgMonthlySugar }} g
              </div>
              <div class="title22">/</div>
              <div class="title22">{{ nutritionStore.sugarGoal }} g (미만)</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
