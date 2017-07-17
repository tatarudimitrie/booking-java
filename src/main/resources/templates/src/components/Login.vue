<template>
  <div>
    <img src="../assets/logo.jpg" alt="" width="200">
    <p class="title">Booking <span class="app">App</span></p>
    <div class = "container">
      <b-form>
        <div class="form-input"><br>
          <label><b>Email address</b></label>
          <b-form-input v-model="email"
          type="text"
          placeholder="Enter email"
          :state="email.length?'success':'warning'"
          :formatter="format"
          ></b-form-input>
          <p v-if="!$v.email.email">This field must be an email!</p>
        </div>
        <div class="form-input">
          <label><b>Password</b></label>
          <b-form-input v-model="password"
          type="password"
          placeholder="Enter password"
          :state="password.length?'success':'warning'"
          :formatter="format"
          ></b-form-input>
          <p v-if="!$v.password.minLength">The password must be 6 characters or longer!</p>
          <p v-if="!$v.password.maxLength">The password must be less than 16 characters!</p>
        </div>
        <b-button @click="submit" class="btn btn-success">Submit</b-button>
        <div>
          <a href="/Recover" id="forget">Recover password</a>
        </div>
      </b-form>   
    </div>
  </div>
</template>


<!-- SCRIPT JavaScript -->
<script>
  import { required, email, minLength, maxLength } from 'vuelidate/lib/validators'

  export default {
   data() {
     return {
       email: '',
       password: ''
     }
   },
   methods: {
     format(value) {
       return value.toLowerCase();
     },
     submit() {
      if (this.$v.email.email && this.$v.password.minLength && this.$v.password.maxLength) {
        debugger
        var data = this.$http.get("http://192.168.151.51:8080/admins/", 
        {
          headers:{
            'Accept':'application/json'
          }

        }).then(response => {
          console.log("success");
        }, response => {
          console.log(response.body)
        })
      }
    }
  },
  validations: {
   email:{
     email
   },
   password:{
     minLength: minLength(6),
     maxLength: maxLength(16)
   }
 }
}
</script>








<!-- STYLE CSS -->
<style>
  a{
    text-decoration: none;
  }
  .container{
    width: 500px;
    height: 350px;
    text-align: center;
    background-color: #ccccff;
    margin: 0 auto;
    border-radius: 0 auto;
    margin-top: 20px;
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
  p{
    font-size: 80px;
    font-family: Arial;
  }
  .form-input{
    text-align: left;
    font-size: 20px;
  }
  #forget{
    font-size: 18px;
  }
  .app{
    color: skyblue;
  }
  .title{
    color: blue;
    font-size:80px;
  }
  p{
   font-size:10px;
 }
</style>