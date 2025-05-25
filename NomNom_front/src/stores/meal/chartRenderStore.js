import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

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

export const useChartRender = defineStore('chartRender', () => {
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

  // 주간 : 그룹 바
  function drawWeeklyChart(canvas, dailySumList) {
    const ctx = canvas.value;
    if (!ctx) return;

    ctx.width = ctx.offsetWidth;
    ctx.height = 150;

    new Chart(ctx, {
      type: 'bar',
      data: createGroupBarData(dailySumList, 'weekly'),
      options: groupChartoptions,
    });
  }

  // 월간 : 그룹 바
  function drawMonthlyChart(canvas, dailySumList) {
    const ctx = canvas;
    if (!ctx) return;

    ctx.width = ctx.offsetWidth;
    ctx.height = 150;

    new Chart(ctx, {
      type: 'bar',
      data: createGroupBarData(dailySumList, 'monthly'),
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

  // 그룹화된 바 그래프
  function createGroupBarData(valueList, type) {
    let labels = '';
    if (type === 'weekly') {
      labels = valueList.map((item) => item.reportDate);
    } else if (type === 'monthly') {
      const weekdayMap = {
        Monday: '월',
        Tuesday: '화',
        Wednesday: '수',
        Thursday: '목',
        Friday: '금',
        Saturday: '토',
        Sunday: '일',
      };

      labels = valueList.map(
        (item) => weekdayMap[item.reportWeekday] || item.reportWeekday
      );
    }

    const maxGoals = {
      carbohydrate: 400,
      protein: 60,
      fat: 50,
      water: 2000,
    };

    const carbData = valueList.map((item) =>
      Math.min(100, (item.carbohydrate / maxGoals.carbohydrate) * 100)
    );
    const proteinData = valueList.map((item) =>
      Math.min(100, (item.protein / maxGoals.protein) * 100)
    );
    const fatData = valueList.map((item) =>
      Math.min(100, (item.totalFattyAcids / maxGoals.fat) * 100)
    );
    const waterData = valueList.map((item) =>
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
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        {
          label: '단백질',
          data: proteinData,
          stack: 'protein',
          backgroundColor: '#ffa751',
          borderRadius: 2,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        {
          label: '지방',
          data: fatData,
          stack: 'fat',
          backgroundColor: '#f9d423',
          borderRadius: 2,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        {
          label: '수분',
          data: waterData,
          stack: 'water',
          backgroundColor: '#69b7f0',
          borderRadius: 2,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        // 남은 양 (회색)
        {
          label: '남은 탄수화물',
          data: carbData.map((v) => 100 - v),
          stack: 'carb',
          backgroundColor: gray,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        {
          label: '남은 단백질',
          data: proteinData.map((v) => 100 - v),
          stack: 'protein',
          backgroundColor: gray,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        {
          label: '남은 지방',
          data: fatData.map((v) => 100 - v),
          stack: 'fat',
          backgroundColor: gray,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
        {
          label: '남은 수분',
          data: waterData.map((v) => 100 - v),
          stack: 'water',
          backgroundColor: gray,
          categoryPercentage: 0.6,
          barPercentage: 0.3,
        },
      ],
    };
  }

  // 도넛 그래프 중앙에 글씨
  const centerTextPlugin = (val) => ({
    id: 'centerText',
    afterDraw(chart) {
      const { width, height, ctx } = chart;
      ctx.save();
      const text = `${val}`;
      ctx.font = 'bold 14px Arial';
      ctx.textBaseline = 'middle';
      ctx.textAlign = 'center';
      ctx.fillStyle = '#1c1c1c';
      ctx.fillText(text, width / 2, height / 2);
      ctx.restore();
    },
  });

  // 도넛 그래프 그리기
  function drawDoughnutCharts(chart, value, unit, valueGoal, color) {
    const canvas = chart;
    canvas.height = 150;
    new Chart(canvas, {
      type: 'doughnut',
      data: createDoughnutData(value, valueGoal, color),
      options: {
        plugins: {
          tooltip: { enabled: false },
          legend: { display: false },
        },
        cutout: '70%',
      },
      plugins: [centerTextPlugin(value + unit)],
    });
  }

  // 바 그래프 그리기(차트, "영양소", 값, 목표값, 색상)
  function drawBarCharts(chart, label, value, valueGoal, color) {
    const canvas = chart;
    canvas.height = 8;

    new Chart(canvas, {
      type: 'bar',
      data: createBarData(label, value, valueGoal, color),
      options: {
        responsive: false,
        maintainAspectRatio: false,
        indexAxis: 'y',
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

  return {
    createDoughnutData,
    createBarData,
    drawWeeklyChart,
    drawMonthlyChart,
    groupChartoptions,
    createGroupBarData,
    centerTextPlugin,
    drawDoughnutCharts,
    drawBarCharts,
  };
});
