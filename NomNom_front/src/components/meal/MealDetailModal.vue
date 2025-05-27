<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import axios from 'axios';
import { useMealRegisterStore } from '@/stores/meal/mealRegisterStore.js';
import defaultImage from '@/assets/images/meal/RegisterMealModal/place-image-here0.png';

const props = defineProps({ show: Boolean });
const emit = defineEmits(['close']);

const store = useMealRegisterStore();

// 날짜 선택 ///////////////////////////////////////////////////////////////////
const selectedDate = ref('');

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

// 끼니 선택 로직 //////////////////////////////////////////////////////////////
const selectedTime = ref('BREAKFAST');

const mealOptions = [
  { value: 'BREAKFAST', label: '아침' },
  { value: 'LUNCH', label: '점심' },
  { value: 'SNACK', label: '간식' },
  { value: 'DINNER', label: '저녁' },
];

const mealTitle = ref('');
const mealMemo = ref('');

// 음식 메뉴 검색 로직 ////////////////////////////////////////////////////////
const searchQuery = ref('');
const searchResults = ref([]);
const isDropdownOpen = ref(false);

const dropdownArea = ref(null);

// 음식명으로 검색
async function searchFood() {
  if (!searchQuery.value.trim()) return;
  try {
    const { data } = await axios.get(
      'http://localhost:8080/api/meal/searchFood',
      {
        params: { foodName: searchQuery.value },
      }
    );
    searchResults.value = data;
  } catch (error) {
    console.error('검색 실패:', error);
  }
}

// 외부 클릭 감지해서 닫기
function handleClickOutside(event) {
  const path = event.composedPath();
  if (dropdownArea.value && !path.includes(dropdownArea.value)) {
    isDropdownOpen.value = false;
  }
}

// 선택된 메뉴 정보 상태
const selectedFood = ref(null);

// 인분 선택 상태
const selectedPortion = ref(null);

// 음식 선택 시 상태 저장하고 dropdown 닫기
async function selectFood(item) {
  isDropdownOpen.value = false;
  searchQuery.value = '';

  const detailedFood = await fetchFoodDetail(item.foodCode);

  if (!detailedFood) {
    alert('선택한 음식의 상세 정보를 불러오지 못했습니다.');
    return;
  }

  selectedFood.value = {
    ...item, // 이름, 그룹 등 기본 정보 유지
    ...detailedFood, // 영양 정보 덮어쓰기
  };

  selectedPortion.value = null;
}

// 선택된 음식 상세 정보 조회
async function fetchFoodDetail(foodCode) {
  try {
    const { data } = await axios.get(
      `http://localhost:8080/api/meal/food?foodCode=${foodCode}`
    );
    return data;
  } catch (err) {
    console.error('음식 상세 조회 실패:', err);
    return null;
  }
}

// 최종 선택 리스트
const selectedMeals = ref([]);

function addSelectedMeal() {
  if (!selectedFood.value || !selectedPortion.value) {
    alert('메뉴와 식사량을 선택해주세요!');
    return;
  }

  const portion = Number(selectedPortion.value);
  selectedMeals.value.push({
    ...selectedFood.value,
    portion: selectedPortion.value,
    totalWeight: Number(
      (Number(selectedFood.value.foodWeight) * portion).toFixed(2)
    ),
    energy: Math.round(Number(selectedFood.value.nutriEnergy) * portion),
    carbohydrate: Number(
      (Number(selectedFood.value.nutriCarbohydrate) * portion).toFixed(1)
    ),
    protein: Number(
      (Number(selectedFood.value.nutriProtein) * portion).toFixed(1)
    ),
    fat: Number(
      (Number(selectedFood.value.nutriTotalFattyAcids) * portion).toFixed(1)
    ),
  });

  selectedFood.value = null;
  detailedFood.value = null;
  selectedPortion.value = null;
  searchQuery.value = '';
  searchResults.value = [];
}

// 선택한 음식 리스트 초기화
function clearSelectedMeal() {
  selectedFood.value = null;
  selectedPortion.value = null;
  searchQuery.value = '';
  searchResults.value = [];
  selectedMeals.value = [];
}

// 사진 업로드 /////////////////////////////////////////////////////////////////
const fileList = ref([]); // 업로드된 파일들
const previewUrl = ref(''); // 첫 번째 파일 미리보기 URL

// input file 숨기고 trigger용 ref
const fileInputRef = ref(null);

