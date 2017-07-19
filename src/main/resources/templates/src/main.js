// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue';
import Icon from 'vue-awesome/components/Icon'
import Vuelidate from 'vuelidate'
import VueResource from 'vue-resource'
import Axios from 'axios'
// import config from '../config';
import 'vue-awesome/icons'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.component('icon', Icon);

Vue.use(BootstrapVue);
Vue.use(Vuelidate)
Vue.use(VueResource);

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App},
})
