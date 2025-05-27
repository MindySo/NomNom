<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();

import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import koLocale from '@fullcalendar/core/locales/ko';

const events = [
  {
    title: '하루짜리',
    start: '2025-05-28',
    end: '2025-05-30',
  },
  {
    title: '여러날짜 이벤트',
    start: '2025-05-29',
    end: '2025-06-02',
  },
];

const customKo = {
  ...koLocale,
  dayHeaderFormat: { weekday: 'short' }, // '일', '월' 같은 요일만
  dayPopoverFormat: { month: 'numeric', day: 'numeric' }, // '5.25'
};

const calendarOptions = {
  plugins: [dayGridPlugin],
  locale: customKo,
  initialView: 'dayGridMonth',
  events: events,
  titleFormat: { year: 'numeric', month: 'long' },
  // ✅ 날짜 셀 숫자에서 '일' 제거!
  dayCellContent: function (arg) {
    return { html: String(arg.date.getDate()) }; // 👉 숫자만 출력
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
@import '@/assets/css/vars.css';
@import '@/assets/css/challenge/ChallengeCal.css';
</style>

<style scoped>
@import '@/assets/css/challenge/MyChallenge.css';
</style>

<template>
  <div class="temp">
    <FullCalendar :options="calendarOptions" />
  </div>
</template>
