<template>
  <main class="mypage">
    <h1 class="title">마이페이지</h1>
    <p v-if="userInfo" class="user-greeting">
      {{ userInfo.userId }} 님, 환영합니다! ({{ userInfo.userEmail }})
    </p>

    <section class="user-info">
      <h2>🙋‍♂️ 내 정보</h2>
      <router-link to="/mypage/userinfo" class="card-link">
        <div class="card">내 정보</div>
      </router-link>
    </section>

    <section class="user-posts">
      <h2>📝 내가 작성한 글</h2>
      <router-link to="/mypage/posts" class="card-link">
        <div class="card">작성한 게시글 리스트</div>
      </router-link>
    </section>

    <section class="user-comments">
      <h2>💬 내가 작성한 댓글</h2>
      <router-link to="/mypage/comments" class="card-link">
        <div class="card">작성한 댓글 리스트</div>
      </router-link>
    </section>

    <section class="user-challenges">
      <h2>🏆 참여 중인 챌린지</h2>
      <router-link to="/mypage/challenges" class="card-link">
        <div class="card">참여 중인 챌린지 리스트</div>
      </router-link>
    </section>

    <!-- ✅ 자식 라우트 컴포넌트가 여기에 출력됨 -->
    <router-view />
  </main>
</template>

<script setup>
import { useAuthStore } from '@/stores/authStore';
import { computed } from 'vue';

const authStore = useAuthStore();
const userInfo = computed(() => authStore.userInfo);
</script>

<style scoped>
.mypage {
  max-width: 960px;
  margin: 0 auto;
  padding: 2rem;
  font-family: 'Poppins', sans-serif;
}

.title {
  text-align: center;
  font-size: 2.5rem;
  margin-bottom: 2rem;
  color: #333;
}

.user-greeting {
  text-align: center;
  margin-bottom: 2rem;
  font-size: 1.1rem;
  color: #555;
}

section {
  margin-bottom: 2rem;
}

h2 {
  font-size: 1.4rem;
  margin-bottom: 0.8rem;
  color: #444;
}

.card {
  background: #fdfdfd;
  border-radius: 12px;
  padding: 1.5rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: box-shadow 0.2s ease;
  cursor: pointer;
}

.card:hover {
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
}

.card-link {
  text-decoration: none;
  color: inherit;
  display: block;
}
</style>