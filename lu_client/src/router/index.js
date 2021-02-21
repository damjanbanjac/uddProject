import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import Register from '../views/register/Register.vue'
import RegisterReader from '../views/register/RegisterReader.vue'
import RegisterWriter from '../views/register/RegisterWriter.vue'
import UploadFile from '../views/indexing/UploadFile.vue'
import Search from '../views/search/Search.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/upload',
    name: 'UploadFile',
    component: UploadFile
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    children: [
      {
        path: 'reader',
        name: 'Register Reader',
        component: RegisterReader,
      },
      {
        path: 'writer',
        name: 'Register Writer',
        component: RegisterWriter,
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL, //why this ?
  routes
})

export default router
