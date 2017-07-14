<template>
	<b-navbar toggleable type="inverse" variant="success">
		<b-nav-toggle target="nav_collapse"></b-nav-toggle>
		<b-link class="navbar-brand" to="#">
			<span>Dashboard</span>
		</b-link>
		<b-collapse is-nav id="nav_collapse">
			<b-nav is-nav-bar>
				<b-nav-item>Bookings</b-nav-item>
				<b-nav-item>Calendar</b-nav-item>
				<b-nav-item>Profile</b-nav-item>
			</b-nav>
			<b-nav is-nav-bar class="ml-auto">
			</b-nav>
		</b-collapse>
		<span class="booking">Booking&nbsp</span>
		<span class="booking">App</span>
	</b-navbar>
	<!-- navbar.vue -->
</template>





<!-- Script JavaScript Dashboard -->
<script>
	import Vue from 'vue';
	export default Vue.extend({
	});
</script>


<!-- CSS Dashboard -->
<style>
	.booking{
		font-size: 35px;
		color: white;
		font-family: Arial;
	}
</style>