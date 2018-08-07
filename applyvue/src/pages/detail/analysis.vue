<template>
  <div class="sales-board">
    <div class="sales-board-intro">
      <h2>流量分析</h2>
      <p>是指在获得网站访问量基本数据的情况下对有关数据进行统计、分析，从中发现用户访问网站的规律，并将这些规律与网络营销策略等相结合，从而发现目前网络营销活动中可能存在的问题，并为进一步修正或重新制定网络营销策略提供依据。当然这样的定义是站在网络营销管理的角度来考虑的</p>
    </div>
    <div class="sales-board-form">
      <div class="sales-board-line">
        <div class="sales-board-line-left">
          购买数量：
        </div>
        <div class="sales-board-line-right">
          <v-counter v-if='buyNum' @on-change="onParamChange('buyNum',$event)" :numberVal="buyNum"></v-counter>
        </div>
      </div>
      <div class="sales-board-line">
        <div class="sales-board-line-left">
          产品类型：
        </div>
        <div class="sales-board-line-right">
          <v-selection :selections="buyTypes" :nowIndexVal="buyType.value"@on-change="onParamChange('buyType',$event)" ></v-selection>
        </div>
      </div>
      <div class="sales-board-line">
        <div class="sales-board-line-left">
          有效时间：
        </div>
        <div class="sales-board-line-right">
          <v-chooser :selections="periodList" :nowIndexVal="period.value" @on-change="onParamChange('period',$event)"></v-chooser>
        </div>
      </div>
      <div class="sales-board-line">
        <div class="sales-board-line-left">
          产品版本：
        </div>
        <div class="sales-board-line-right">
          <v-m-chooser :selections="versionList" :nowIndexesVal="nowIndexes" @on-change="onParamChange('versions',$event)"></v-m-chooser>
        </div>
      </div>
      <div class="sales-board-line">
        <div class="sales-board-line-left">
          总价：
        </div>
        <div class="sales-board-line-right">
          {{ price }} 元
        </div>
      </div>
      <div class="sales-board-line">
        <div class="sales-board-line-left">&nbsp;</div>
        <div class="sales-board-line-right">
          <div class="button" @click="toShowPayDialog" >
          立即购买
        </div>
        </div>
      </div>
    </div>
    <div class="sales-board-des">
      <h2>产品说明</h2>
      <p>网站访问统计分析报告的基础数据源于网站流量统计信息，但其价值远高于原始数据资料。专业的网站访问统计分析报告对网络营销的价值，正如专业的财务分析报告对企业经营策略的价值。</p>

      <h3>用户行为指标</h3>
      <ul>
        <li>用户行为指标主要反映用户是如何来到网站的、在网站上停留了多长时间、访问了哪些页面等，主要的统计指标包括：</li>
        <li>用户在网站的停留时间；</li>
        <li>用户来源网站（也叫“引导网站”）；</li>
        <li>用户所使用的搜索引擎及其关键词；</li>
        <li>在不同时段的用户访问量情况等。</li>
      </ul>

      <h3>浏览网站方式</h3>
      <ul>
        <li>用户上网设备类型</li>
        <li>用户浏览器的名称和版本</li>
        <li>访问者电脑分辨率显示模式</li>
        <li>用户所使用的操作系统名称和版本</li>
        <li>用户所在地理区域分布状况等</li>
      </ul>
    </div>
    <my-dialog :is-show="isShowPayDailog" @on-close="closeShowPayDailog">
      <table class="buy-dialog-table">
        <tr>
          <th>购买数量</th>
          <th>产品类型</th>
          <th>有效时间</th>
          <th>产品版本</th>
          <th>总价</th>
        </tr>
        <tr>
          <td>{{ buyNum }}</td>
          <td>{{ buyType.label }}</td>
          <td>{{ period.label }}</td>
          <td>
            <span v-for="item in versions">{{ item.label }}&nbsp</span>
          </td>
          <td>{{ price }}</td>
        </tr>
      </table>
      <h3 class="buy-dialog-title">请选择银行</h3>
      <bank-chooser @on-change="onChangeBanks"></bank-chooser>
      <div class="button buy-dialog-btn" @click="confirmBuy">
        确认购买
      </div>
    </my-dialog>
    <my-dialog :is-show="isPayDailog" @on-close="closePayDailog"></my-dialog>
    <check-order :is-show-check-dialog="isShowCheckDialog"
                 :order-id="orderId"
                 @closeCheckDialog="closeCheckDialog"
                 >
    </check-order>
  </div>
</template>

