<template>
<b-card>
<div class = "container">
	<b-form>
		<div>
			<!-- Standard -->
			<b-card header="TITLE"
			class="mb-2"
			sub-title="DESCRIPTION"
			show-footer>
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
								<td>{{availability}}</td>
								<td>{{spaces}}</td>
								<td>{{duration}}</td>
								<td>{{price}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</small>
		</b-card>
	</div>
</b-form>	
</div>
</b-card>
</template>