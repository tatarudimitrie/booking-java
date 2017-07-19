<template>
	<div>

		<b-button @click="toggleModal('open', 'modal-1');" ref="btnShow">
			BOOK NOW
		</b-button>





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
								<label :for="day + hour"><input type="checkbox" :id="day + hours.indexOf(hour)"></label>
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
				<b-button class="btn-success" @click="toggleModal('open', 'modal-3'); toggleModal('close', 'modal-2')" ref="btnShow">
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
				if (this.$v.email.email && this.$v.name.required && this.$v.phone.required) {
					this.$http.post("http://192.168.151.51:8080/bookings", {
						"name": this.name,
						"email": this.email,
						"phone": this.phone
					},
					{
						headers:{
							
							'Content-Type': 'application/json'
						}
					}).then(response => {
                        // if(response.status === 200){
                            // location.href= '/login';
                            debugger;
                            console.log('response: ', response);
                        // }else{
                        //  document.getElementById("hidden").style.visibility = "visible";
                        // }
                        
                    }, response => {
                    	console.log(response);
                    })
				}
			},
			toggleModal(action, modalId) {
				debugger;
				if(!this.$v.$invalid) {
					this.goNextStep = true;
				}

				if(this.goNextStep ) {
					if(action === 'open') {
						this.$root.$emit('show::modal', modalId);
					} else {
						this.$root.$emit('hide::modal', modalId);
						this.$refs.btnShow.$el.focus();
					}
					this.goNextStep = false;
				}
				
			}
		},
		validations: {
			name: { required },
			email: { required, email },
			phone: { required }
		}
	}
</script>

<style>
	.modal-footer{
		display: none;
	}

	.button-wrapper {
		display: flex;
		align-items: flex-end;
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
		padding-left: 50px;
	}
</style>