// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Layout from './components/Layout.vue'
import VueRouter from 'vue-router'
import IndexPage from  './pages/index.vue'
import Detail from './pages/detail.vue'
import DetailAna from './pages/detail/analysis.vue'
import DetailCou from './pages/detail/count.vue'
import DetailFor from './pages/detail/forecast.vue'
import DetailPub from './pages/detail/publish.vue'
import orderList from './pages/orderList'
import mock from './mock.js'
Vue.use(VueRouter)
let router=new VueRouter({
        mode:'history',
        routes:[
          {
            path:'/',
            component:IndexPage
          },
          {
            path:'/orderList',
            component:orderList
          },
          {
            path:'/detail',
            component:Detail,
            redirect: '/detail/analysis',
            children:[
              {path:'analysis',component:DetailAna},
              {path:'count',component:DetailCou},
              {path:'forecast',component:DetailFor},
              {path:'publish',component:DetailPub}
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
