// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import $ from 'jquery'
import Vue from 'vue'
import Layout from './components/Layout.vue'
import VueRouter from 'vue-router'
import IndexPage from  './pages/index.vue'
import Detail from './pages/detail.vue'
import DetailAna from './pages/detail/analysis.vue'
import test from './pages/detail/test.vue'
import DetailCou from './pages/detail/count.vue'
import DetailFor from './pages/detail/forecast.vue'
import DetailPub from './pages/detail/publish.vue'
import orderList from './pages/orderList'
import login from './pages/Login.vue'
import mock from './mock.js'

Vue.use(ElementUI)
Vue.use(VueRouter)
let router=new VueRouter({
        mode:'history',
        routes:[
          {
            path:'/',
            component:IndexPage
          },
          {
            path:'/ui/login',
            component:login
          },
          {
            path:'/test',
            component:test
          },
          {
            path:'/ui/orderList',
            component:orderList
          },
          {
            path:'/ui/detail',
            component:Detail,
            redirect: '/ui/detail/analysis',
            children:[
              {path:'analysis',component:DetailAna},
              {path:'count',component:DetailCou},
              {path:'forecast',component:DetailFor},
              {path:'publish',component:DetailPub},
              {path:'analysis/:id',name:'analysis',component:DetailAna},
              {path:'count/:id',name:'count',component:DetailCou},
              {path:'forecast/:id',name:'forecast',component:DetailFor},
              {path:'publish/:id',name:'publish',component:DetailPub}
            ]
          }
       ]
})
Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router:router,
  components: { Layout },
  template: '<Layout/>'
})
