<template>
<div id="list">
    <div class="board-header">
        <h1 class="board-title">챌린지 목록</h1>
        <RouterLink :to="{name: 'challengeDetail', params: {id: 123}}">챌린지 상세</RouterLink>
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