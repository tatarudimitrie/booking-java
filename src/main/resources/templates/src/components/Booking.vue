<template>
	<div class="container">
		<div class="company__header row">
			<div class="col-sm-4">
				<br><b-button type="button" class="btn btn-default circle" v-if="Logo" aria-label="Left Align"><span>Logo</span></b-button>
				<img :src="file" v-else class="image" />
			</div>
			<div class="col-sm-8 text-left">
				<div><h2>{{company.name}}</h2></div>
				<hr>
				<div><h4>{{company.description}}</h4></div>
			</div>
		</div>
		<b-card>
			<div class = "container">
				<b-form>
					<div class="cardItem text-left" v-for="service in services">
						<!-- Standard -->
						<b-card :header = "service.name"
						class="mb-2 bg-info header"
						:sub-title = "service.description"
						show-footer>
						<small slot="footer" id="footer">
							<div class="table text-center">
								<div class="theader row">
									<div class="col-sm-3">
										<p>Availability</p>
									</div>
									<div class="col-sm-3">
										<p>Spaces</p>
									</div>
									<div class="col-sm-3">
										<p>Duration</p>
									</div>
									<div class="col-sm-3">
										<p>Price</p>
									</div>
								</div>
								<div class="tbody row bg-info">
									<div class="col-sm-3">
										<p>{{service.date}}</p>
									</div>
									<div class="col-sm-3">
										<p>{{service.free_space}}</p>
									</div>
									<div class="col-sm-3">
										<p>{{service.duration}}</p>
									</div>
									<div class="col-sm-3">
										<p>{{service.price}}</p>
									</div>
								</div>
							</div>
							<b-button @click="toggleModal('open', 'modal-1'); getSchedule(service)" ref="btnShow" class="btn btn-success">
								BOOK NOW
							</b-button>
						</small>
					</b-card>
				</div>
				<div>

					<!-- MODAL 1 -->
					<b-modal id="modal-1" title="Make your booking">
						<form name="form1" @submit.stop.prevent="submit" class="formular">
							<div class="row">
								<div class="input-field col-lg-6">
									<label>Name</label>
									<b-form-input v-model="name"
									type="text"
									placeholder="Enter your name"
									:state="name.length?'success':'warning'"
									></b-form-input>
									<p v-if="!$v.name.required">This field is required!</p>
								</div>
								<div class="input-field col-lg-6">
									<label>Email</label>
									<b-form-input v-model="email"
									type="email"
									placeholder="Enter email"
									:state="email.length?'success':'warning'"
									></b-form-input>
									<p v-if="!$v.email.email">This field must be an email!</p>
								</div>
								<div class="input-field col-lg-6">
									<label>Phone number</label>
									<b-form-input v-model="phone"
									type="text"
									placeholder="Enter your phone number"
									:state="phone.length?'success':'warning'"
									></b-form-input>
									<p v-if="!$v.phone.required">This field is required!</p>
								</div>
								<div class="input-field button-wrapper col-lg-6">
									<b-button class="btn-success" @click="toggleModal('open', 'modal-2'); toggleModal('close', 'modal-1')" ref="btnShow">
										NEXT
									</b-button>
								</div>
							</div>
						</form>
					</b-modal>
					<!-- MODAL 2 -->
					<b-modal id="modal-2" title="Make your booking">
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
											<label :for="day + hour"><input v-model="dayAndHour" type="radio" name="same" :id="day + hours.indexOf(hour)" disabled="true"></label>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row">
							<div class="input-field button-wrapper col-lg-6">
								<b-button class="btn-success" @click="toggleModal('open', 'modal-1'); toggleModal('close', 'modal-2')" ref="btnShow">
									BACK
								</b-button>
							</div><div class="input-field button-wrapper col-lg-6">
							<b-button class="btn-success" @click="toggleModal('open', 'modal-3'); toggleModal('close', 'modal-2'); sendBooking()" ref="btnShow">
								SEND BOOKING
							</b-button>
						</div>
					</div>
				</b-modal>
				<!-- MODAL 3 -->
				<b-modal id="modal-3" title="Make your booking">
					<img src="../assets/modalok.png" alt="" width="150"><br>
					<span>In a couple of seconds a confirmation email will be sent to your email address with all the details for your reservation. Thank you for using our services!</span><br>
					<form @submit.stop.prevent="submit" class="formular">
						<div class="row">
							<div class="input-field button-wrapper col-lg-12">
								<b-button class = "btn-success" @click=" toggleModal('close', 'modal-3')" ref="btnShow">
									CLOSE
								</b-button>
							</div>
						</div>
					</form>
				</b-modal>
			</div>
		</b-form>	
	</div>
