import { createRouter, createWebHistory } from "vue-router";

import MainView from "../views/MainView.vue";
import SidebarView from "@/views/SidebarView.vue";

import MealView from "../views/MealView.vue";
import MealList from "../components/meal/MealList.vue";
import MealDetailModal from "../components/meal/MealDetailModal.vue";

import ChallengeView from "../views/ChallengeView.vue";
import ChallengeList from "../components/challenge/ChallengeList.vue";
import ChallengeDetail from "../components/challenge/ChallengeDetail.vue";
import MyChallenge from "../components/challenge/MyChallenge.vue";
import ChallengeCal from "../components/challenge/ChallengeCal.vue";
import ChallengeMain from "../components/challenge/ChallengeMain.vue";

import CommunityView from "@/views/CommunityView.vue";
import BoardDetailView from "@/views/BoardDetailView.vue";

import MypageView from "../views/MypageView.vue";
import UserInfoView from "../views/UserInfoView.vue";
import UserPostsView from "../views/UserPostsView.vue";
import UserCommentsView from "../views/UserCommentsView.vue";
import UserChallengesView from "../views/UserChallengesView.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/main",
    },
    {
      path: "/main",
      name: "main",
      component: MainView,
    },
    {
      path: "/sidebar",
      name: "sidebar",
      component: SidebarView,
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/views/LoginView.vue"),
    },
    {
      path: "/signup",
      name: "signup",
      component: () => import("@/views/SignupView.vue"),
    },
    {
      path: "/signup/profile",
      name: "SignupProfile",
      component: () => import("@/views/SignupProfileView.vue"),
    },
    {
      path: "/signup/completed",
      name: "SignupCompleted",
      component: () => import("@/views/SignupCompletedView.vue"),
    },
    {
      path: "/meal",
      name: "meal",
      component: MealView,
      children: [
        {
          path: "",
          name: "mealList",
          component: MealList,
        },
        {
          path: "detail/:mealNo",
          name: "mealDetailModal",
          component: MealDetailModal,
        },
      ],
    },
    {
      path: "/challenge",
      name: "challenge",
      component: ChallengeMain,
      children: [
        {
          path: "",
          name: "challengeMain",
          component: ChallengeMain,
        },
        {
          path: "",
          name: "challengeList",
          component: ChallengeList,
        },
        {
          path: "detail/:id",
          name: "challengeDetail",
          component: ChallengeDetail,
        },
        {
          path: "myChallenge",
          name: "myChallenge",
          component: MyChallenge,
        },
        {
          path: "challengeCal",
          name: "challengeCal",
          component: ChallengeCal,
        },
      ],
    },
    {
      path: '/community',
      name: 'community',
      component: CommunityView
    },
    {
      path: '/community/write',
      name: 'BoardWrite',
      component: () => import('@/views/BoardWriteView.vue')
    },
    {
      path: '/community/detail/:id',
      name: 'postDetail',
      component: () => import('@/views/BoardDetailView.vue')
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MypageView,
    },
    {
      path: "/mypage/userinfo",
      name: "UserInfo",
      component: UserInfoView,
    },
    {
      path: "/mypage/posts",
      name: "UserPosts",
      component: UserPostsView,
    },
    {
      path: "/mypage/comments",
      name: "UserComments",
      component: UserCommentsView,
    },
    {
      path: "/mypage/challenges",
      name: "UserChallenges",
      component: UserChallengesView,
    },
  ],
});
// router.beforeEach
export default router;