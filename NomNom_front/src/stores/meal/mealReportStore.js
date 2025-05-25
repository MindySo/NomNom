import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useReportStore = defineStore('report', () => {
  const meals = ref([]);
  const dayReport = ref({});
  const weeklyReport = ref({});
  const monthlyReport = ref({});

  async function fetchMeals(userNo, mealRegDate) {
    const { data } = await axios.get('http://localhost:8080/api/meal', {
      params: { userNo, mealRegDate },
    });
    meals.value = data;
  }

  async function fetchDayReport(userNo, mealRegDate) {
    const { data } = await axios.get(
      'http://localhost:8080/api/meal/report/daily',
      {
        params: { userNo, mealRegDate },
      }
    );
    dayReport.value = data;
  }

  async function fetchWeeklyReport(userNo) {
    const { data } = await axios.get(
      'http://localhost:8080/api/meal/report/weekly',
      {
        params: { userNo },
      }
    );
    weeklyReport.value = data;
  }

  async function fetchMonthlyReport(userNo) {
    const { data } = await axios.get(
      'http://localhost:8080/api/meal/report/monthly',
      {
        params: { userNo },
      }
    );
    monthlyReport.value = data;
  }

  return {
    meals,
    dayReport,
    weeklyReport,
    monthlyReport,
    fetchMeals,
    fetchDayReport,
    fetchWeeklyReport,
    fetchMonthlyReport,
  };
});