// place-image-here 클릭 시 file input 열기
function triggerFileUpload() {
  fileInputRef.value?.click();
}

// 파일 선택 시 처리
function handleFileChange(event) {
  const files = Array.from(event.target.files).map((file) => ({
    file,
    uid: crypto.randomUUID(),
  }));

  fileList.value = [...fileList.value, ...files];

  if (fileList.value.length > 0) {
    previewUrl.value = URL.createObjectURL(fileList.value[0].file);
  }
}

// 파일 삭제
function removeFile(index) {
  const removed = fileList.value.splice(index, 1)[0];

  // 기존 대표 삭제된 경우 → 새 대표 설정
  if (index === 0 && fileList.value.length > 0) {
    URL.revokeObjectURL(previewUrl.value);
    previewUrl.value = URL.createObjectURL(fileList.value[0].file);
  }

  // 전체 비었을 때도 초기화
  if (fileList.value.length === 0) {
    previewUrl.value = '';
  }
}

function setAsMainImage(index) {
  if (index === 0) return;

  const newMain = fileList.value.splice(index, 1)[0];
  fileList.value.unshift(newMain);

  previewUrl.value = URL.createObjectURL(fileList.value[0].file);
}

// 식단 insert /////////////////////////////////////////////////////////////
async function submitMeal() {
  if (!mealTitle.value.trim()) {
    alert('식단 제목을 입력해주세요.');
    return;
  }
  if (!selectedDate.value) {
    alert('날짜를 선택해주세요.');
    return;
  }
  if (selectedMeals.value.length === 0) {
    alert('최소 1개 이상의 음식을 선택해주세요.');
    return;
  }

  const mealForm = {
    mealNo: 0,
    userNo: 1,
    mealRegDate: selectedDate.value,
    mealTime: selectedTime.value,
    mealTitle: mealTitle.value,
    mealContent: mealMemo.value,
    mealFoodList: selectedMeals.value.map((meal) => ({
      mealFoodNo: 0,
      mealNo: 0,
      foodCode: meal.foodCode,
      foodAmount: Number(meal.portion),
    })),
  };

  const formData = new FormData();
  formData.append(
    'meal',
    new Blob([JSON.stringify(mealForm)], { type: 'application/json' })
  );

  fileList.value.forEach((fileWrap) => {
    formData.append('fileList', fileWrap.file);
  });

  try {
    await axios.post('http://localhost:8080/api/meal', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    alert('식단 등록 완료!');
    emit('mealRegistered');
    closeModal();
  } catch (err) {
    console.error('등록 실패:', err);
    alert('등록 중 오류가 발생했습니다.');
  }
}

function closeModal() {
  emit('close');

  // 선택 상태 초기화
  mealTitle.value = '';
  mealMemo.value = '';
  selectedDate.value = new Date().toISOString().split('T')[0];
  selectedTime.value = 'BREAKFAST';

  selectedFood.value = null;
  selectedPortion.value = null;
  selectedMeals.value = [];

  searchQuery.value = '';
  searchResults.value = [];
  isDropdownOpen.value = false;

  // 파일 초기화
  fileList.value = [];
  previewUrl.value = '';
}

onMounted(() => {
  selectedDate.value = new Date().toISOString().split('T')[0];
});

onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside);
});
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
@import '@/assets/css/vars.css';
</style>

<style scoped>
@import '@/assets/css/meal/RegisterMealModal.css';
</style>

