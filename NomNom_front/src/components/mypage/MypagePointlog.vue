<template>
<div>
    포인트 적립 내역
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