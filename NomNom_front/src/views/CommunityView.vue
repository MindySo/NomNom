<template>
  <div class="community-page">
    <div class="content-layout">
      <SidebarView />
      <div class="main-content">
        <div class="community-container">
          <div class="community-header">
            <div class="search-bar">
              <input v-model="searchQuery" placeholder="검색어를 입력하세요" />
              <button @click="handleSearch">검색</button>
            </div>
            <div class="write-button">
              <button @click="goToWrite">
                <img src="@/assets/images/community/icon-plus.svg" alt="글쓰기" class="write-icon" />
                글쓰기
              </button>
            </div>
          </div>
          <div class="post-list">
            <div v-for="post in filteredPosts" :key="post.id" class="post-item">
              <router-link :to="`/community/detail/${post.boardNo}`" class="post-title-link">
                {{ post.boardTitle }}
              </router-link>
              <p class="content">{{ post.boardContent }}</p>
              <div class="meta">작성자: {{ post.userNo }} | {{ post.boardRegDate.slice(0, 10) }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import HeaderNavbar from "../components/common/HeaderNavbar.vue";
import SidebarView from "@/views/SidebarView.vue";
import { ref, computed, onMounted, watch } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();

const searchQuery = ref("");
const posts = ref([]);

const boardType = computed(() => route.query.boardType || "");

const fetchPosts = async () => {
  try {
    if (boardType.value) {
      const response = await axios.get(`http://localhost:8080/api/boards/category?boardType=${boardType.value}`);
      posts.value = response.data;
    } else {
      const response = await axios.get("http://localhost:8080/api/boards");
      posts.value = response.data;
    }
  } catch (error) {
    console.error("게시글 불러오기 실패:", error);
  }
};

onMounted(fetchPosts);
watch(() => route.query.boardType, fetchPosts);

const filteredPosts = computed(() => posts.value.filter((post) => post.boardTitle.includes(searchQuery.value) || post.boardContent.includes(searchQuery.value)));

const handleSearch = () => {
  console.log("검색:", searchQuery.value);
};

const goToWrite = () => router.push("/community/write");
</script>

<style scoped>
.community-page {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.content-layout {
  display: flex;
  flex: 1;
}

.main-content {
  flex: 1;
}

.community-container {
  max-width: 1080px;
  margin: 0 auto;
  padding: 20px;
  font-family: "Pretendard", sans-serif;
}

.community-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  gap: 10px;
}

.search-bar input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  flex: none;
  width: 320px;
  max-width: 100%;
}

.search-bar button {
  padding: 8px 16px;
  background-color: #73a107;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.write-button button {
  padding: 8px 16px;
  background-color: #c2e66e;
  color: #333;
  border: none;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
}

.post-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.post-item {
  padding: 16px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  background-color: #fafafa;
}

.post-item h3 {
  margin: 0 0 8px;
  color: #333;
}

.post-item .content {
  color: #555;
  margin-bottom: 8px;
}

.post-item .meta {
  font-size: 12px;
  color: #888;
  text-align: right;
  display: block;
}

.post-title-link {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  text-decoration: none;
  cursor: pointer;
}
</style>

.write-icon { width: 16px; height: 16px; margin-right: 8px; vertical-align: middle; }