</b-card>
</div>  
</template>



<script>
	import Vue from 'vue'
	import { required, email, phone } from 'vuelidate/lib/validators'
	export default {
		data () {
			return {
				goNextStep: true,
				modalshow:'',
				phone: '',
				email: '',
				name: '',
				price: '',
				duration: '',
				spaces: '',
				availability: '',
				Logo: true,
				idService:'',
				services:[],
				company:{
					"name":"andrei_company",
					"description":"this is my personal description that is way too long to be on a single row",
					"id":"41"
				},
				schedule:[],
				dayAndHour: '',
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
			clearName() {
				this.name = '';
			},
			submit() {
			},
			toggleModal(action, modalId) {
				
				if(!this.$v.$invalid) {
					this.goNextStep = true;
				}

				if(this.goNextStep ) {
					if(action === 'open') {
						this.$root.$emit('show::modal', modalId);
					} else {
						this.$root.$emit('hide::modal', modalId);					}
						this.goNextStep = false;
					}

				},
				getCompany: function (){
					this.$http.get(`${process.env["API_URL"]}/companies/id/`+ sessionStorage.getItem("idCompany"),
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
				getServices: function (){
					this.$http.post(`${process.env["API_URL"]}/services/company`,{
						"id": sessionStorage.getItem("idCompany")
					},
					{
						headers:{
							'Content-Type': 'application/json'
						}
					}).then(response =>{
						this.services = response.body;


					})
					.then(error => {
						console.log('error: ', error);
					})
				},
				getSchedule(itemObject){
					this.idService = itemObject.id
					this.$http.post(`${process.env["API_URL"]}/schedules/service`,{
						"id": itemObject.id
					},
					{
						headers:{
							'Content-Type': 'application/json'
						}
					}).then(response =>{
						this.schedule = response.body;

						for(var i=0; i<this.schedule.length;i++){
							var test = this.schedule[i].time;
							document.getElementById(test).disabled = false
						};
						console.log("Raspuns bun");
						
					}).then(error => {
						console.log('response: ', error);
					})
				},
				sendBooking(){
					for(var i=0; i<this.schedule.length;i++){
						var test = this.schedule[i].time;
						var choice = document.getElementById(test);
						if(choice){
							this.dayAndHour = test;
						}
					};

					this.$http.post(`${process.env["API_URL"]}/bookings/add`,{
						"service":{
							"id": this.idService
						},
						"name": this.name,
						"email": this.email,
						"date": this.dayAndHour,
						"phone": this.phone
					},
					{
						headers:{
							'Content-Type': 'application/json'
						}
					}).then(response =>{
						debugger;


					})
					.then(error => {
						console.log('error: ', error);
					})
				}
			},
			created(){
				this.getCompany();
				this.getServices();
			},
			validations: {
				name: { required },
				email: { required, email },
				phone: { required }
			}
		}
	</script>

	<style>
		.company__header ul {
			list-style-type: none;
			padding: 0;
			margin: 0;
			display: inline-block;
		}
		.modal-footer{
			display: none;
		}

		.button-wrapper {
			display: flex;
			align-items: flex-end;
		}
		.col-sm-8{
			font-weight: bold;
		}
		.button-wrapper button {
			width: 150px;
			margin: 0 auto;
		}
		td{
			padding-left: 7px;
			padding-right:10px;
			padding-top:10px;
			font-size:20px;
		}
		td input{
			border-radius: 50%;
			zoom:1.5;
		}
		.btn-succes{
			padding: 15px;
		}
		.circle{
			border-radius: 100%;
			background-color: #8A2BE2;
			color:white;
			width: 180px;
			height: 180px;
		}
		.card {
			border: none;
		}

		.card-footer {
			padding: 0;
		}
		.header, .theader{
			font-size: 20px;
			font-weight: bold;
		}
		.container{
			max-width: 750px;
			height: 155px;
			text-align: center;
			margin: 0 auto;
			border-radius: 0 auto;
			margin-top: 20px;
		}
		.btn .btn-default {
			display: flex;
			align-items: center;
			justify-content: center;
		}
		.cardItem{
			padding:20px;
		}
		.row{
			margin:0;
		}
	</style>