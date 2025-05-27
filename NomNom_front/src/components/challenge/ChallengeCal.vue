<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import dayjs from 'dayjs';
const router = useRouter();

import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import koLocale from '@fullcalendar/core/locales/ko';

const calendarEvents = ref([]);

const fetchEvents = async () => {
  try {
    const response = await axios.get(
      'http://localhost:8080/api/mission/challenge/all',
      {
        params: { userNo: 1 },
      }
    );

    const challengeList = response.data;

    const events = challengeList.map((challenge) => {
      const {
        challengeNo,
        missionName,
        challengeStartDate,
        challengeCurrentStreak,
        challengeStatus,
        missionColor,
      } = challenge;

      // 종료일 계산 (시작일 + 진행일수 - 1)
      const endDate = dayjs(challengeStartDate)
        .add(challengeCurrentStreak - 1, 'day')
        .format('YYYY-MM-DD');

      // 색상 설정
      let backgroundColor;
      if (challengeStatus === 'COMPLETED') {
        backgroundColor = '#c2e66e'; // 초록색
      } else if (challengeStatus === 'IN_PROGRESS') {
        backgroundColor = '#FFCB65'; // 노란색
      } else {
        backgroundColor = '#c7c7c7'; // 진한 회색
      }

      return {
        id: challengeNo, // FullCalendar 고유 식별자
        title: missionName,
        start: challengeStartDate,
        end: dayjs(endDate).add(1, 'day').format('YYYY-MM-DD'), // FullCalendar는 end 날짜는 exclusive
        color: backgroundColor,
        extendedProps: {
          challengeNo,
          challengeStatus,
        },
        allDay: true,
      };
    });

    console.log(events);

    return events;
  } catch (error) {
    console.error('챌린지 이벤트 불러오기 실패:', error);
    return [];
  }
};

onMounted(async () => {
  calendarEvents.value = await fetchEvents();
});

const customKo = {
  ...koLocale,
  dayHeaderFormat: { weekday: 'short' }, // '일', '월' 같은 요일만
  dayPopoverFormat: { month: 'numeric', day: 'numeric' }, // '5.25'
};

const calendarOptions = ref({
  plugins: [dayGridPlugin],
  locale: customKo,
  initialView: 'dayGridMonth',
  events: [],
  eventDisplay: 'block',
  titleFormat: { year: 'numeric', month: 'long' },
  dayCellContent(arg) {
    return { html: String(arg.date.getDate()) };
  },
});

watchEffect(() => {
  calendarOptions.value.events = calendarEvents.value;
});

const calendarRef = ref(null);
const originalColorMap = new Map();
const previousHighlighted = ref(null);

function highlightChallengeEvent(challengeNo) {
  const calendarApi = calendarRef.value.getApi();

  if (previousHighlighted.value) {
    const prevEvent = calendarApi.getEventById(previousHighlighted.value);
    const originalColor = originalColorMap.get(previousHighlighted.value);
    if (prevEvent && originalColor) {
      prevEvent.setProp('color', originalColor);
    }
    previousHighlighted.value = null;
  }

  const event = calendarApi.getEventById(challengeNo);
  if (event) {
    if (!originalColorMap.has(challengeNo)) {
      originalColorMap.set(challengeNo, event.backgroundColor || '#999999');
    }

    const status = event.extendedProps.challengeStatus;
    const darkerColor = getDarkerColor(status);

    event.setProp('color', darkerColor);
    previousHighlighted.value = challengeNo;
  }
}

function getDarkerColor(challengeStatus) {
  // if (challengeStatus === "COMPLETED") {
  //   return "#78ab00"; // #c2e66e보다 더 어두운 연두
  // } else if (challengeStatus === "IN_PROGRESS") {
  //   return "#eb9c00"; // #FFCB65보다 더 진한 주황
  // } else {
  //   return "#707070"; // #A0A0A0보다 더 어두운 회색
  // }
  return '#ff3f3f';
}

defineExpose({
  highlightChallengeEvent,
});
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
    <FullCalendar ref="calendarRef" :options="calendarOptions" />
  </div>
</template>
