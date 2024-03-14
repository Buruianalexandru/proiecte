import { createRouter, createWebHistory } from 'vue-router'
import SignUp from '@/views/SignUp.vue'

const routes = [
  {
    path: '/jobupload',
    name: "JobUpload",
    component: () => import('@/views/JobForm.vue')
  },
  {
    path: '/upload-personal-files',
    name: "FileUpload",
    component: () => import('@/views/FileUpload.vue')
  },
  {
    path: '/jobs',
    name: 'Jobs',
    component: () => import('@/views/JobsPage.vue')
  },
  {
    path: '/',
    name: 'signup',
    component: SignUp
  },
  {
    path: '/login',
    name: 'login',

    component: () => import('@/views/LogIn.vue')
  },
  {
    path: '/contact',
    name: 'contact',
    component: () => import('@/views/ContactUs.vue')
  },
  {
    path: '/appjob',
    name: 'appjob',
    component: () => import('@/views/AppJob.vue')
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('@/views/DashboardPage.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('@/views/ProfilePage.vue')
  },
  {
    path: '/cerere',
    name: 'cerere',
    component: () => import('@/views/CereRe.vue')
  },
  {
    path: '/intrb',
    name: 'intrb',
    component: () => import('@/views/IntrebariFrecv.vue')
  },

  {
    path: '/edit',
    name: 'edit',
    component: () => import('@/views/EditView.vue')
  },
  {
    path: '/org',
    name: 'org',
    component: () => import('@/views/OrganiGrama.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
