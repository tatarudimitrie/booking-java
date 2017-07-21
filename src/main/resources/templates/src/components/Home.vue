<template>
	<div>
		<div class="row title">
		<div class="col-sm-2 text-right">
				<img src="../assets/logo.jpg" alt="" width="auto">
			</div>
			<div class="col-sm-8 text-left"> 
			<p>Booking <span class="app">App</span></p>
			</div>
		</div>
		<div>
			<div class="container">
				<h2>We provide you with information from {{company.length}} companies.</h2>
				<h3>Click a company to see their services!</h3>
				<br>
				<div class="row bg-header">
					<div class="col-sm-4"><h3>Company</h3></div>
					<div class="col-sm-8"><h3>Description</h3></div>
				</div>
				<span class="header">
					<div v-for="entity in company" class="row bg-infos cHeader" @click="submit(entity)">
						<div class="col-sm-4"><h5>{{entity.name}}</h5></div>
						<div class="col-sm-8"><h5>{{entity.description}}</h5></div>
					</div>
				</span>
			</div>
		</div>
	</div>
</template>



<script>
	import Vue from 'vue'
	export default {
		data () {
			return {
				company:[]
			}
		},
		methods: {
			submit(entity){
				sessionStorage.setItem("idCompany", entity.id)
				location.href = "http://localhost:8080/booking"
			},
			getCompany: function (){
				this.$http.get(`${process.env["API_URL"]}/companies/all`,
				{
					headers:{
						'Content-Type': 'application/json'
					}
				}).then(response =>{
					this.company = response.body;
				})
				.then(error => {
					console.log('error: ', error);
				})
			},
		},
		created(){
			this.getCompany();
		}
	}
</script>

<style>
	body{
		background-color: #f3f3f3;
	}
	.container{
		margin-top: 20px;
		text-align: left;
		width:75%;
		min-height: 200px;
		background-color: 
	}
	.row{
		padding: 10px;
		min-height: 20px;
	}
	.title{
		color: blue;
		font-size:80px;
		background-color: white;
	}
	.app{
    color: skyblue;
  	}
	.bg-header{
		background-color: #ff00ff;
	}
	.bg-infos{
		background-color: #ff99ff;
	}
	.header :hover{
		background-color: #ff66ff;
	}
	.cHeader{
		border-top: 5px solid white;
		text-align: left;
		min-height: 20px;
	}
</style>