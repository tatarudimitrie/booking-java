<template>
	<div>
		<img src="../assets/logo.jpg" alt="" width="200px" > 
		<p class="title">Booking <span class="app">App</span></p>
		<div class="container">

			<div v-if = "seen">
				<p style="font-size:20px">This user already exists!</p>
			</div>
			<div v-if = "EmailSeen">
				<p style="font-size:20px">This email already exists!</p>
			</div>

			<b-form class="form-horizontal">
				<div class="form-input"><br>
					<div class="form-group" v-bind:class="{ 'form-group--error': $v.name.$error }">
						<label><b>Your name:</b></label><br>
						<input class="form__input" 
						v-model.trim="name" 
						placeholder="Enter your name" 
						:state="email.length?'success':'warning'" 
						:formatter="format" 
						@blur="$v.name.$touch()" 
						type="text" 
						@input="$v.name.$touch()">
						<p v-if="!$v.name.required">This field is required!</p>
					</div>

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
						<p v-if="!$v.email.email">This field must be an email!</p>
					</div>

					<div class="form-group" v-bind:class="{ 'form-group--error': $v.password.$error }">
						<label><b>Password:</b></label><br>
						<input class="form__input" 
						v-model.trim="password" 
						placeholder="Enter password" 
						:state="password.length?'success':'warning'" 
						:formatter="format" 
						@blur="$v.password.$touch()" 
						type="password" 
						@input="$v.password.$touch()">
						<p v-if="!$v.password.minLength">The password must be 6 characters or longer!</p>
						<p v-if="!$v.password.maxLength">The password must be less than 16 characters!</p>
					</div>

					<div class="form-group" v-bind:class="{ 'form-group--error': $v.pass_confirm.$error }">
						<label><b>Confirm password:</b></label><br>
						<input class="form__input" 
						v-model.trim="pass_confirm" 
						placeholder="Confirm your password" 
						:state="pass_confirm.length?'success':'warning'" 
						:formatter="format" 
						@blur="$v.pass_confirm.$touch()" 
						type="password" 
						@input="$v.pass_confirm.$touch()">
						<p v-if="!$v.pass_confirm.sameAs">This field must be the same as password!</p>
					</div>
					<b-button @click="submit" class="btn btn-success">REGISTER</b-button>
				</div>
				<a href="/Login">You already have an account?</a>
			</b-form>
		</div>
	</div>
</template>

<script>

	import Router from 'vue-router'
	var router = new Router();
	import { required, email, minLength,maxLength, between, sameAs } from 'vuelidate/lib/validators'
	export default {
		data()  {
			return {
				name: '',
				email: '',
				password: '',
				pass_confirm:'',
				seen: false,
				EmailSeen: false
			}

		},
		methods: {
			format(value) {
				return value.toLowerCase();
			},
			submit() {
				if (this.$v.email.email && this.$v.name.required && this.$v.password.minLength && this.$v.password.maxLength && this.$v.pass_confirm.sameAs) {
					this.$http.post(`${process.env["API_URL"]}/admins/add`, {
						"name": this.name,
						"email": this.email,
						"pass": this.password
					},
					{
						headers:{
							'Access-Control-Allow-Origin': '*',
							'Content-Type': 'application/json',
						}

					}).then(response => {
						if(response.status === 200){
							location.href= '/login';
							// console.log('response: ', response);
						}else{
							
						}
						
					}, response => {
						if(response.bodyText === "Duplicate name"){
							this.seen = true;
						}
						else if(response.bodyText === "Duplicate email"){
							this.EmailSeen = true;
						}
						
					})

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
	.container{
		width: 500px;
		min-height: 400px;
		text-align: center;
		background-color: #ccccff;
		margin: 0 auto;
		border-radius: 0 auto;
		margin-top: 20px;
	}
	.container{
    width: 500px;
    min-height: 350px;
    text-align: center;
    background-color: #ccccff;
    margin: 0 auto;
    border-radius: 0 auto;
    margin-top, margin-bottom: 20px;
    padding-bottom: 20px;
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

	p{
		font-size:15px;
		color:red;
	}
	.form-group--error input{
   color: red;
 }
</style>