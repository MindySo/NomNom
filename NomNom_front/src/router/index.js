import { createRouter, createWebHistory } from 'vue-router'

import MainView from '@/views/MainView.vue'
import SidebarView from '@/views/SidebarView.vue'
import MealView from '@/views/MealView.vue'
import MealList from '@/components/meal/MealList.vue'
import MealDetail from '@/components/meal/MealDetail.vue'

import ChallengeView from '@/views/ChallengeView.vue'
import ChallengeList from '@/components/challenge/ChallengeList.vue'
import ChallengeDetail from '@/components/challenge/ChallengeDetail.vue'

import CommunityView from '@/views/CommunityView.vue'
import BoardDetailView from '@/views/BoardDetailView.vue'

import MypageView from '@/views/MypageView.vue'
import MypageList from '@/components/mypage/MypageList.vue'
import MypageChallenge from '@/components/mypage/MypageChallenge.vue'
import MypagePointlog from '@/components/mypage/MypagePointlog.vue'
import MypageUserinfo from '@/components/mypage/MypageUserinfo.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/main'
    },
    {
      path: '/main',
      name: 'main',
      component: MainView
    },
    {
      path: '/sidebar',
      name: 'sidebar',
      component: SidebarView
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/LoginView.vue')
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('@/views/SignupView.vue')
    },
    {
      path: '/signup/profile',
      name: 'SignupProfile',
      component: () => import('@/views/SignupProfileView.vue')
    },
    {
      path: '/signup/completed',
      name: 'SignupCompleted',
      component: () => import('@/views/SignupCompletedView.vue')
    },
    {
      path: '/meal',
      name: 'meal',
      component: MealView,
      children: [
        {
          path: '',
          name: 'mealList',
          component: MealList
        },
        {
          path: 'detail/:regDate',
          name: 'mealDetail',
          component: MealDetail
        }
      ]
    },
    {
      path: '/challenge',
      name: 'challenge',
      component: ChallengeView,
      children: [
        {
          path: '',
          name: 'challengeList',
          component: ChallengeList
        },
        {
          path: 'detail/:id',
          name: 'challengeDetail',
          component: ChallengeDetail
        }
      ]
    },
    {
      path: '/community',
      name: 'community',
      component: CommunityView,
    },
    {
      path: '/community/detail/:id',
      name: 'postDetail',
      component: BoardDetailView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
      children: [
        {
          path: '',
          name: 'mypageList',
          component: MypageList
        },
        {
          path: 'challenge',
          name: 'mypageChallenge',
          component: MypageChallenge
        },
        {
          path: 'pointlog',
          name: 'mypagePointlog',
          component: MypagePointlog
        },
        {
          path: 'userinfo',
          name: 'mypageUserinfo',
          component: MypageUserinfo
        }
      ]
    }
  ]
})

export default router
