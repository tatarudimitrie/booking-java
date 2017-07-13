<template>
<body>
<div>
	<navbar></navbar>
	<div class="row text-right">
	<div class="col-sm-6"></div>
	<div class="col-sm-4"><br>
	<b-button type="button" class="btn btn-default circle" aria-label="Right Align">
	<icon name="plus" style="font-size:30px;" scale=3></icon></b-button><br>
 	<span>New service</span>
 	</div>
 	</div>

 	<div class = "container">
	<b-form>
	<span>Name of the service</span>
			
	</b-form>	
	</div>
</div>
</body>
</template>




<script type="text/javascript">
import Navbar from '@/components/Navbar'
export default {
  name: 'app',
  components: { Navbar }

}
</script>


<style>
.container{
	width: 500px;
	height: 220px;
	text-align: center;
	background-color: white;
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
width: 100px;
height: 100px;
}
</style>