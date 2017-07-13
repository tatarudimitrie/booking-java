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

<b-button @click="submit" class="btn btn-success">Sign out</b-button>
<b-button @click="submit" class="btn btn-warning">Save</b-button>

<div class="w3-display-left">
<b-button @click="submit" class="btn btn-circle btn-xl">Logo</b-button>
<br>
<br>
<b-form-file v-model="file"></b-form-file>
<br> Selected file: {{file && file.name}}
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
        cDescription: ''
      };
  },
  methods: {
    format(value) {
      return value.toLowerCase();
    },
    submit() {
    	console.log(this.Company, this.cDescription);
    }
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