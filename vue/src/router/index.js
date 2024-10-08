import Vue from 'vue'
import VueRouter from 'vue-router'

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',  // 重定向到主页
    children: [
      { path: '403', name: 'Auth', meta: { name: 'No Access' }, component: () => import('../views/manager/Auth') },
      { path: 'home', name: 'Home', meta: { name: 'Home Page' }, component: () => import('../views/manager/Home') },
      { path: 'user', name: 'User', meta: { name: 'User Info' }, component: () => import('../views/manager/User') },
      { path: 'person', name: 'Person', meta: { name: 'Personal Info' }, component: () => import('../views/manager/Person') },
      { path: 'password', name: 'Password', meta: { name: 'Change Password' }, component: () => import('../views/manager/Password') },
      { path: 'news', name: 'News', meta: { name: 'News Info' }, component: () => import('../views/manager/News') },
      { path: 'newsDetail', name: 'NewsDetail', meta: { name: 'News Details' }, component: () => import('../views/manager/NewsDetail') },
      { path: 'notice', name: 'Notice', meta: { name: 'Notice' }, component: () => import('../views/manager/Notice') },
      { path: 'logs', name: 'Logs', meta: { name: 'Logs' }, component: () => import('../views/manager/Logs') },
      { path: 'charts', name: 'Charts', meta: { name: 'Data Statistics' }, component: () => import('../views/manager/Charts') },
      { path: 'orders', name: 'Orders', meta: { name: 'Order Management' }, component: () => import('../views/manager/Orders') },
      { path: 'chat', name: 'Chats', meta: { name: 'AI Chat' }, component: () => import('../views/manager/Chat') },
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: '404', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // to 是到达的路由信息
  // from 是开源的路由信息
  // next 是帮助我们跳转路由的函数
  let adminPaths = ['/user']
  let user = JSON.parse(localStorage.getItem('honey-user') || '{}')

  if (user.role !== '管理员' && adminPaths.includes(to.path)) {
    // 如果当前登录的用户不是管理员，然后当前的到达的路径是管理员才有权限访问的路径，那这个时候我就让用户去到一个没有权限的页面，不让他访问实际的页面
    next('/403')
  } else {
    next()
  }
})

export default router
