<template>
<div>
    <img src="../assets/logo.jpg" alt="" width="200">
    <p class="title">Booking <span class="app">App</span></p>

<div class="container">
  
  
  <form>
  <div class="form-input">
  <label>Email Adress.</label>

      <b-form-input v-model="email"
                  type="text"
                  placeholder="Enter email"
                  :state="email.length?'success':'warning'"
                  :formatter="format"
    ></b-form-input>
   
  <b-button @click="submit" class="btn btn-success">Recover</b-button>
  </div>

  </form>
</div>
</div>
</template>

<script>
export default {
  data(){return {
    email: ''
    };
  },
  methods: {
    format(value) {
      return value.toLowerCase();
    },
    submit(){
      console.log(this.email);
    }
  }
}
</script>

<style>
a{
text-decoration: none;
}


.title{
    font-size:80px;
    font-family: Arial;
    color:blue;
}
.app{
    color:skyblue;
}



.container{
  width: 500px;
  min-height: 150px;
  text-align: center;
  background-color: #ccccFf;
  margin: 0 auto;
  border-radius: 0 auto;
  margin-top: 20px;
}
.container img{
  width: 120px;
  height: 120px;
  margin-top: 0px;
  margin-bottom: 30px;
}
input{
  height: 45px;
  width: 300px;
  font-size: 18px;
  border: none;
  margin-bottom: 20px;
  border-radius: 4px;
  background-color: #fff;
  padding-left: 30px;
}
.form-input{
  text-align:left;
  font-size:20px;
}
.btn-login{
  height: 45px;
  padding: 15px 30px;
  cursor: pointer;
  border-radius: 4px;
  border: none;
  background-color: blue;
  border-bottom: 4px solid blue;
  margin-bottom: 20px;
}

</style>
