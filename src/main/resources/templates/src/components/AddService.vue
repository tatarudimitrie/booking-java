<template>
<body>
<div>
	<navbar></navbar>
	<br>
	<div class="container">
		<div class="container-service row">
			<h1 class="title col-sm-6">Add new service</h1>
			<div class="button col-sm-6">
				<button type="submit" class="btn btn-default circle" aria-label="Right Align">
					<icon name="plus" scale="3" style="color:white"></icon>
				</button>
				<p>Add service</p>
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
		<b-form-input textarea v-model="textarea" placeholder="Enter your description" class="textarea"></b-form-input>
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
		<div id="boxes">
		
</div>
</div>

</div>
</div>
</body>
</template>



<script>
	import Navbar from '@/components/Navbar'
	import { required, email, minLength, between } from 'vuelidate/lib/validators'
	export default {
		data () {
			return {
				s_name: '',
				s_duration:'',
				description: '',
				spaces:'',
				price:'',
				textarea:''
			}
		},
		methods: {
			format(value) {
				return value.toLowerCase();
			},
			submit(){
				console.log(this.s_name, this.s_duration, this.description, this.spaces, this.price);
			}
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

	// function newCheckbox(name, id){
	// 	var checkB = document.createElement('input');
	// 		checkB.type = 'checkbox';
	// 		checkB.name = name;
	// 		checkB.id = id;
	// 	return checkB;
	// };
	// var iDiv = document.createElement('div');
	// iDiv.innerHTML =  newCheckbox("box", "b1");
	// document.getElementById("boxes").innerHTML += iDiv;
</script>

<style>
	.container{
		width:50%;
		min-height: 150px;
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
</style>