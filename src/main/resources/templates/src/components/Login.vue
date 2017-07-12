<template>
<div class = "container">
	<b-form>
		<div><img src = "#";</div>
			<div class="form-input">
				<label><b>Email address</b></label>
				<b-form-input v-model="email"
                  type="text"
                  placeholder="Enter email"
                  :state="email.length?'success':'warning'"
                  :formatter="format"
    			></b-form-input>
			</div>
			<div class="form-input">
				<label><b>Password</b></label>
				<b-form-input v-model="password"
                  type="text"
                  placeholder="Enter password"
                  :state="password.length?'success':'warning'"
                  :formatter="format"
    			></b-form-input>
			</div>
			<b-button @click="submit">Submit</b-button>
			<div>
			<a href="#">Recover password</a>
			</div>
		</b-form>	
	</div>
</template>
<!-- SCRIPT JavaScript -->
<script>
export default {
  data() {
  	return {
        email: '',
        password: ''
      };
  },
  methods: {
    format(value) {
      return value.toLowerCase();
    },
    submit() {
    	console.log(this.email, this.password);
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
	height: 400px;
	text-align: center;
	background-color: #F3F3F3;
	margin: 0 auto;
	border-radius: 0 auto;
	margin-top: 150px;
}
.container img{
	width: 120px;
	height: 120px;
	margin-top: -60px;
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
