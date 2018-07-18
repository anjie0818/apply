<template>
  <div class="upload clear">
    <span>营业1执1照</span>
d
    <slider animation="fade">
      <slider-item v-for="(i, index) in slides" :key="index">
        <div :style="i">
          <p style="line-height: 280px; font-size: 5rem; text-align: center;">Page{{ index + 1 }}</p>
        </div>
      </slider-item>
    </slider>
  </div>
</template>

<script>
  import axios from 'axios'
  import { Slider, SliderItem } from 'vue-easy-slider'
  export default {
    components: {
      Slider,
      SliderItem
    },
    mounted(){
      axios.get('api/getSlides')
      .then((res) => {
        console.log(res)
        this.slides = res.data
      })
      .catch((error) => {
        console.log(error)
      })
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
