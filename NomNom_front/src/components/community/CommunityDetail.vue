<template>
<div>
    글 상세 : {{ id }}
    <button class="btn" @click="requestBoardList">목록</button>
</div>
</template>

<script setup>
import {ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';

const router = useRouter();
const route = useRoute();
const id = route.params.id;

const requestBoardList = () => {
    router.push({name: 'communityList'});
};
</script>

<style scoped>

</style>