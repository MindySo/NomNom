<template>
  <div>
    <div class="post-detail">
      <div class="header-row">
        <h2 class="title">게시글 작성</h2>
        <div class="meta">작성자: {{ userNickname }}</div>
      </div>

      <div class="form-group">
        <label for="boardTitle">제목</label>
        <input type="text" id="boardTitle" v-model="boardTitle" class="form-input" />
      </div>

      <div class="form-group">
        <label for="boardType">게시판 타입</label>
        <select id="boardType" v-model="boardType" class="form-input">
          <option value="FREE">자유게시판</option>
          <option value="TIP">식단 꿀팁</option>
          <option value="REVIEW">챌린지 후기</option>
        </select>
      </div>

      <div class="form-group">
        <label for="boardContent">내용</label>
        <textarea id="boardContent" v-model="boardContent" rows="10" class="form-input"></textarea>
      </div>

      <div class="recommend-button">
        <button @click="submitPost">등록하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const boardTitle = ref('')
const boardType = ref('FREE')
const boardContent = ref('')
const storedUser = sessionStorage.getItem('user')
const parsedUser = storedUser ? JSON.parse(storedUser) : null
const userNickname = parsedUser?.nickname || '로그인한 사용자'
const router = useRouter()

const submitPost = async () => {
  const accessToken = sessionStorage.getItem('accessToken')
  if (!accessToken) {
    alert('로그인이 필요합니다.')
    return
  }

  try {
    const formData = new FormData()
    const board = {
      boardTitle: boardTitle.value,
      boardType: boardType.value,
      boardContent: boardContent.value,
    }

    formData.append('board', new Blob([JSON.stringify(board)], { type: 'application/json' }))

    const response = await axios.post('http://localhost:8080/api/boards', formData, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    })

    alert('게시글이 등록되었습니다.')

    const newBoardNo = response.data.boardNo
    if (newBoardNo) {
      router.push(`/community/detail/${newBoardNo}`)
    } else {
      router.push('/community')
    }
  } catch (error) {
    console.error('게시글 등록 실패:', error)
    alert('게시글 등록에 실패했습니다.')
  }
}
</script>

<style scoped>
.post-detail {
  max-width: 1000px;
  margin: 80px auto;
  padding: 40px 20px;
  font-family: 'Pretendard', sans-serif;
  background: #fefefe;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.header-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  margin-bottom: 12px;
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

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  font-weight: bold;
  display: block;
  margin-bottom: 8px;
}

.form-input {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

textarea.form-input {
  resize: vertical;
}

.recommend-button button {
  padding: 6px 12px;
  background-color: #73a107;
  color: white;
  border: none;
  border-radius: 4px;
  font-weight: bold;
  cursor: pointer;
}

.recommend-button button:hover {
  background-color: #5d8806;
}
</style>