<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from "vue";
import axios from "axios";
import defaultImage from "@/assets/images/global/food_default.png";

const props = defineProps({
  show: Boolean,
  mealNo: Number,
});

// 식단 상세 정보
const meal = ref(null);

// mealNo가 바뀌거나 모달이 열릴 때 API 호출
watch(
  () => [props.mealNo, props.show],
  async ([mealNo, show]) => {
    if (show && mealNo) {
      try {
        const { data } = await axios.get(`http://localhost:8080/api/meal/mealDetail`, { params: { mealNo } });
        meal.value = data;
        console.error(meal.value);
      } catch (err) {
        console.error("식단 상세 조회 실패:", err);
      }
    }
  },
  { immediate: true }
);

const emit = defineEmits(["close"]);

// 날짜 포맷 표시: 2024. 06. 24 (월)
function formatDateForDisplay(isoDate) {
  if (!isoDate) return "";
  const date = new Date(isoDate);
  const weekDay = ["일", "월", "화", "수", "목", "금", "토"][date.getDay()];
  return `${date.getFullYear()}. ${String(date.getMonth() + 1).padStart(2, "0")}. ${String(date.getDate()).padStart(2, "0")} (${weekDay})`;
}

const mealOptions = [
  { value: "BREAKFAST", label: "아침" },
  { value: "LUNCH", label: "점심" },
  { value: "SNACK", label: "간식" },
  { value: "DINNER", label: "저녁" },
];

function closeModal() {
  emit("close");
}

