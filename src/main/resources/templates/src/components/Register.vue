<template>
	<div>
		<img src="../assets/logo.jpg" alt="" width="200px" > 
    	<p class="title">Booking <span class="app">App</span></p>
		<div class="container">
		<b-form class="form-horizontal">
		<br>
			<div class="form-input">
				<label><b>Your name</b></label>
				<b-form fieldset>
				<b-form-input v-model="name"
				type="text"
				placeholder="Enter your name"
				:state="name.length?'success':'warning'"
				:formatter="format"
				></b-form-input>
				</b-form fieldset>
			</div>
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
				type="password"
				placeholder="Password"
				:state="password.length?'success':'warning'"
				:formatter="format"
				></b-form-input>
			</div>
			<div class="form-input">
				<label><b>Confirm password</b></label>
				<b-form-input v-model="pass_confirm"
				type="password"
				placeholder="Confirm password"
				:state="pass_confirm.length?'success':'warning'"
				:formatter="format"
				:required="true"
				></b-form-input>
			</div>
			<br>
			<b-button @click="submit" class="btn btn-success">REGISTER</b-button>
			<br>
			<a href="/Login">You already have an account?</a>
		</b-form>
		<br>
	</div>
	</div>
</template>

<script>


	export default {
		data()  {
			return {
				name: '',
				email: '',
				password: '',
				pass_confirm:''
			}

		},
		methods: {
			format(value) {
				return value.toLowerCase();
			},
			submit() {
				console.log(this.name, this.email, this.password, this.pass_confirm);
			}
		}
	}
</script>

<style>
.container{
	width: 500px;
	min-height: 400px;
	text-align: center;
	background-color: #ccccff;
	margin: 0 auto;
	border-radius: 0 auto;
	margin-top: 20px;
}
.container img{
	width: 120px;
	height: 120px;
	margin-top: 0px;
	margin-bottom: 0px;
}
.form-input{
	text-align: left;
	font-size: 20px;
}
input{
	height: 45px;
	width: 200px;
	font-size: 18px;
	border: none;
	margin-bottom: 10px;
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
.title{
	font-size:80px;
	font-family: Arial;
	color:blue;
}
.app{
	color:skyblue;
}
</style>