<template>
  <div v-if="show" class="modal-overlay">
    <div class="modal">
      <form class="body" @submit.prevent="submitMeal">
        <div class="header-menu">
          <div class="name">식단 등록하기</div>
          <div class="header-menu2">
            <button @click="closeModal" class="button-close">
              <div class="text">
                <div class="label">닫기</div>
              </div>
            </button>
            <button class="button-more">
              <div class="text">
                <div class="label">등록하기</div>
              </div>
            </button>
          </div>
        </div>
        <div class="main">
          <div class="content">
            <div class="content-scroll">
              <form class="frame-12">
                <div class="frame-17">
                  <div class="name2">날짜 선택</div>
                  <input
                    type="date"
                    v-model="selectedDate"
                    class="date-input"
                  />
                </div>

                <div class="frame-18">
                  <div class="name2">식사 시간</div>
                  <div class="left-section">
                    <div class="segmented-button">
                      <label
                        v-for="option in mealOptions"
                        :key="option.value"
                        :class="[
                          'button-picker-base',
                          selectedTime === option.value
                            ? 'button-picker'
                            : 'button-picker2',
                        ]"
                      >
                        <input
                          type="radio"
                          name="mealTime"
                          :value="option.value"
                          v-model="selectedTime"
                          class="meal-time-radio"
                        />
                        <div class="text3">
                          <div class="label2">{{ option.label }}</div>
                        </div>
                      </label>
                    </div>
                  </div>
                </div>

                <!-- 식단 제목 입력 -->
                <div class="frame-13">
                  <div class="name2">식단 제목</div>
                  <div class="input-search">
                    <input
                      type="text"
                      class="label4"
                      placeholder="제목을 입력하세요."
                      v-model="mealTitle"
                      maxlength="100"
                    />
                  </div>
                </div>

                <!-- 메모 작성 -->
                <div class="frame-14">
                  <div class="name2">메모 작성</div>
                  <div class="input-search2">
                    <textarea
                      class="label4"
                      placeholder="메모를 입력하세요."
                      v-model="mealMemo"
                      maxlength="100"
                      rows="3"
                    ></textarea>
                  </div>
                </div>

                <div class="frame-15" ref="dropdownArea">
                  <div class="name2">메뉴 선택</div>
                  <div class="frame-122">
                    <div class="frame-172">
                      <!-- 검색창 -->
                      <div class="input-search3">
                        <div class="icon">
                          <img
                            class="icon-magnifying-glass"
                            src="@/assets/images/meal/RegisterMealModal/icon-magnifying-glass0.svg"
                          />
                        </div>
                        <input
                          type="text"
                          v-model="searchQuery"
                          placeholder="메뉴 검색"
                          class="text4"
                          @focus="isDropdownOpen = true"
                          @keydown.enter.prevent="searchFood"
                        />
                      </div>

                      <!-- 검색 버튼 -->
                      <div class="button-picker4" @click="searchFood">
                        <div class="label2">검색</div>
                      </div>
                    </div>

                    <!-- 드롭다운 결과 -->
                    <ul v-if="isDropdownOpen" class="dropdown-result">
                      <!-- 결과가 있을 경우 -->
                      <li
                        v-for="(item, index) in searchResults"
                        :key="index"
                        class="dropdown-item"
                        @click="selectFood(item)"
                      >
                        {{ item.foodName }} ({{ item.foodSubGroup }})
                      </li>

                      <!-- 결과 없음 & 검색어 있음 -->
                      <li
                        v-if="
                          searchResults.length === 0 &&
                          searchQuery.trim() !== ''
                        "
                        class="dropdown-item dropdown-empty"
                      >
                        검색 결과가 없습니다.
                      </li>

                      <!-- 검색어 없음 -->
                      <li
                        v-if="searchQuery.trim() === ''"
                        class="dropdown-item dropdown-empty"
                      >
                        검색어를 입력하세요.
                      </li>
                    </ul>

                    <!-- 카드 형식: 메뉴 선택 시만 표시 -->
                    <div class="card-list-all-menu" v-if="selectedFood">
                      <div class="frame-123">
                        <div class="frame-124">
                          <div class="info-cal">
                            <div class="name3">{{ selectedFood.foodName }}</div>
                            <div class="value">
                              <div class="amount">
                                {{ selectedFood.foodSubGroup }}
                              </div>
                            </div>
                          </div>

                          <div class="button-picker5" @click="addSelectedMeal">
                            <div class="label2">추가하기</div>
                          </div>
                        </div>
                        <div class="frame-125">
                          <div class="frame-126">
                            <div class="name4">식사량 선택</div>
                            <div class="separator"></div>
                            <div class="frame-127">
                              <label
                                v-for="portion in [
                                  '0.5',
                                  '1',
                                  '1.5',
                                  '2',
                                  '2.5',
                                  '3',
                                ]"
                                :key="portion"
                                class="portion-radio-label"
                              >
                                <input
                                  type="radio"
                                  name="portion"
                                  :value="portion"
                                  v-model="selectedPortion"
                                  class="portion-radio"
                                />
                                <span class="amount">{{ portion }}인분</span>
                              </label>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
              <div class="frame-133">
                <div class="frame-129">
                  <div class="name5">최종 선택 식단</div>
                </div>
                <div class="frame-1210">
                  <!-- 리스트가 없을 경우 -->
                  <div
                    v-if="selectedMeals.length === 0"
                    class="empty-selected-message"
                  >
                    선택한 음식이 없습니다.
                  </div>

                  <div
                    class="card-list-all-menu2"
                    v-for="(meal, index) in selectedMeals"
                    :key="index"
                  >
                    <div class="frame-1211">
                      <div class="frame-selected-meal-name">
                        <div class="name5">{{ meal.foodName }}</div>
                        <div class="nutrition-info">
                          <div class="amount">{{ meal.foodSubGroup }}</div>
                          <div class="separator"></div>
                          <div class="amount">{{ meal.portion }}인분</div>
                          <div class="separator"></div>
                          <div class="unit">
                            {{ meal.totalWeight.toFixed(2) }}g
                          </div>
                        </div>
                      </div>

                      <div class="nutrition-info">
                        <div class="info-cal">
                          <img
                            class="icon-special-fire"
                            src="@/assets/images/meal/RegisterMealModal/icon-special-fire0.svg"
                          />
                          <div class="value2">
                            <div class="amount">{{ meal.energy }} kcal</div>
                          </div>
                        </div>
                        <div class="separator"></div>
                        <div class="info-carbs">
                          <img
                            class="icon-special-bread"
                            src="@/assets/images/meal/RegisterMealModal/icon-special-bread0.svg"
                          />
                          <div class="value">
                            <div class="amount">탄수화물</div>
                            <div class="unit">{{ meal.carbohydrate }}g</div>
                          </div>
                        </div>
                        <div class="separator"></div>
                        <div class="info-protein">
                          <img
                            class="icon-special-fish"
                            src="@/assets/images/meal/RegisterMealModal/icon-special-fish0.svg"
                          />
                          <div class="value3">
                            <div class="amount">단백질</div>
                            <div class="unit">{{ meal.protein }}g</div>
                          </div>
                        </div>
                        <div class="separator"></div>
                        <div class="info-fats">
                          <img
                            class="icon-special-drop"
                            src="@/assets/images/meal/RegisterMealModal/icon-special-drop0.svg"
                          />
                          <div class="value">
                            <div class="amount">지방</div>
                            <div class="unit">{{ meal.fat }}g</div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- 삭제 버튼 -->
                    <div class="head-info">
                      <div class="action">
                        <img
                          class="button-picker6"
                          src="@/assets/images/meal/RegisterMealModal/button-picker6.svg"
                          @click="selectedMeals.splice(index, 1)"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- input file 숨김 -->
          <input
            type="file"
            accept="image/*"
            multiple
            ref="fileInputRef"
            style="display: none"
            @change="handleFileChange"
          />

          <div class="frame-1212">
            <!-- 대표 이미지 영역 -->
            <div class="image" @click="triggerFileUpload">
              <img
                class="place-image-here"
                :src="previewUrl || defaultImage"
                alt="미리보기"
              />

              <!-- 이미지 없을 때만 보이는 '사진 추가' 큰 버튼 -->
              <div class="add-image-big" v-if="fileList.length === 0">
                <div class="label5">사진 추가</div>
                <img
                  class="icon-plus"
                  src="@/assets/images/meal/RegisterMealModal/icon-plus0.svg"
                />
              </div>
            </div>

            <!-- 파일 리스트 -->
            <div class="frame-134">
              <!-- 리스트 비었을 때 메시지 -->
              <div v-if="fileList.length === 0" class="empty-photo-message">
                선택된 사진이 없습니다.
              </div>

              <div class="img-list-div" v-if="fileList.length !== 0">
                <!-- 각 이미지 항목 -->
                <div class="img-list">
                  <div
                    class="frame-1213"
                    v-for="(fileWrap, index) in fileList"
                    :key="fileWrap.uid"
                    @click="setAsMainImage(index)"
                    style="cursor: pointer"
                  >
                    <div class="action2" @click.stop="removeFile(index)">
                      <img
                        class="button-picker8"
                        src="@/assets/images/meal/RegisterMealModal/button-picker8.svg"
                      />
                    </div>
                    <div class="value">
                      <div
                        class="img-text"
                        :class="{ 'representative-file': index === 0 }"
                      >
                        {{ fileWrap.file.name }}
                      </div>
                    </div>
                  </div>
                </div>

                <div class="add-image-div">
                  <div
                    class="add-image-small"
                    @click="triggerFileUpload"
                    style="cursor: pointer"
                  >
                    <div class="action2">
                      <img
                        class="button-picker9"
                        src="@/assets/images/meal/RegisterMealModal/icon-plus0.svg"
                      />
                    </div>
                    <div class="value">
                      <div class="amount">사진 추가</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>
