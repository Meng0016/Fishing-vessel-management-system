import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
import Manage from '../views/Manage.vue'

Vue.use(VueRouter)

//解决vue路由重复导航错误
//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const routes = [
  {
    path: '/',
    component: Manage,
    redirect: "/home",
    children:[
      {path:'home' , name: '首页', component :()=>import('@/views/Home.vue')},
      {path:'user' , name: '系统管理 / 用户管理',component :()=>import('@/views/User.vue')},
      {path:'person' , name: '系统管理 / 个人信息',component :()=>import('@/views/Person.vue')},
      {path:'ship',name:'系统管理 / 船舶信息',component:()=>import('@/views/Ship.vue')},
      {path:'role',name:'系统管理 / 权限管理',component:()=>import('@/views/Role.vue')},
      {path:'certificate',name:'系统管理 / 证书管理',component:()=>import('@/views/Certificate.vue')},
      {path: 'fileupload',name: '系统管理 / 文件管理',component:()=>import('@/views/FileUP.vue')}
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import('@/views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import('@/views/Register.vue')
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
