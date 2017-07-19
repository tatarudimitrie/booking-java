<template>
  <body>
    <navbar></navbar>


    <div class="container">
      <div class="w3-display-topright">
        <label>Company Name</label>
        <b-form-input v-model="Company"

        type="text"
        placeholder=""
        :state="Company.length?'success':'warning'"
        :formatter="format"
        ></b-form-input>
      </div>
      <div class="w3-display-topright1">
        <label>Company Description</label>
        <b-form-input v-model="cDescription"
        type="text"

        placeholder=""
        :state="cDescription.length?'success':'warning'"
        :formatter="format"
        :height=200
        ></b-form-input>
      </div>

      <b-button @click="signOut" class="btn btn-success">Sign out</b-button>
      <b-button @click="submit" class="btn btn-warning">Save</b-button>

      <div class="w3-display-left">
        <b-button @click="submit" class="btn btn-circle btn-xl">Logo</b-button>
        <br>
        <br>
        <b-form-file v-model="file"></b-form-file>
        <p>Selected file: {{file && file.name}}</p>
        <br>
      </div>
    </div>
  </body>
</template>

<script>

  import Navbar from '@/components/Navbar'


  export default{
    data(){
     return {
      Company: '',
      cDescription: '',
      file:''
    };
  },
  methods: {
    format(value) {
      return value.toLowerCase();
    },
    submit() {
      if(sessionStorage.getItem('id_company') !== null){
        this.$http.put("http://192.168.151.51:8080/companies/edit", {
          "name": this.Company,
          "description": this.cDescription,
          "image_url": "aceastaing",
          "id": sessionStorage.getItem('id_company')
        },
        {
          headers:{
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
          }
        }).then(response =>{
          console.log("response", response);
        });
      } else {
        this.$http.post("http://192.168.151.51:8080/companies/add", {
          "name": this.Company,
          "description": this.cDescription,
          "image_url": "aceastaing",
          "admin":{
            "email":sessionStorage.getItem('email')
          }
        },
        {
          headers:{
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
          }

        }).then(response =>{
          console.log("response", response)
        })
      }
    },
    signOut(){
      sessionStorage.clear();
      location.href="/login"
    },
    getUserInfo() {
      this.$http.post("http://192.168.151.51:8080/companies/admin",{
        "email":sessionStorage.getItem('email')
      },
      {
        headers:{
          'Content-Type': 'application/json'
        }
      }).then(response =>{
        if(response.body) {
          var company = response.body;
          this.Company = company.name ;
          this.cDescription = company.description;
          sessionStorage.setItem("id_company", company.id)
        } else {
          return
        }
       
      })
      .then(error => {
        console.log('error: ', error);
      });
    }
  },
  created() {
    this.getUserInfo();
  },

  name: 'app',
  components: {Navbar}

}

</script>




<style>
  .container{
    width: 750px;
    min-height: 150px;
    text-align: left;
    /*background-color: #ccccFf;*/
    margin: 0 auto;
    border-radius: 0 auto;
    margin-top: 20px;
  }

  .btn.btn-success{

    background-color:#D8BFD8;
    margin: 10;
    border: none;
    position:absolute;
    transition: .5s ease;
    top: 175%;
    left: 60%;

  }
  .btnci{
   text-align: center;

 }
 .btn.btn-warning{
   background-color:#EE82EE;
   margin: 10;
   border: none;
   position:absolute;
   transition: .5s ease;
   top: 175%;
   left: 50%;
 }

 .w3-display-topright{
   position:absolute;
   transition: .5s ease;
   top: 70%;
   left: 50%;
   width: 250px;


 }

 .w3-display-topright1{
   position:absolute;
   transition: .5s ease;
   top: 120%;
   left: 50%;
   width: 250px;
 }
 h4{
   text-align: center;

 }
 h6{
   text-align: center;}

   .btn-circle.btn-xl {
    width: 200px;
    height: 200px;
    padding: 15px 15px;
    font-size: 55px;
    line-height: 1.33;
    border-radius: 140px;
    text-align: center;
    background-color: #ccccFf;
    color: white;
    left:-8%;

  }
  .w3-display-left{
    text-align: left;
    position:absolute;
    transition: .5s ease;
    top: 70%;
    left: -10%;
    width: 250px;

  }
</style>