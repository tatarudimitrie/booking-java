<template>
  <div>
    <img src="../assets/logo.jpg" alt="" width="200">
    <p class="title">Booking <span class="app">App</span></p>
    <div class = "container">
      <b-form>
        <div>
          <p v-if = "EmailError">Wrong email!</p>
          <p v-if = "PassError">Wrong password!</p>
        </div>



        <div class="form-input"><br>

          <div class="form-group" v-bind:class="{ 'form-group--error': $v.email.$error }">
           <label><b>Email address:</b></label><br>
           <input class="form__input" 
           v-model.trim="email" 
           placeholder="Enter your email" 
           :state="email.length?'success':'warning'" 
           :formatter="format" 
           @blur="$v.email.$touch()" 
           type="text" 
           @input="$v.email.$touch()">
           <p class="form-group__message" v-if="!$v.email.email">This field must be an email!</p>
         </div>
       



       <div class="form-input">
        <label><b>Password:</b></label><br>
        <input v-model="password"
        type="password"
        placeholder="Enter your password"
        :state="password.length?'success':'warning'"
        :formatter="format"
        >
        <p class="form-group__message" v-if="!$v.password.minLength">The password must be 6 characters or longer!</p>
        <p class="form-group__message" v-if="!$v.password.maxLength">The password must be less than 16 characters!</p>
      </div>

      <b-button @click="submit" class="btn btn-success">Submit</b-button>
      <div>
        <a href="/Recover" id="forget">Recover password</a>
      </div>
      <br>
      <a href="/Register" id="register">Don't have an account? Register here!</a>
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
       password: '',
       EmailError: false,
       PassError: false
     }
   },
   methods: {
     format(value) {
       return value.toLowerCase();
     },
     submit() {
       if (this.$v.email.email && this.$v.password.minLength && this.$v.password.maxLength) {
       //  this.input = {
       //    email: this.email,
       // password: this.password,
       //  }
       //  this.$auth.login(this, this.input, "/dashboard", (errors) =>{
       //    console.log(errors)
       //  } );
        this.$http.post(`${process.env["API_URL"]}/login`, {
          "email": this.email,
          "pass": this.password
        },
        {
          headers:{
            'Acces-Control-Allow-Origin': '*'
          }

        }).then(response => {
          console.log("success", response.status, response.body);
          if(response.status === 200){
            location.href = "/dashboard"
            sessionStorage.setItem("email", this.email)
          }
          
        }, response => {
          if(response.bodyText === "Wrong email"){
            this.EmailError = true
          }
          else if(response.bodyText === "Wrong password"){
            this.PassError = true
          }
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
    min-height: 350px;
    text-align: center;
    background-color: #ccccff;
    margin: 0 auto;
    border-radius: 0 auto;
    margin-top: 20px;
  }
  input{
    height: 45px;
    width: 350px;
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
   font-size:15px;
   color:red;
 }
 .form-group--error input{
   color: red;
 }
</style>