onBeforeUnmount(() => {
  document.removeEventListener("click", handleClickOutside);
});
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
                <div class="year2">2024</div>
                <div class="div">년</div>
              </div>
              <div class="month">
                <div class="month2">06</div>
                <div class="div">월</div>
              </div>
              <div class="day2">
                <div class="day3">24</div>
                <div class="div">일</div>
              </div>
              <div class="day2">
                <div class="div">(</div>
                <div class="div">수</div>
                <div class="div">)</div>
              </div>
            </div>
            <div class="badges-info">
              <div class="badge-meal-category">
                <div class="category">점심</div>
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
              <div class="name">친구들이랑 같이 점심!!</div>
              <div class="name2">오랜만에 친구들이랑 점심 같이 엄청 배부르게 먹었음</div>
            </div>
            <div class="frame-172">
              <div class="frame-202">
                <div class="frame-12">
                  <div class="card-list-all-menu">
                    <div class="frame-122">
                      <div class="frame-123">
                        <div class="name3">강낭콩샐러드</div>
                        <div class="nutrition-info">
                          <div class="amount">무침류</div>
                          <div class="amount">1인분</div>
                          <div class="unit">150.40g</div>
                        </div>
                      </div>
                      <div class="nutrition-info">
                        <div class="info-cal">
                          <div class="value">
                            <div class="amount">150 kcal</div>
                          </div>
                        </div>
                        <div class="info-carbs">
                          <div class="value2">
                            <div class="amount">탄수화물</div>
                            <div class="unit">30g</div>
                          </div>
                        </div>
                        <div class="info-protein">
                          <div class="value3">
                            <div class="amount">단백질</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                        <div class="info-fats">
                          <div class="value2">
                            <div class="amount">지방</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-list-all-menu">
                    <div class="frame-122">
                      <div class="frame-123">
                        <div class="name3">낙지젓</div>
                        <div class="nutrition-info">
                          <div class="amount">젓갈류</div>
                          <div class="amount">0.5인분</div>
                          <div class="unit">20.10g</div>
                        </div>
                      </div>
                      <div class="nutrition-info">
                        <div class="info-cal">
                          <div class="value">
                            <div class="amount">45 kcal</div>
                          </div>
                        </div>
                        <div class="info-carbs">
                          <div class="value2">
                            <div class="amount">탄수화물</div>
                            <div class="unit">30g</div>
                          </div>
                        </div>
                        <div class="info-protein">
                          <div class="value3">
                            <div class="amount">단백질</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                        <div class="info-fats">
                          <div class="value2">
                            <div class="amount">지방</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-list-all-menu">
                    <div class="frame-122">
                      <div class="frame-123">
                        <div class="name3">현미밥</div>
                        <div class="nutrition-info">
                          <div class="amount">젓갈류</div>
                          <div class="amount">0.5인분</div>
                          <div class="unit">20.10g</div>
                        </div>
                      </div>
                      <div class="nutrition-info">
                        <div class="info-cal">
                          <div class="value">
                            <div class="amount">45 kcal</div>
                          </div>
                        </div>
                        <div class="info-carbs">
                          <div class="value2">
                            <div class="amount">탄수화물</div>
                            <div class="unit">30g</div>
                          </div>
                        </div>
                        <div class="info-protein">
                          <div class="value3">
                            <div class="amount">단백질</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                        <div class="info-fats">
                          <div class="value2">
                            <div class="amount">지방</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-list-all-menu">
                    <div class="frame-122">
                      <div class="frame-123">
                        <div class="name3">유채나물볶음</div>
                        <div class="nutrition-info">
                          <div class="amount">볶음류</div>
                          <div class="amount">0.5인분</div>
                          <div class="unit">20.10g</div>
                        </div>
                      </div>
                      <div class="nutrition-info">
                        <div class="info-cal">
                          <div class="value">
                            <div class="amount">45 kcal</div>
                          </div>
                        </div>
                        <div class="info-carbs">
                          <div class="value2">
                            <div class="amount">탄수화물</div>
                            <div class="unit">30g</div>
                          </div>
                        </div>
                        <div class="info-protein">
                          <div class="value3">
                            <div class="amount">단백질</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                        <div class="info-fats">
                          <div class="value2">
                            <div class="amount">지방</div>
                            <div class="unit">14g</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="menu-nav">
                <div class="button-nav"></div>
                <div class="button-nav2"></div>
              </div>
            </div>
          </div>
          <div class="image"></div>
        </div>
        <div class="frame-203">
          <div class="frame-204">
            <div class="title">식단에 포함된 영양소</div>
          </div>
          <div class="frame-16">
            <div class="frame-132">
              <div class="frame-14">
                <div class="group-15">
                  <div class="frame-142">
                    <div class="title2">탄수화물</div>
                    <div class="title3">13g</div>
                  </div>
                  <div class="menu-nav2">
                    <div class="button-nav3"></div>
                    <div class="button-nav2"></div>
                  </div>
                </div>
                <div class="frame-133">
                  <div class="frame-134">
                    <div class="title4">당류</div>
                    <div class="title4">13g</div>
                  </div>
                  <div class="frame-143">
                    <div class="title4">식이섬유</div>
                    <div class="title4">13g</div>
                  </div>
                </div>
              </div>
              <div class="frame-15">
                <div class="group-15">
                  <div class="frame-142">
                    <div class="title2">단백질</div>
                    <div class="title3">13g</div>
                  </div>
                  <div class="menu-nav2">
                    <div class="button-nav4"></div>
                    <div class="button-nav2"></div>
                  </div>
                </div>
                <div class="frame-135">
                  <div class="frame-134">
                    <div class="title4">회분</div>
                    <div class="title4">13g</div>
                  </div>
                </div>
              </div>
              <div class="frame-162">
                <div class="group-15">
                  <div class="frame-142">
                    <div class="title2">지방</div>
                    <div class="title3">13g</div>
                  </div>
                  <div class="menu-nav2">
                    <div class="button-nav5"></div>
                    <div class="button-nav2"></div>
                  </div>
                </div>
                <div class="frame-133">
                  <div class="frame-134">
                    <div class="title4">포화지방</div>
                    <div class="title4">13g</div>
                  </div>
                  <div class="frame-143">
                    <div class="title4">불포화지방</div>
                    <div class="title4">13g</div>
                  </div>
                </div>
              </div>
              <div class="frame-173">
                <div class="group-15">
                  <div class="frame-142">
                    <div class="title2">수분</div>
                    <div class="title3">13g</div>
                  </div>
                  <div class="menu-nav2">
                    <div class="button-nav6"></div>
                    <div class="button-nav2"></div>
                  </div>
                </div>
              </div>
            </div>
            <div class="frame-26"></div>
            <div class="frame-136">
              <div class="frame-144">
                <div class="frame-174">
                  <div class="title5">콜레스테롤</div>
                  <div class="frame-137">
                    <div class="frame-134">
                      <div class="title4"></div>
                      <div class="title4">13g</div>
                    </div>
                  </div>
                </div>
                <div class="frame-182">
                  <div class="title5">무기질</div>
                  <div class="frame-138">
                    <div class="frame-134">
                      <div class="title4">나트륨</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-143">
                      <div class="title4">칼슘</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-152">
                      <div class="title4">철</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-163">
                      <div class="title4">인</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-175">
                      <div class="title4">칼륨</div>
                      <div class="title4">13g</div>
                    </div>
                  </div>
                </div>
                <div class="frame-19">
                  <div class="title5">비타민</div>
                  <div class="frame-138">
                    <div class="frame-134">
                      <div class="title4">비타민A</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-183">
                      <div class="title4">비타민 B1 티아민</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-192">
                      <div class="title4">비타민 B2 리보플라빈</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-205">
                      <div class="title4">비타민 B3 니아신</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-143">
                      <div class="title4">엽산</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-152">
                      <div class="title4">비타민 C</div>
                      <div class="title4">13g</div>
                    </div>
                    <div class="frame-163">
                      <div class="title4">비타민 D</div>
                      <div class="title4">13g</div>
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
</template>
