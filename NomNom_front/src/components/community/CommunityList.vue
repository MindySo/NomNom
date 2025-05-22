<template>
<div id="list">
    <div class="board-header">
        <h1 class="board-title">커뮤니티 목록</h1>
        <RouterLink :to="{name: 'communityDetail', params: {id: '123'}}">글 상세</RouterLink>
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