import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const userNutritionStore = defineStore('nutrition', () => {
  const calorieGoal = ref(0);
  const waterGoal = ref(0);
  const carbGoal = ref(0);
  const proteinGoal = ref(0);
  const fatGoal = ref(0);
  const sodiumGoal = ref(0);
  const sugarGoal = ref(0);

  const setGoals = (data) => {
    calorieGoal.value = data.energy;
    waterGoal.value = data.water;
    carbGoal.value = data.carbohydrate;
    proteinGoal.value = data.protein;
    fatGoal.value = data.totalFattyAcids;
    sodiumGoal.value = data.sodium;
    sugarGoal.value = data.sugar;
  };

  const fetchNutriStandard = async (userNo) => {
    const { data } = await axios.get(
      'http://localhost:8080/api/meal/report/nutriStandard',
      { params: { userNo } }
    );
    setGoals(data); // ✅ 여기서 this 안 써도 됨 (composition API 방식이라면)
  };

  return {
    calorieGoal,
    waterGoal,
    carbGoal,
    proteinGoal,
    fatGoal,
    sodiumGoal,
    sugarGoal,
    setGoals,
    fetchNutriStandard,
  };
});
