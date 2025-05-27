<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import axios from "axios";
import { useMealRegisterStore } from "@/stores/meal/mealRegisterStore.js";

const props = defineProps({ show: Boolean });
const emit = defineEmits(["close"]);

const store = useMealRegisterStore();

const userNo = 1;
const waterList = ref([]);

// 날짜 선택 ///////////////////////////////////////////////////////////////////
const selectedDate = ref("");

// 날짜 포맷 표시: 2024. 06. 24 (월)
function formatDateForDisplay(isoDate) {
  if (!isoDate) return "";
  const date = new Date(isoDate);
  const weekDay = ["일", "월", "화", "수", "목", "금", "토"][date.getDay()];
  return `${date.getFullYear()}. ${String(date.getMonth() + 1).padStart(2, "0")}. ${String(date.getDate()).padStart(2, "0")} (${weekDay})`;
}

// 물 리스트 받아오기 //////////////////////////////////////////////////////
async function fetchWaterList() {
  const today = new Date().toISOString().slice(0, 10);
  try {
    const res = await axios.get(`http://localhost:8080/api/meal/water?userNo=${userNo}&mealRegDate=${today}`);
    waterList.value = res.data;
  } catch (err) {
    console.error("수분 리스트 불러오기 실패:", err);
  }
}

// 물 삭제 ////////////////////////////////////////////////////////////////
async function deleteWater(mealNo) {
  try {
    await axios.delete(`http://localhost:8080/api/meal/meal/${mealNo}`);
    // 성공 시 목록에서 제거
    waterList.value = waterList.value.filter((item) => item.mealNo !== mealNo);
  } catch (err) {
    console.error("삭제 실패:", err);
    alert("삭제에 실패했습니다.");
  }
}

onMounted(() => {
  fetchWaterList();
});

// 입력 변수
const mealTitle = ref("");
const selectedPortion = ref(null);

// 식단 insert /////////////////////////////////////////////////////////////
async function submitMeal() {
  if (!mealTitle.value.trim()) {
    alert("제목을 입력해주세요.");
    return;
  }
  if (selectedPortion.value === null) {
    alert("마신 양을 선택해주세요.");
    return;
  }

  const today = new Date().toISOString().slice(0, 10);
  const mealForm = {
    mealNo: 0,
    userNo: 1,
    mealRegDate: today,
    mealTime: "WATER",
    mealTitle: mealTitle.value,
    mealContent: null,
    mealFoodList: [
      {
        mealFoodNo: 0,
        mealNo: 0,
        foodCode: "WATER00",
        foodAmount: Number(selectedPortion.value),
      },
    ],
  };
  console.log("선택한 portion:", selectedPortion.value);

  const formData = new FormData();
  formData.append("meal", new Blob([JSON.stringify(mealForm)], { type: "application/json" }));

  try {
    await axios.post("http://localhost:8080/api/meal", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    alert("물 기록 완료!");
    emit("waterRegistered");
    closeModal();
  } catch (err) {
    console.error("기록 실패:", err);
    alert("등록 중 오류가 발생했습니다.");
  }
}

function closeModal() {
  emit("close");

  // 선택 상태 초기화
  selectedDate.value = new Date().toISOString().split("T")[0];
  selectedPortion.value = null;
}

onMounted(() => {
  selectedDate.value = new Date().toISOString().split("T")[0];
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
@import "@/assets/css/vars.css";
</style>

<style scoped>
@import "@/assets/css/meal/RegisterWaterModal.css";
</style>

<template>
  <div v-if="show" class="modal-overlay">
    <div class="body">
      <div class="header-menu">
        <div class="name">물 기록하기</div>
      </div>
      <div class="frame-19">
        <!-- <div class="frame-192">
          <div class="name2">오늘 섭취한 수분</div>
          <div class="group-17">
            <canvas class="chart-doughnut" ref="waterChart"></canvas>
          </div>
        </div> -->
        <div class="group-19">
          <div class="water-list">
            <div class="frame-17">
              <div v-for="(item, index) in waterList" :key="item.mealNo" class="card-list-all-menu">
                <div class="nutrition-info">
                  <div class="unit">{{ item.foodAmount }} ml</div>
                  <div class="separator"></div>
                  <div class="amount">{{ item.mealTitle }}</div>
                </div>
                <div class="action" @click="deleteWater(item.mealNo)">
                  <img class="button-picker8" src="@/assets/images/meal/RegisterMealModal/button-picker8.svg" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="main">
        <form class="content" @submit.prevent="submitMeal">
          <div class="frame-12">
            <div class="name3">제목 입력</div>
            <div class="input-search">
              <div class="text">
                <input type="text" class="label" v-model="mealTitle" placeholder="제목을 입력하세요." />
              </div>
            </div>
          </div>
          <div class="frame-13">
            <div class="name3">섭취량 추가</div>
            <div class="frame-127">
              <label v-for="portion in ['0.5', '1', '1.5', '2', '2.5', '3']" :key="portion" class="portion-radio-label">
                <input type="radio" name="portion" :value="portion" v-model="selectedPortion" class="portion-radio" />
                <span class="amount">{{ portion }}잔 ({{ portion * 200 }}ml)</span>
              </label>
            </div>
          </div>

          <div class="header-menu2">
            <button @click="closeModal" class="button-close">
              <div class="text2">
                <div class="label2">닫기</div>
              </div>
            </button>
            <button class="button-more">
              <div class="text2">
                <div class="label2">추가하기</div>
              </div>
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
