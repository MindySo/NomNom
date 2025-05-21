<template>
<div id="list">
    <div class="board-header">
        <h1 class="board-title">식단 목록</h1>
        <RouterLink :to="{name: 'mealDetail', params: {regDate: '2025-05-18'}}">식단 상세</RouterLink>
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