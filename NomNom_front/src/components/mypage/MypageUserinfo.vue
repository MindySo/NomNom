<template>
<div>
    유저 정보
    <button class="btn" @click="requestMypageList()">목록</button>
</div>
</template>

<script setup>
import {ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';

const router = useRouter();
const route = useRoute();

const requestMypageList = () => {
    router.push({name: 'mypageList'});
};

</script>

<style scoped>

</style>