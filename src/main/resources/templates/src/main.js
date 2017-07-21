// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue';
import Icon from 'vue-awesome/components/Icon'
import Vuelidate from 'vuelidate'
import VueResource from 'vue-resource'
import Auth from 'vue-token'
// import config from '../config';
import 'vue-awesome/icons'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import basicAuthToken from 'basic-auth-token';

Vue.component('icon', Icon);

Vue.use(basicAuthToken)
Vue.config.productionTip = false

Vue.use(BootstrapVue);
Vue.use(Vuelidate)
Vue.use(VueResource);
Vue.use(Auth, {
	loginUrl:`${process.env["API_URL"]}/login`,
	signUpUrl: "/api/users",
	logoutUrl: "/api/logout",
	refresh: false
})

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App},
})
// Vue.http.interceptors.push((request, next) => {
// 	const token = localStorage.getItem('token');
// 	console.log("TOKEN", token);
// 	if (token) {
// 		request.headers.set('Authorization', `Basic ${token}`);
// 		request.headers.set('Accept', 'application/json');
// 	}
//   next()
// });
