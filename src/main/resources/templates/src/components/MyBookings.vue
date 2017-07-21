<template>
	<body>
		<div>
			<navbar></navbar>
			<div class="container">
				<h2>You have {{info.length}} bookings for your company!</h2>
				<br>
				<div class="row bg-primary">
					<div class="col-sm-2">
						<h4>Name</h4>
					</div>
					<div class="col-sm-5">
						<h4>Email</h4>
					</div>
					<div class="col-sm-3">
						<h4>Phone number</h4>
					</div>
					<div class="col-sm-2">
						<h4>Date</h4>
					</div>
				</div>
				<div class="row bg-info" v-for="booking in info">
					<div class="col-sm-2">
						<h5>{{booking.name}}</h5>
					</div>
					<div class="col-sm-5">
						<h5>{{booking.email}}</h5>
					</div>
					<div class="col-sm-3">
						<h5>{{booking.phone}}</h5>
					</div>
					<div class="col-sm-2">
						<h5>{{booking.date}}</h5>
					</div>
				</div>
			</div>
		</div>
	</body>
</template>




<script type="text/javascript">
	import Navbar from '@/components/Navbar'
	export default {
		name: 'app',
		data(){
			return{
				info: []
			}
		},
		components: { Navbar },
		methods:{
			getServices: function (){
				this.$http.get(`${process.env["API_URL"]}/bookings/all`, 
				// {
				// 	"id": sessionStorage.getItem("idCompany")
				// },
				{
					headers:{
						'Content-Type': 'application/json'
					}
				}).then(response =>{
					this.info = response.body;


				})
				.then(error => {
					console.log('error: ', error);
				})
			}
		},
		created() {
			this.getServices();
		}
	}
</script>


<style>
	.container{
		width: 80%;
		min-height: 220px;
		text-align: center;
		background-color: white;
		margin-top: 20px;
	}
	body{
		background-color: #f3f3f3;
	}
	.row{
		padding:10px;
	}
</style>