<script >

  import BankChooser from '../../components/base/bankChooser.vue'
  import axios from 'axios'
  import _ from 'lodash'
  import MyDialog from '../../components/dialog'
  import VCounter from '../../components/base/counter'
  import VSelection from '../../components/base/selection'
  import VMChooser from '../../components/base/multiplyChooser'
  import VChooser from '../../components/base/chooser'
  import CheckOrder from '../../components/checkOrder.vue'

  export default {
    components: {
      VCounter,
      VSelection,
      VMChooser,
      VChooser,
      MyDialog,
      BankChooser,
      CheckOrder
    },
    created(){
      this.orderNo=this.$route.query.id

      this.findOrderDetail()

    },

    methods:{
      findOrderDetail(){
        axios
          .get("test/getOrderByOrderNo", {
            params: {
              id: this.$route.query.id
            }
          })
          .then(response => {
            this.buyNum= new Number( response.data.proOrder.buyNumber)
            console.log("ajax修改"+this.buyNum)
          })
          .catch(error => {
            console.log(error);
          });
        console.log("暂存单修改"+this.buyNum)

      },
      toShowPayDialog(){
        this.isShowPayDailog=true
      },
      closeCheckDialog(){
                      console.log("jiancha")

        this.isShowCheckDialog=false
      },
      confirmBuy(){
        let buyVersionArray=_.map( this.versions,(item)=>{
                  return item.value
                  }
                )
        let reqParams={
          buyNumber:this.buyNum,
          buyType:this.buyType.value,
          period:this.period.value,
          version:buyVersionArray.join(','),
          bankId:this.bankId,
          price:this.price,
          product:"analysis"
        }
        axios.post('/api/createOrder',reqParams)
          .then((res)=>{
            this.orderId=res.data.orderId
        console.log(this.orderId)

        //支付成功
            this.isShowCheckDialog=true
            this.isShowPayDailog=false
          })
          .catch((error)=>{
            console.log(error)
            //支付失败?失败如何处理
          })
      },

      onChangeBanks(bankObj){
        this.bankId=bankObj.id
        console.log(this.bankId)
      },
      closePayDailog(){
        this.isPayDailog=false
      },
      closeShowPayDailog(){
        this.isShowPayDailog=false
      },
      onParamChange(arr,val){
        this[arr]=val
        console.log(this[arr])
        this.computAnalysisPrice()
      },
      computAnalysisPrice(){

        let buyVersionArray=_.map( this.versions,(item)=>{
          return item.value
          }
        )

        let reqParams={
          buyNumber:this.buyNum,
          buyType:this.buyType.value,
          period:this.period.value,
          version:buyVersionArray.join(',')
        }
        axios.post('api/getAnalysisPrice',reqParams)
          .then((res)=>{
            console.log(res)
            this.price=res.data.price
          })
          .catch((error)=>{
            console.log(error)
          })
      }
    },
    data () {
      return {
        proOrder:{},
        nowIndexes:[1],
        orderNo:null,
        isShowCheckDialog:false,
        isShowPayDailog:false,
        isPayDailog:false,
        buyNum: null,
        buyType: {
          label: '入门版',
          value: 0
        },
        versions: [1],
        period: {
          label: '半年',
          value: 0
        },
        price: 0,
        versionList: [
          {
            label: '客户版',
            value: 0
          },
          {
            label: '代理商版',
            value: 1
          },
          {
            label: '专家版',
            value: 2
          }
        ],
        periodList: [
          {
            label: '半年',
            value: 0
          },
          {
            label: '一年',
            value: 1
          },
          {
            label: '三年',
            value: 2
          }
        ],
        buyTypes: [
          {
            label: '入门版',
            value: 0
          },
          {
            label: '中级版',
            value: 1
          },
          {
            label: '高级版',
            value: 2
          }
        ],
        isShowPayDialog: false,
        bankId: null,
        orderId: null,
        isShowCheckOrder: false,
        isShowErrDialog: false
      }
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .smbutton {
    background: #4fc08d;
    color: #4fc08d;
    display: inline-block;
    padding: 1px 2px;
    cursor: pointer;
  }
  .buy-dialog-title {
    font-size: 16px;
    font-weight: bold;
  }
  .buy-dialog-btn {
    margin-top: 20px;
  }
  .buy-dialog-table {
    width: 100%;
    margin-bottom: 20px;
  }
  .buy-dialog-table td,
  .buy-dialog-table th{
    border: 1px solid #e3e3e3;
    text-align: center;
    padding: 5px 0;
  }
  .buy-dialog-table th {
    background: #4fc08d;
    color: #fff;
    border: 1px solid #4fc08d;
  }
</style>
