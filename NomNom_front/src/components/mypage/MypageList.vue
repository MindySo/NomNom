<template>
<div id="list">
    <div class="board-header">
        <h1 class="board-title">마이페이지</h1>
        <RouterLink :to="{name: 'mypageUserinfo'}">유저 정보</RouterLink>
        <RouterLink :to="{name: 'mypagePointlog'}">포인트 적립 내역</RouterLink>
        <RouterLink :to="{name: 'mypageChallenge'}">진행 중인 챌린지</RouterLink>
    </div>
    
</div>
</template>

<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';

const router = useRouter();

</script>

<style scoped>

</style>