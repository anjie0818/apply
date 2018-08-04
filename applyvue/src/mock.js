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
Mock.mock('test/delete',{
  message:'true'
})
Mock.mock('test/getOrderList',{
  "list" : [ {
    "id" : 27,
    "orderNo" : "DC2018080371260938",
    "product" : "流量分析",
    "buyType" : "0",
    "buyTypeName" : "入门版",
    "price" : "765",
    "buyNumber" : "1",
    "period" : "0",
    "periodName" : "半年",
    "version" : "0",
    "versionName" : "客户版 ",
    "bankId" : "501",
    "orderStatus" :'0',
    "date" : "2018-08-03"
  }, {
    "id" : 28,
    "orderNo" : "DC2018080356719302",
    "product" : "流量分析",
    "buyType" : "2",
    "buyTypeName" : "高级版",
    "price" : "765",
    "buyNumber" : "2",
    "period" : "2",
    "periodName" : "三年",
    "version" : "0",
    "versionName" : "客户版 ",
    "bankId" : null,
    "orderStatus" :'0',
    "date" : "2018-08-03"
  }, {
    "id" : 29,
    "orderNo" : "DC2018080393461208",
    "product" : "流量分析",
    "buyType" : "0",
    "buyTypeName" : "入门版",
    "price" : "765",
    "buyNumber" : "1",
    "period" : "0",
    "periodName" : "半年",
    "version" : "0",
    "versionName" : "客户版 ",
    "bankId" : "201",
    "orderStatus" :'0',
    "date" : "2018-08-03"
  }, {
    "id" : 30,
    "orderNo" : "DC2018080338175640",
    "product" : "流量分析",
    "buyType" : "0",
    "buyTypeName" : "入门版",
    "price" : "765",
    "buyNumber" : "1",
    "period" : "0",
    "periodName" : "半年",
    "version" : "0",
    "versionName" : "客户版 ",
    "bankId" : "301",
    "orderStatus" :'1',
    "date" : "2018-08-03 14:53:34"
  } ]
})
Mock.mock('api/testcreateOrder',{
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
