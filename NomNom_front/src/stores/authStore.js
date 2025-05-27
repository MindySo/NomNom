import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isLoggedIn: false,
    accessToken: null,
    userNo: null, // 필요 시 사용자 정보도 추가
  }),
  actions: {
    setLogin(token, userNo = null) {
      this.isLoggedIn = true;
      this.accessToken = token;
      this.userNo = userNo;
    },
    logout() {
      this.isLoggedIn = false;
      this.accessToken = null;
      this.userNo = null;
      sessionStorage.removeItem("accessToken");
    },
    checkAuth() {
      const token = sessionStorage.getItem("accessToken");
      if (token) {
        this.isLoggedIn = true;
        this.accessToken = token;
      } else {
        this.isLoggedIn = false;
        this.accessToken = null;
      }
    },
  },
});
