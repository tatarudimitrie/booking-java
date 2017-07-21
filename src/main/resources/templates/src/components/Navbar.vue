<template>
<b-navbar toggleable type="inverse" variant="success">

    <b-nav-toggle target="nav_collapse"></b-nav-toggle>

    <b-link class="navbar-brand" to="/Dashboard">
      <span>Dashboard</span>
    </b-link>

    <b-collapse is-nav id="nav_collapse">

      <b-nav is-nav-bar>
        <b-nav-item to="/MyBookings">Bookings</b-nav-item>
        <b-nav-item to="/Calendar">Calendar</b-nav-item>
        <b-nav-item to="/Profile">Profile</b-nav-item>

      </b-nav>
      <b-nav is-nav-bar class="ml-auto">
      </b-nav>
    </b-collapse>
   <span class="booking">Booking&nbsp</span>
   <span class="booking">App&nbsp|</span>
   <button class="btn btn-success" @click = "SignOut">
   <span class="logOut">Log Out</span>
   </button>
   <!-- <div>
   <icon name="angle-down" scale="2" style="color:white"></icon>
   </div> -->
</b-navbar>

<!-- navbar.vue -->
</template>





<!-- Script JavaScript Dashboard -->
<script>
	import Vue from 'vue';
	export default Vue.extend({
    methods:{
      SignOut(){
        sessionStorage.clear();
        location.href = "/Login"
      }
    }
	});
</script>


<!-- CSS Dashboard -->
<style>
	.booking{
		font-size: 35px;
		color: white;
		font-family: Arial;
	}
  .logOut{
    font-size: 20px;
    color: #006600;
  }
</style>