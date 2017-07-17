// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue';
import Icon from 'vue-awesome/components/Icon'
import Vuelidate from 'vuelidate'
import VueResource from 'vue-resource'

import 'vue-awesome/icons'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(BootstrapVue);
Vue.component('icon', Icon);



Vue.config.productionTip = false
Vue.use(Vuelidate)
Vue.use(VueResource);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App},
  mounted(){
    this.$http.get("localhost:9999", response =>{
      console.log("success", response)
    }, response => {
      console.log("error", response)
    })
  }
  //  http: {
  //   root: '/root',
  //   headers: {
  //     Authorization: 'Basic YXBpOnBhc3N3b3Jk'
  //   }
  // }

})

// Vue.http.options.root = '/root';

