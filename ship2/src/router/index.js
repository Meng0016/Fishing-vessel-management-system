import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
import Manage from '../views/Manage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Manage,
    redirect: "/home",
    children:[
      {path:'home' , name: '首页', component :()=>import('@/views/Home.vue')},
      {path:'user' , name: '系统管理 / 用户管理',component :()=>import('@/views/User.vue')},
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  console.log(from)
  localStorage.setItem('currentPathName',to.name)
  store.commit("setPath")  // 触发store的数据更新
  next()
})
export default router
