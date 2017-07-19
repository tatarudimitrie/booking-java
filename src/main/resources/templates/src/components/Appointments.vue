<template>
<body>
<div>
<navbar></navbar> 
<br>
<div class="container">
<b-button @click="submit" class="btn btn-circle btn-xl">
<b-icon name="plus" scale="3" style="color:white"></b-icon> 
<p>Prev.week</p>


>

</b-button>





</div>
</div>
</body>
</template>


<script>
 import Navbar from '@/components/Navbar'
import Vue from 'vue'
export default {
    data(){return {
      e: ''
    };
  },
  methods: {
    format(value) {
      return value.toLowerCase();
    },
    submit(){
       {
        console.log(this.e);
      }
    }
}
}



</script>
<style>

   .container{

        width:50%;

        min-height: 150px;

    }

.btn-smaller.btn-xl {
  width: 100px;
  height: 100px;
  border:none;
  font-size: 10px;
  line-height: 1.33;
  padding-bottom: 25px;
  text-align: center;
  background-color: white;
  color: black;
  left:-8%;

}


</style>