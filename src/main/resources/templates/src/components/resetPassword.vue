<template>
  <div>
    <img src="../assets/logo.jpg" alt="" width="200">
    <p class="title">Booking <span class="app">App</span></p>

    <div class="container">
    <br>
      <div>
        <div class="form-group" v-bind:class="{ 'form-group--error': $v.password.$error }">
          <label><b>Password</b></label>
          <b-form-input v-model="password"
          type="password"
          placeholder="Password"
          :state="password.length?'success':'warning'"
          :formatter="format"
          ></b-form-input>
          <p v-if="!$v.password.minLength">The password must be 6 characters or longer!</p>
          <p v-if="!$v.password.maxLength">The password must be less than 16 characters!</p>
        </div>
        <div class="form-group" v-bind:class="{ 'form-group--error': $v.pass_confirm.$error }">
          <label><b>Confirm password</b></label>
          <b-form-input v-model="pass_confirm"
          type="password"
          placeholder="Confirm password"
          :state="pass_confirm.length?'success':'warning'"
          :formatter="format"
          :required="true"
          ></b-form-input>
          <p v-if="!$v.pass_confirm.sameAs">This field must be the same as password!</p>
        </div>
      </div>
      <b-button @click="submit" class="btn btn-success">Recover</b-button>

      <br>
      <br>
    </div>
  </div>
</template>

<script>


  import { required, email, minLength,maxLength, between, sameAs } from 'vuelidate/lib/validators'
  export default {
    data(){return {
      password: '',
      pass_confirm:''
    };
  },
  methods: {
    format(value) {
      return value.toLowerCase();
    },
    
                 submit(){

                if (this.$v.password.minLength && this.$v.password.maxLength && this.$v.pass_confirm.sameAs ) {

                    this.$http.post("http://192.168.150.245:8080/forgot/password", {

                        "password": this.password,
                        "confirm": this.pass_confirm,
                        "token":""

                        

                    },

                    {

                        headers:{

                            'Accept':'application/json',
                           
//                             'Access-Control-Allow-Origin':  '*',
// 'Access-Control-Allow-Methods':  'POST', 'GET', 'OPTIONS', 'PUT', 'DELETE',
// 'Access-Control-Allow-Headers':  'Content-Type', 'X-Auth-Token',' Origin', 'Authorization'

                        }

                    }).then(response => {
                       if(response.bodyText!='Success!')
                        console.log('Dani ');
                      else
                        console.log('Mocanu');
                    
                        
                    }, response => {
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
      name: {
        required
      },
      email: {
        email
      },
      password: {
        minLength: minLength(6),
        maxLength: maxLength(16)
      },
      pass_confirm:{
        sameAs: sameAs('password')

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
  p{
    font-size:10px;
  }


</style>
