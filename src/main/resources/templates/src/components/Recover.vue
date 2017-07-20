<template>
 <div>
   <img src="../assets/logo.jpg" alt="" width="200">
   <p class="title">Booking <span class="app">App</span></p>

   <div class="container">
     <br>
     <div>
       <div class="form-group" v-bind:class="{ 'form-group--error': $v.email.$error }">
         <label class="form__label">Email address</label>
         <input class="form__input" v-model.trim="email" @blur="$v.email.$touch()" @input="$v.email.$touch()">
         <p class="form-group__message" v-if="!$v.email.email">Field is required</p>
       </div>
     </div>
     <b-button @click="submit" class="btn btn-success">Recover</b-button>

     <br>
     <br>
   </div>
 </div>
</template>

<script>
 import { required, email } from 'vuelidate/lib/validators'
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
     if (this.$v.email.email   ) {

       this.$http.post("http://192.168.150.245:9999/forgot/password", {
         "email": this.email
       },
       {
         headers:{
           'Accept':'application/json',
         }

       }).then(response => {
        debugger;
        if(response.bodyText!='Success!')
         console.log('E ');
       else
         console.log('S');


     }, response => {
      debugger;
      if(response.bodyText!='Success!')
      {
       console.log('EROARE');
     } else {
       console.log('succes');                        
     }
   });
     }
   }
 },
 validations: {
   email:{
     email
   }
 }
}
</script>

<style>
 a{
   text-decoration: none;
 }

 .form-group input{
   color: black;
 }
 .form-group input:focus{
   outline: 0;
 }
 .form-group--error input{
   color: red;
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
   background-color: #ccccFf ;
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
 p{
   font-size:10px;
 }


</style>
