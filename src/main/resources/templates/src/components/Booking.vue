<template>
	<div class="container">

		<div class="header row">
			<div class="logo col-sm-4">
				<span class="textLogo">LOGO</span>
			</div>
			<div class="text-left col-sm-8">
				<p>Company name</p>
				<p>Description</p>
			</div>
		</div>
	</div>
</div>
</template>

<script>
	
	export default{
		data(){
			return {
			};
		}

	}

</script>

<style>
	.container{
		width:70%;
	}
	.header{
		height:200px;
	}
	.logo{
		border-radius: 100%;
		color:white;
		background-color: blue;
		width: auto;
		height: 80%;
		font-size:50px;
	}
</style>