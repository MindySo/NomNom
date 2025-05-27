<template>
  <div>
    <!-- <HeaderNavbar /> -->
    <div class="post-detail">
      <div class="header-row">
        <h2 class="title">[{{ post.boardType }}] {{ post.boardTitle }}</h2>
        <div class="meta">작성자: {{ post.userId }} | {{ formattedDate }}</div>
      </div>
      <div class="recommend-row">
        <div class="extra-info">
          <span>조회수: {{ post.boardViewCnt }}</span> |
          <span>추천수: {{ post.recommendationCnt ?? 0 }}</span>
        </div>
        <div class="recommend-button">
          <button @click="recommendPost">❤️ 추천하기</button>
        </div>
      </div>

      <p class="content">{{ post.boardContent }}</p>

      <div class="comments-section">
        <h3>댓글</h3>
        <ul>
          <li v-for="comment in post.comments ?? []" :key="comment.id">
            <strong>{{ comment.userId }}</strong>: {{ comment.commentContent }}
          </li>
        </ul>
        <div class="comment-form">
          <div class="comment-input-row">
            <textarea v-model="newComment" placeholder="댓글을 입력하세요" rows="3"></textarea>
            <button @click="submitComment">댓글 작성</button>
          </div>
        </div>
      </div>
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

const newComment = ref('')

const submitComment = async () => {
  if (!newComment.value.trim()) return
  try {
    await axios.post(`http://localhost:8080/api/comments`, {
      boardNo,
      commentContent: newComment.value
    }, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('accessToken')}`
      }
    })
    newComment.value = ''
    const commentsRes = await axios.get(`http://localhost:8080/api/comments/board/${boardNo}`)
    post.value.comments = commentsRes.data
  } catch (error) {
    console.error('댓글 작성 실패:', error)
  }
}

const recommendPost = async () => {
  try {
    await axios.post(`http://localhost:8080/api/boards/${boardNo}/reactions`, null, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('accessToken')}`
      }
    })
    const reactionRes = await axios.get(`http://localhost:8080/api/boards/${boardNo}/reactions/count`)
    post.value.recommendationCnt = reactionRes.data.count
  } catch (error) {
    console.error('추천 실패:', error)
  }
}
</script>

<style scoped>
.post-detail {
  max-width: 1000px;
  margin: 40px auto;
  padding: 20px;
  font-family: 'Pretendard', sans-serif;
  background: #fefefe;
  border: 1px solid #ddd;
  border-radius: 8px;
}

/* Header row flex container */
.header-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  margin-bottom: 12px;
}

.header-row .title {
  margin-bottom: 0;
}

.header-row .meta {
  text-align: right;
  white-space: nowrap;
  padding-top: 6px; /* pushes it downward */
}

.title {
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 12px;
}

.meta {
  font-size: 17px;
  color: #666;
  margin-bottom: 20px;
}

.extra-info {
  font-size: 16px;
  color: #888;
  margin-bottom: 20px;
}

.content {
  font-size: 18px;
  color: #333;
  white-space: pre-line;
}

.comments-section {
  margin-top: 40px;
  border-top: 1px solid #ccc;
  padding-top: 20px;
}

.comments-section h3 {
  font-size: 21px;
  margin-bottom: 10px;
}

.comments-section ul {
  list-style: none;
  padding: 0;
}

.comments-section li {
  margin-bottom: 8px;
  font-size: 16px;
}

.comment-form {
  margin-top: 20px;
}

.comment-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 15px;
  border-radius: 4px;
  border: 1px solid #ccc;
  resize: vertical;
}

.comment-form button {
  margin-top: 8px;
  padding: 6px 12px;
  background-color: #73a107;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.comment-form button:hover {
  background-color: #5d8806;
}

.recommend-button {
  margin-bottom: 20px;
}

.recommend-button button {
  padding: 6px 12px;
  background-color: #ff69b4;
  color: white;
  border: none;
  border-radius: 4px;
  font-weight: bold;
  cursor: pointer;
}

.recommend-button button:hover {
  background-color: #ff4fa3;
}

.recommend-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
</style>

.comment-input-row {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 10px;
}

.comment-input-row textarea {
  flex: 1;
}

.comment-input-row button {
  white-space: nowrap;
  height: fit-content;
}