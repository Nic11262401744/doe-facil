import { createRouter, createWebHistory } from 'vue-router'
import NotFound from '@/components/NotFound/NotFound.vue'
import LoginView from '@/views/LoginView.vue'
import Logout from '@/views/Logout.vue'
import DoacaoView from '@/views/DoacaoView.vue'
import HomeView from '@/views/HomeView.vue'
import ColaboradoresView from '@/views/ColaboradoresView.vue'
import TermsView from '@/views/TermsView.vue'
import PrivacyView from '@/views/PrivacyView.vue'
import { getToken, isTokenExpired, removeToken } from '@/token'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/termos',
      name: 'termos',
      component: TermsView
    },
    {
      path: '/privacidade',
      name: 'privacidade',
      component: PrivacyView
    },
    // {
    //   path: '/admin',
    //   name: 'admin',
    //   component: AdminView
    // },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/doacao',
      name: 'doacao',
      component: DoacaoView
    },
    {
      path: '/colaboradores',
      name: 'colaboradores',
      component: ColaboradoresView
    },
    {
      path: '/logout',
      name: 'logout',
      component: Logout
    },
    {
      path: "/:catchAll(.*)",
      name: "NotFound",
      component: NotFound,
    },

  ]
})

router.beforeEach((to) => {
  if (['/', '/termos', '/privacidade'].includes(to.path)) return true
  if (getToken() && !isTokenExpired()) return true

  removeToken()
  localStorage.removeItem('idOng')
  return { name: 'login' }
})

export default router
