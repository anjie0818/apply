<template>
  <div>
    <this-dialog :is-show="isShowCheckDialog" @on-close="checkStatus">
      请检查你的支付状态！
      <div class="button" @click="checkStatus">
        支付成功
      </div>
      <div class="button" @click="checkStatus">
        支付失败
      </div>
    </this-dialog>
    <this-dialog :is-show="isShowSuccessDialog" @on-close="toOrderList">
      购买成功！
    </this-dialog>
    <this-dialog :is-show="isShowFailDialog" @on-close="toOrderList">
      购买失败！
    </this-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import Dialog from './dialog'
export default {
  components: {
    thisDialog: Dialog
  },
  props: {
    isShowCheckDialog: {
      type: Boolean,
      default: false
    },
    orderId: {
       type: [String, Number]
    }
  },
  data () {
    return {
        isShowSuccessDialog:false,
        isShowFailDialog:false,
        checkResult:""
    }
  },
  methods: {
    toOrderList(){
      this.$router.push({path: '/orderList'})
    },
    closeCheckDialog(){
      console.log("closeCheckDialog")
    },
    checkStatus () {
          axios.post('api/checkOrder',{orderId:this.orderId})
          .then((res)=>{
              this.checkResult=res.data.checkResult
              if(this.checkResult==="pass"){
                this.isShowSuccessDialog=true
                this.$emit('closeCheckDialog')
              }
              else{
                this.isShowFailDialog=true
                this.$emit('closeCheckDialog')
              }
          })
          .catch((error)=>{
            console.log(error)

          })
        },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
