<template>
  <div>
    <HeaderNavbar />
    <div class="post-detail">
      <h2 class="title">{{ post.boardTitle }}</h2>
      <div class="meta">작성자: {{ post.nickname }} | {{ formattedDate }}</div>
      <p class="content">{{ post.boardContent }}</p>
    </div>
  </div>
</template>

<script setup>
import HeaderNavbar from '../components/common/HeaderNavbar.vue'
import { onMounted, ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const post = ref({})
const boardNo = route.params.id

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/boards/${boardNo}`)
    post.value = response.data
  } catch (error) {
    console.error('게시글 조회 실패:', error)
  }
})

const formattedDate = computed(() => {
  return post.value.boardRegDate ? post.value.boardRegDate.slice(0, 10) : ''
})
</script>

<style scoped>
.post-detail {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
  font-family: 'Pretendard', sans-serif;
  background: #fefefe;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 12px;
}

.meta {
  font-size: 14px;
  color: #666;
  margin-bottom: 20px;
}

.content {
  font-size: 16px;
  color: #333;
  white-space: pre-line;
}
</style>