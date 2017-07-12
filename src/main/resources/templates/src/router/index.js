import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Recover from '@/components/Recover'
import Login from '@/components/Login'
import Register from '@/components/Register'



Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
        {
      path: '/recover',
      name: 'Recovers',
      component: Recover

    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
<<<<<<< Updated upstream
    },
    {   
      path: '/register',
      name: 'Register',
      component: Register
=======

>>>>>>> Stashed changes
    }
  ]
})
