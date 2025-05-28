<script setup>
import { ref, onMounted, onBeforeUnmount, watch, nextTick } from "vue";
import axios from "axios";
const props = defineProps({
  show: Boolean,
  mealNo: Number,
});

// 식단 상세 정보
const meal = ref(null);

const emit = defineEmits(["close"]);

// 날짜 포맷 표시: 2024. 06. 24 (월)
function formatDateForDisplay(isoDate) {
  if (!isoDate) return "";
  const date = new Date(isoDate);
  const weekDay = ["일", "월", "화", "수", "목", "금", "토"][date.getDay()];
  return `${date.getFullYear()}. ${String(date.getMonth() + 1).padStart(2, "0")}. ${String(date.getDate()).padStart(2, "0")} (${weekDay})`;
}

// 기본이미지 지정
import defaultImage from "@/assets/images/global/food_default.png";
const currentIndex = ref(0);

function getImageUrl(index) {
  if (!meal.value?.fileList?.length) return defaultImage;
  return meal.value.fileList[index]?.attachmentName || defaultImage;
}

function prevImage() {
  if (!meal.value?.fileList) return;
  currentIndex.value = (currentIndex.value - 1 + meal.value.fileList.length) % meal.value.fileList.length;
}

function nextImage() {
  if (!meal.value?.fileList) return;
  currentIndex.value = (currentIndex.value + 1) % meal.value.fileList.length;
}

// 아침 점심 저녁
function getMealCategoryClass(time) {
  switch (time) {
    case "BREAKFAST":
      return "badge-breakfast";
    case "LUNCH":
      return "badge-lunch";
    case "DINNER":
      return "badge-dinner";
    case "SNACK":
      return "badge-snack";
    default:
      return "";
  }
}
function convertMealTime(mealTime) {
  if (mealTime === "BREAKFAST") return "아침";
  if (mealTime === "LUNCH") return "점심";
  if (mealTime === "DINNER") return "저녁";
  if (mealTime === "SNACK") return "간식";
  return time;
}

// chart.js //////////////////////////////////////////////////////////////////////

// Pinia : 차트 함수 store //////////////////////////////////////////////////////////////////
import { useChartRender } from "@/stores/meal/chartRenderStore";
const chartRender = useChartRender();
// Pinia : 사용자 맞춤 영양소 권장량 store ///////////////////////////////////////////////////
import { userNutritionStore } from "@/stores/meal/nutritionStore";
const nutritionStore = userNutritionStore();

import { Chart, DoughnutController, BarController, ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend } from "chart.js";

