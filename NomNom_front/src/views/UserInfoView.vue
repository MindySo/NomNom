<template>
  <div class="section-container" v-if="user">
    <h1>🙋‍♂️ 내 정보</h1>
    <p><strong>이름:</strong> {{ user.userId }}</p>
    <p><strong>이메일:</strong> {{ user.userEmail }}</p>
    <p><strong>생년월일:</strong> {{ user.userBirthday }}</p>
    <p><strong>키:</strong> {{ user.userHeight ?? '' }} <span v-if="user.userHeight">cm</span></p>
    <p><strong>몸무게:</strong> {{ user.userWeight ?? '' }} <span v-if="user.userWeight">kg</span></p>
    <p><strong>알러지:</strong> 
      <span v-if="user.allergyIds?.length > 0">
        {{ user.allergyIds.join(', ') }}
      </span>
      <span v-else>없음</span>
    </p>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/authStore';
import { computed } from 'vue';

const authStore = useAuthStore();
const user = computed(() => authStore.userInfo);
</script>

<style scoped>
.section-container {
  padding: 2rem;
  max-width: 800px;
  margin: 5rem auto 0 auto;
  background: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}
h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
}
p {
  font-size: 1.1rem;
  margin-bottom: 0.8rem;
}
</style>