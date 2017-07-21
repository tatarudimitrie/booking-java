<template>
	<body>
		<div>
			<navbar></navbar>
			<br>
			<div class="container">
				<div class="container-service row">
					<h1 class="title col-sm-6">Edit service</h1>
					<div class="button col-sm-6">
						<button @click="submit" class="btn btn-default circle" aria-label="Right Align">
							<icon name="pencil" scale="3" style="color:white"></icon>
						</button>
						<p>Edit service</p>
					</div>
				</div>
				<p class="text-left details">SERVICE DETAILS</p>
				<div class="row">

					<div class="input-field col-lg-6">
						<b-form-fieldset
						label="Service name"
						:label-cols="3"
						>
						<b-form-input v-model="s_name"></b-form-input>
						<p v-if="!$v.s_name.required">The field is required!</p>
					</b-form-fieldset>
				</div>

				<div class="input-field col-lg-6">
					<b-form-fieldset
					label="Service duration"
					:label-cols="3"
					>
					<b-form-input v-model="s_duration"></b-form-input>
					<p v-if="!$v.s_duration.required">The field is required!</p>
				</b-form-fieldset>
			</div>

			<div class="input-field col-lg-6">
				<b-form-fieldset
				label="Service description"
				:label-cols="3"
				>				  
				<b-form-input textarea v-model="description" placeholder="Enter your description" class="textarea"></b-form-input>
			</b-form-fieldset>
		</div>

		<div class="input-field col-lg-6">
			<b-form-fieldset
			label="Spaces"
			:label-cols="3"
			>
			<b-form-input v-model="spaces"></b-form-input>
			<p v-if="!$v.spaces.required">The field is required!</p>
		</b-form-fieldset>
		<b-form-fieldset
		label="Price"
		:label-cols="3"
		>
		<b-form-input v-model="price"></b-form-input>
		<p v-if="!$v.price.required">The field is required!</p>
	</b-form-fieldset>
</div>
</div>
<div class="availability text-left">
	<p class="details">AVAILABILITY</p>
</div>

<div id="box">
	<table>
		<thead>
			<tr>
				<td></td>
				<td v-for="day in week">
					{{ day }}
				</td>
			</tr>
		</thead>
		<tbody>
			<tr v-for="hour in hours">
				<td>{{hour}}</td>
				<td v-for="day in week">
					<label :for="day + hour" > <input type="checkbox" :id="day + hours.indexOf(hour)" ></label>
				</td>
			</tr>
		</tbody>
	</table>
</div>


</div>
</div>
</body>
</template>



<script>
	import Navbar from '@/components/Navbar'
	import { required, email, minLength, between } from 'vuelidate/lib/validators'
	import Vue from 'vue'
	export default {
		data () {
			return {
				s_name: sessionStorage.getItem('serviceName'),
				s_duration: sessionStorage.getItem('serviceDuration'),
				description: sessionStorage.getItem('serviceDescription'),
				spaces: sessionStorage.getItem('serviceSpace'),
				price: sessionStorage.getItem('servicePrice'),
				week: [
				'MON',
				'TUE',
				'WED',
				'THU',
				'FRI',
				'SAT',
				'SUN'
				],
				hours: [
				'07:00',
				'08:00',
				'09:00',
				'10:00',
				'11:00',
				'12:00',
				'13:00',
				'14:00',
				'15:00',
				'16:00',
				'17:00',
				'18:00'
				]
			}
		},
		methods: {
			format(value) {
				return value.toLowerCase();
			},
			submit(){
				if (this.$v.s_name.required && this.$v.s_duration.required && this.$v.spaces.required && this.$v.price.required) {
					this.$http.put(`${process.env["API_URL"]}/services/edit`, {
						"company":{
							"id": sessionStorage.getItem('idCompany')
						},
						"id":sessionStorage.getItem('idService'),
						"name": this.s_name,
						"description": this.description,
						"duration": this.s_duration,
						"free_space": this.spaces,
						"price": this.price,
						"date": sessionStorage.getItem('serviceAvailability')
					},
					{
						headers:{
							'Accept':'application/json'
						}

					}).then(response => {
						console.log("response:", response);
						sessionStorage.removeItem('idService');
						sessionStorage.removeItem('serviceName');
						sessionStorage.removeItem('serviceDescription');
						sessionStorage.removeItem('serviceDuration');
						sessionStorage.removeItem('serviceSpace');
						sessionStorage.removeItem('servicePrice');
						sessionStorage.removeItem('serviceDate');
						sessionStorage.removeItem('serviceAvailability');
						location.href = "/myBookings";
					}, response => {
						console.log(response.status, response.body);
					});
					

				}
			},
			getUserInfo() {
				this.$http.post(`${process.env["API_URL"]}/services/edit`,{
					"email":sessionStorage.getItem('email')
				},
				{
					headers:{
						'Content-Type': 'application/json'
					}
				}).then(response =>{
					if(response.body) {
						var company = response.body;
						sessionStorage.setItem("idCompany", company.id)
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
		name:"app",
		components: { Navbar },
		validations: {
			s_name: {
				required
			},
			s_duration: {
				required
			},
			spaces: {
				required
			},
			price: {
				required
			}
		}
	}


</script>

<style>
	.container{
		width:50%;
		min-height: 150px;
		margin-bottom: 50px;
	}
	.container-service{
		width:100%;
		text-align:left;
		display:wrap;
		flex-wrap: flex;
	}
	.title{
		width:50%;
		display:inline;
		font-size: 30px;
		font-family: Arial;
		color:#8a2be2;
	}
	.button{
		width:30%;
		display:inline;
		text-align: right;
	}
	.details{
		color:#7f7f7f;
		font-size: 15px;
	}
	.textarea{
		min-height: 125px;
	}
	.input-field{
		text-align: left;
		font-size:20px;
	}
	.circle{
		border-radius:100%;
		width:100px;
		height:100px;
		background-color: #8a2be2;
	}
	p{
		font-size:10px;
	}
	td{
		padding-right:30px;
		padding-top:10px;
		font-size:20px;
	}
	td input{
		border-radius: 50%;
		zoom:2;
	}
</style>