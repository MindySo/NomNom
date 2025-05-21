<template>
<div>
    챌린지 상세 : {{ id }}
    <button class="btn" @click="requestChallengeList">목록</button>
</div>
</template>

<script setup>
import {ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';

const router = useRouter();
const route = useRoute();

const regDate = route.params.regDate;
const board = ref({})

const requestChallengeList = () => {
    router.push({name: 'challengeList'});
};

</script>

<style scoped>

</style>