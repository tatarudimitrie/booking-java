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
				<div class = "container" v-for="item in database">
					<b-form>
						<div>
							<!-- Standard -->
							<b-card header="item.name"
							class="mb-2"
							sub-title="item.description"
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
												<td>{{item.availability}}</td>
												<td>{{item.spaces}}</td>
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
				"database": getDatabase()
		}

	},
	components: { Navbar },
	methods:{
		submit(){
			location.href="/AddService"
		},
		getDatabase(){
			this.$http.get("http://192.168.151.51:8080/services")
		}
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