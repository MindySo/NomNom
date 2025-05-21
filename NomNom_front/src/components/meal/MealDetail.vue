<template>
<div>
    식단 상세 : {{ regDate }}
    <button class="btn" @click="requestMealList()">목록</button>
</div>
</template>

<script setup>
import {ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';

const router = useRouter();
const route = useRoute();
const regDate = route.params.regDate;

const requestMealList = () => {
    router.push({name: 'mealList'});
};

</script>

<style scoped>

</style>