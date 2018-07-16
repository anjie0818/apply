import Mock from 'mockjs'
Mock.mock('api/getNewsList',{
  'list|5':[
    {
      url:'@url',
      title:'@ctitle(5,10)'
    }
  ]
})
Mock.mock('api/login',{
    username:'anjie'
})
Mock.mock('api/getOrderList',{
  "list": [
    {
      "orderId": "ddj123",
      "product": "数据统计",
      "version": "高级版",
      "period": "1年",
      "buyNum": 2,
      "date": "2016-10-10",
      "amount": "500元"
    },
    {
      "orderId": "yuj583",
      "product": "流量分析",
      "version": "户外版",
      "period": "3个月",
      "buyNum": 1,
      "date": "2016-5-2",
      "amount": "2200元"
    },
    {
      "orderId": "pmd201",
      "product": "广告发布",
      "version": "商铺版",
      "period": "3年",
      "buyNum": 12,
      "date": "2016-8-3",
      "amount": "7890元"
    }
  ]

})
Mock.mock('api/createOrder',{
  orderId:'DC201809010001'
})
Mock.mock('api/checkOrder',{
  checkResult:'passk'
})
Mock.mock('api/getAnalysisPrice',{price:765})
function getRImg () {
  return Mock.Random.image('700x300', Mock.Random.hex())
}
Mock.mock(/getSlides/, [
  {
    src: getRImg (),
    title: '@ctitle(4, 12)',
    ky: 1,
    toKey: 'analysis'
  },
  {
    src: getRImg (),
    title: '@ctitle(4, 12)',
    ky: 2,
    toKey: 'count'
  },
  {
    src: getRImg (),
    title: '@ctitle(4, 12)',
    ky: 3,
    toKey: 'publish'
  },
  {
    src: getRImg (),
    title: '@ctitle(4, 12)',
    ky: 4,
    toKey: 'forecast'
  }
])
