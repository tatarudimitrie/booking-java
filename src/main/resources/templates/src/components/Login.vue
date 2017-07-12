<template>

<div class = "container">
	<b-form>
		<div><img src = "../assets/logo.jpg" alt="" width="200"></div>
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
>>>>>>> Stashed changes
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
	}
</style>
