import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Recover from '@/components/Recover'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Dashboard from '@/components/Dashboard'


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
      name: 'Recover',
      component: Recover

    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {   
      path: '/register',
      name: 'Register',
      component: Register
    },
    {   
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard
    },
  ]
})
