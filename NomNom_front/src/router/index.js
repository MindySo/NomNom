import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/MainView.vue';
import MealView from '../views/MealView.vue';
import MealList from '../components/meal/MealList.vue';
import MealDetailModal from '../components/meal/MealDetailModal.vue';

import ChallengeView from '../views/ChallengeView.vue';
import ChallengeList from '../components/challenge/ChallengeList.vue';
import ChallengeDetail from '../components/challenge/ChallengeDetail.vue';

import CommunityView from '../views/CommunityView.vue';
import CommunityList from '../components/community/CommunityList.vue';
import CommunityDetail from '../components/community/CommunityDetail.vue';

import MypageView from '../views/ChallengeView.vue';
import MypageList from '../components/mypage/MypageList.vue';
import MypageChallenge from '../components/mypage/MypageChallenge.vue';
import MypagePointlog from '../components/mypage/MypagePointlog.vue';
import MypageUserinfo from '../components/mypage/MypageUserinfo.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/main',
      name: 'main',
      component: MainView,
    },
    {
      path: '/meal',
      name: 'meal',
      component: MealView,
      children: [
        {
          path: '',
          name: 'mealList',
          component: MealList,
        },
        {
          path: 'detail/:regDate',
          name: 'mealDetailModal',
          component: MealDetailModal,
        },
      ],
    },
    {
      path: '/challenge',
      name: 'challenge',
      component: ChallengeView,
      children: [
        {
          path: '',
          name: 'challengeList',
          component: ChallengeList,
        },
        {
          path: 'detail/:id',
          name: 'challengeDetail',
          component: ChallengeDetail,
        },
      ],
    },
    {
      path: '/community',
      name: 'community',
      component: CommunityView,
      children: [
        {
          path: '',
          name: 'communityList',
          component: CommunityList,
        },
        {
          path: 'detail/:id',
          name: 'communityDetail',
          component: CommunityDetail,
        },
      ],
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
      children: [
        {
          path: '',
          name: 'mypageList',
          component: MypageList,
        },
        {
          path: '',
          name: 'mypageChallenge',
          component: MypageChallenge,
        },
        {
          path: 'detail/',
          name: 'mypagePointlog',
          component: MypagePointlog,
        },
        {
          path: 'detail/',
          name: 'mypageUserinfo',
          component: MypageUserinfo,
        },
      ],
    },
  ],
});
// router.beforeEach
export default router;
