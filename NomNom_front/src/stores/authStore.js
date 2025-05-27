import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isLoggedIn: false,
    accessToken: null,
    userNo: null,
    userInfo: null,
  }),
  actions: {
    setLogin(token, userNo = null, userInfo = null) {
      this.isLoggedIn = true;
      this.accessToken = token;
      this.userNo = userNo;
      this.userInfo = userInfo;
      sessionStorage.setItem('accessToken', token);
      if (userInfo) {
        sessionStorage.setItem('user', JSON.stringify(userInfo));
      }
      console.log(this.isLoggedIn + ', ' + userNo);
    },
    logout() {
      this.isLoggedIn = false;
      this.accessToken = null;
      this.userNo = null;
      this.userInfo = null;
      sessionStorage.removeItem('accessToken');
      sessionStorage.removeItem('user');
    },
    checkAuth() {
      const token = sessionStorage.getItem('accessToken');
      const user = sessionStorage.getItem('user');
      if (token) {
        this.isLoggedIn = true;
        this.accessToken = token;
        this.userInfo = user ? JSON.parse(user) : null;
      } else {
        this.isLoggedIn = false;
        this.accessToken = null;
        this.userInfo = null;
      }
    },
  },
});
