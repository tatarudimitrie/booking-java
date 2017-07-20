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
							class="mb-2"
							:sub-title="item.description"
							show-footer
							>
							<small slot="footer" class="text-muted" id="footer">
								<div class="table">
									<table class="table">
										<thead>
											<tr>
												<th>Availability</th>
												<th>Spaces</th>
												<th>Duration</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>{{item.date}}</td>
												<td>{{item.free_space}}</td>
												<td>{{item.duration}}</td>
												<td>{{item.price}}</td>
											</tr>
										</tbody>
									</table>
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
				this.$http.delete("http://192.168.151.51:8080/services/delete/" + serviceObject.id);
				let index = this.services.indexOf(serviceObject);
				this.services.splice(index, 1);
			},
			editCard(serviceObject){
				sessionStorage.setItem('id_service', serviceObject.id);
				sessionStorage.setItem('serviceName', serviceObject.name);
				sessionStorage.setItem('serviceDescription', serviceObject.description);
				sessionStorage.setItem('serviceAvailability', serviceObject.date);
				sessionStorage.setItem('servicePrice', serviceObject.price);
				sessionStorage.setItem('serviceSpace', serviceObject.free_space);
				sessionStorage.setItem('serviceDuration', serviceObject.duration);
				location.href = "/EditService"
			},
			getServices: function (){
				this.$http.post("http://192.168.151.51:8080/services/company", {
					"id": sessionStorage.getItem("id_company")
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
				this.$http.post("http://192.168.151.51:8080/companies/admin",{
					"email":sessionStorage.getItem('email')
				},
				{
					headers:{
						'Content-Type': 'application/json'
					}
				}).then(response =>{
					if(response.body) {
						var company = response.body;
						sessionStorage.setItem("id_company", company.id);
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
		width: 500px;
		height: 220px;
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
	.circle{
		border-radius: 100%;
		background-color: #8A2BE2;
		color:white;
		width: 80px;
		height: 80px;
	}
	.table td {
		text-align: left;
		border: 0;
	}

	.table tr th {
		border: 0;
	}

	.table {
		margin: 0;
	}

	.card-footer {
		padding: 0;
	}
</style>