Chart.register(DoughnutController, BarController, ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const calorieChart = ref(null);
const waterChart = ref(null);
const dailyCarbChart = ref(null);
const dailyProteinChart = ref(null);
const dailyFatChart = ref(null);

// 식단 영양 분석 //////////////////////////////////////////////////////////////////////
async function requestDayReport() {
  chartRender.drawBarCharts(calorieChart.value, "칼로리", meal.value.energy, nutritionStore.calorieGoal, "#EF5E5E");
  chartRender.drawBarCharts(waterChart.value, "수분", meal.value.water, nutritionStore.waterGoal, "#9cd9ff");

  chartRender.drawBarCharts(dailyCarbChart.value, "탄수화물", meal.value.carbohydrate, nutritionStore.carbGoal, "#a8e063");
  chartRender.drawBarCharts(dailyProteinChart.value, "단백질", meal.value.protein, nutritionStore.proteinGoal, "#ffa751");
  chartRender.drawBarCharts(dailyFatChart.value, "지방", meal.value.totalFattyAcids, nutritionStore.fatGoal, "#f9d423");
}

// mealNo가 바뀌거나 모달이 열릴 때 API 호출
watch(
  () => [props.mealNo, props.show],
  async ([mealNo, show]) => {
    if (show && mealNo) {
      try {
        const { data } = await axios.get(`http://localhost:8080/api/meal/mealDetail`, { params: { mealNo } });
        meal.value = data;
        console.log(meal.value);
        await nextTick(); // DOM 렌더 완료 대기
        requestDayReport();
      } catch (err) {
        console.error("식단 상세 조회 실패:", err);
      }
    }
  },
  { immediate: true }
);

// onMounted(async () => {
//   requestDayReport();
// });

function closeModal() {
  emit("close");
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/meal/MealDetailModal.css";
</style>

<template>
  <div v-if="show" class="modal-overlay">
    <div class="modal">
      <div class="main-content">
        <div class="frame-18">
          <div class="day">
            <div class="day">
              <div class="year">
                <div class="div">
                  {{ formatDateForDisplay(meal.mealRegDate) }}
                </div>
              </div>
            </div>
            <div class="badges-info">
              <div :class="['badge-meal-category', getMealCategoryClass(meal.mealTime)]">
                <div class="category">{{ convertMealTime(meal.mealTime) }}</div>
              </div>
            </div>
          </div>
          <div class="head-info">
            <div class="frame-1">
              <div class="action">
                <div class="button-picker">
                  <div class="text">
                    <div class="label">수정</div>
                  </div>
                </div>
              </div>
              <div class="action">
                <div class="button-picker2">
                  <div class="text">
                    <div class="label">삭제</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="frame-13">
          <div class="frame-17">
            <div class="frame-20">
              <div class="name">{{ meal.mealTitle }}</div>
              <div class="name2">
                {{ meal.mealContent }}
              </div>
            </div>
            <div class="frame-172">
              <div class="frame-202">
                <div class="frame-12">
                  <div class="card-list-all-menu" v-for="(food, index) in meal.foodList" :key="index">
                    <div class="frame-122">
                      <div class="frame-123">
                        <div class="name3">{{ food.foodName }}</div>
                        <div class="nutrition-info">
                          <div class="amount">{{ food.foodSubGroup }}</div>
                          <div class="separator"></div>
                          <div class="unit">{{ food.foodWeight }}g</div>
                        </div>
                      </div>
                      <div class="nutrition-info">
                        <div class="info-cal">
                          <div class="value">
                            <img class="icon-special-fire" src="@/assets/images/meal/RegisterMealModal/icon-special-fire0.svg" />
                            <div class="amount">{{ food.nutriEnergy }} kcal</div>
                          </div>
                        </div>
                        <div class="separator"></div>
                        <div class="info-carbs">
                          <div class="value2">
                            <img class="icon-special-bread" src="@/assets/images/meal/RegisterMealModal/icon-special-bread0.svg" />
                            <div class="amount">탄수화물</div>
                            <div class="unit">{{ food.nutriCarbohydrate }}g</div>
                          </div>
                        </div>
                        <div class="separator"></div>
                        <div class="info-protein">
                          <div class="value3">
                            <img class="icon-special-fish" src="@/assets/images/meal/RegisterMealModal/icon-special-fish0.svg" />
                            <div class="amount">단백질</div>
                            <div class="unit">{{ food.nutriProtein }}g</div>
                          </div>
                        </div>
                        <div class="separator"></div>
                        <div class="info-fats">
                          <img class="icon-special-drop" src="@/assets/images/meal/RegisterMealModal/icon-special-drop0.svg" />
                          <div class="value2">
                            <div class="amount">지방</div>
                            <div class="unit">{{ food.nutriTotalFattyAcids }}g</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="image-slider">
            <button @click="prevImage" class="arrow left">〈</button>

            <img class="preview-image" :src="getImageUrl(currentIndex)" alt="식단 이미지" />

            <button @click="nextImage" class="arrow right">〉</button>
          </div>
        </div>
        <div class="frame-203">
          <div class="frame-204">
            <div class="title">식단에 포함된 영양소</div>
          </div>
          <div class="frame-16">
            <div class="detail-frame-133">
              <!-- 칼로리 -->
              <div class="detail-frame-14">
                <div class="detail-group-15">
                  <div class="detail-frame-142">
                    <div class="detail-title5">칼로리</div>
                    <div class="detail-title6">{{ meal.energy }} kcal</div>
                  </div>
                  <div class="detail-menu-nav3">
                    <canvas class="detail-chart-bar" ref="calorieChart" style="width: 100%"></canvas>
                  </div>
                </div>
              </div>
              <!-- 탄수화물 -->
              <div class="detail-frame-14">
                <div class="detail-group-15">
                  <div class="detail-frame-142">
                    <div class="detail-title5">탄수화물</div>
                    <div class="detail-title6">{{ meal.carbohydrate }} g</div>
                  </div>
                  <div class="detail-menu-nav3">
                    <canvas class="detail-chart-bar" ref="dailyCarbChart" style="width: 100%"></canvas>
                  </div>
                </div>
                <div class="detail-frame-134">
                  <div class="detail-frame-135">
                    <div class="detail-title7">당류</div>
                    <div class="detail-title7">{{ meal.sugar }} g</div>
                  </div>
                  <div class="detail-frame-143">
                    <div class="detail-title7">식이섬유</div>
                    <div class="detail-title7">{{ meal.dietaryFiber }} g</div>
                  </div>
                </div>
              </div>
              <!-- 단백질 -->
              <div class="detail-frame-14">
                <div class="detail-group-15">
                  <div class="detail-frame-142">
                    <div class="detail-title5">단백질</div>
                    <div class="detail-title6">{{ meal.protein }} g</div>
                  </div>
                  <div class="detail-menu-nav3">
                    <canvas class="detail-chart-bar" ref="dailyProteinChart" style="width: 100%"></canvas>
                  </div>
                </div>
                <div class="detail-frame-134">
                  <div class="detail-frame-135">
                    <div class="detail-title7">회분</div>
                    <div class="detail-title7">{{ meal.ash }} g</div>
                  </div>
                </div>
              </div>
              <!-- 지방 -->
              <div class="detail-frame-14">
                <div class="detail-group-15">
                  <div class="detail-frame-142">
                    <div class="detail-title5">지방</div>
                    <div class="detail-title6">{{ meal.totalFattyAcids }} g</div>
                  </div>
                  <div class="detail-menu-nav3">
                    <canvas class="detail-chart-bar" ref="dailyFatChart" style="width: 100%"></canvas>
                  </div>
                </div>
                <div class="detail-frame-134">
                  <div class="detail-frame-135">
                    <div class="detail-title7">포화지방</div>
                    <div class="detail-title7">{{ meal.saturatedFats }} g</div>
                  </div>
                  <div class="detail-frame-143">
                    <div class="detail-title7">불포화지방</div>
                    <div class="detail-title7">{{ meal.unsaturatedFats }} g</div>
                  </div>
                </div>
              </div>
              <!-- 수분 -->
              <div class="detail-frame-14">
                <div class="detail-group-15">
                  <div class="detail-frame-142">
                    <div class="detail-title5">수분</div>
                    <div class="detail-title6">{{ meal.water }} ml</div>
                  </div>
                  <div class="detail-menu-nav3">
                    <canvas class="detail-chart-bar" ref="waterChart" style="width: 100%"></canvas>
                  </div>
                </div>
              </div>
            </div>
            <div class="detail-separator-1"></div>
            <div class="detail-frame-133-1">
              <!-- 비타민 -->
              <div class="detail-frame-14-1">
                <div class="detail-title8">비타민</div>
                <div class="detail-frame-134">
                  <div class="detail-frame-135">
                    <div class="detail-title7">비타민A</div>
                    <div class="detail-title7">{{ meal.vitaminACarotene }} µg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">비타민 B1 티아민</div>
                    <div class="detail-title7">{{ meal.vitaminB1 }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">비타민 B2 리보플라빈</div>
                    <div class="detail-title7">{{ meal.vitaminB2 }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">비타민 B3 니아신</div>
                    <div class="detail-title7">{{ meal.niacin }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">엽산</div>
                    <div class="detail-title7">{{ meal.folate }} µg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">비타민 C</div>
                    <div class="detail-title7">{{ meal.vitaminC }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">비타민 D</div>
                    <div class="detail-title7">{{ meal.vitaminD }} µg</div>
                  </div>
                </div>
              </div>
              <div class="detail-separator"></div>
              <!-- 콜레스테롤 -->
              <div class="detail-frame-14-1">
                <div class="detail-title8">콜레스테롤</div>
                <div class="detail-frame-137">
                  <div class="detail-frame-135">
                    <div class="detail-title7"></div>
                    <div class="detail-title7">{{ meal.cholesterol }} mg</div>
                  </div>
                </div>
              </div>
              <div class="detail-separator"></div>
              <!-- 무기질 -->
              <div class="detail-frame-14-1">
                <div class="detail-title8">무기질</div>
                <div class="detail-frame-134">
                  <div class="detail-frame-135">
                    <div class="detail-title7">나트륨</div>
                    <div class="detail-title7">{{ meal.sodium }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">칼슘</div>
                    <div class="detail-title7">{{ meal.calcium }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">철</div>
                    <div class="detail-title7">{{ meal.iron }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">인</div>
                    <div class="detail-title7">{{ meal.phosphorus }} mg</div>
                  </div>
                  <div class="detail-frame-135">
                    <div class="detail-title7">칼륨</div>
                    <div class="detail-title7">{{ meal.potassium }} mg</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
