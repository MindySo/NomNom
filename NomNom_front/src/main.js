import './assets/style.css';
// import './assets/css/auth/SignupCompleted.css';
// import './assets/css/vars.css';


import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);

app.mount('#app');
