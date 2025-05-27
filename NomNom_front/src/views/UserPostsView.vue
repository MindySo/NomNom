<template>
  <div class="section-container">
    <h1>📝 내가 작성한 글</h1>
    <ul>
      <li v-for="post in posts" :key="post.boardNo">{{ post.boardTitle }}</li>
    </ul>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { useAuthStore } from '@/stores/authStore';

const authStore = useAuthStore();
const posts = ref([]);

const fetchUserPosts = async () => {
  try {
    const userNo = authStore.user?.userNo;
    if (!userNo) throw new Error('로그인 정보가 없습니다.');

    const response = await axios.get(`http://localhost:8080/api/boards/user/${userNo}`, {
      headers: {
        Authorization: `Bearer ${authStore.token}`
      }
    });
    posts.value = response.data;
  } catch (error) {
    console.error('게시글 불러오기 실패:', error);
  }
};

onMounted(() => {
  fetchUserPosts();
});
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