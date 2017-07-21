<template>
	<body>
		<div>
			<navbar></navbar>
			<div class="row text-right">
				<div class="col-sm-6"></div>
				<div class="col-sm-4"><br>
					<b-button @click = "submit" type="button" class="btn btn-default circle" aria-label="Right Align">
						<icon name="plus" style="font-size:30px;" scale=3></icon></b-button><br>
						<span>New service</span>
					</div>
				</div>
				<div class = "container left-align" v-for="item in services">
					<b-form>
						<div class="text-left">
							<div class="text-right">
								<b-button @click="removeCard(item)">
									<icon name="trash" scale="1.5"></icon>
								</b-button>
								<b-button @click="editCard(item)">
									<icon name="pencil" scale="1.5"></icon>
								</b-button>
							</div>
							<!-- Standard -->
							<b-card :header="item.name"
							class="mb-2 bg-info"
							:sub-title="item.description"
							show-footer
							>
							<small slot="footer" class="" id="footer">
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
											<p>{{item.date}}</p>
										</div>
										<div class="col-sm-3">
											<p>{{item.free_space}}</p>
										</div>
										<div class="col-sm-3">
											<p>{{item.duration}}</p>
										</div>
										<div class="col-sm-3">
											<p>{{item.price}}</p>
										</div>
									</div>
								</div>
							</small>
						</b-card>
					</div>
				</b-form>	
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
				services: []
			}
		},
		components: { Navbar },
		methods:{
			submit(){
				location.href="/AddService"
			},
			removeCard(serviceObject){
				this.$http.delete(`${process.env["API_URL"]}/services/delete/` + serviceObject.id);
				let index = this.services.indexOf(serviceObject);
				this.services.splice(index, 1);
			},
			editCard(serviceObject){
				sessionStorage.setItem('idService', serviceObject.id);
				sessionStorage.setItem('serviceName', serviceObject.name);
				sessionStorage.setItem('serviceDescription', serviceObject.description);
				sessionStorage.setItem('serviceAvailability', serviceObject.date);
				sessionStorage.setItem('servicePrice', serviceObject.price);
				sessionStorage.setItem('serviceSpace', serviceObject.free_space);
				sessionStorage.setItem('serviceDuration', serviceObject.duration);
				location.href = "/EditService"
			},
			getServices: function (){
				this.$http.post(`${process.env["API_URL"]}/services/company`, {
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
			getUserInfo() {
				this.$http.post(`${process.env["API_URL"]}/companies/admin`,{
					"email":sessionStorage.getItem('email')
				},
				{
					headers:{
						'Content-Type': 'application/json'
					}
				}).then(response =>{
					if(response.body) {
						var company = response.body;
						sessionStorage.setItem("idCompany", company.id);
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
			this.getServices();
		}
	}
</script>


<style>
	.container{
		width: 50%;
		min-height: 20px;
		text-align: center;
		/*background-color: white;*/
		margin: 0 auto;
		border-radius: 0 auto;
		margin-top: 20px;
	}
	body{
		background-color: #f3f3f3;
	}
	span{
		text-align: left;
	}
	.table{
		font-size:15px;
	}
	.tbody{
		width:100%;
	}
	.circle{
		border-radius: 100%;
		background-color: #8A2BE2;
		color:white;
		width: 80px;
		height: 80px;
	}
	.card-footer {
		padding: 0;
	}
	.row{
		margin:0;
	}
</style>