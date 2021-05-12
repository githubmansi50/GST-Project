<?php
	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$name = $_GET['name'];
		$mobile = $_GET['mobile'];
		$password = $_GET['password'];
		$state = $_GET['state'];
		$district = $_GET['district'];
		$email = $_GET['email'];
		
		$chs = "select * from user where mobile='$mobile' or email='$email'";
		$res = mysqli_query($db,$chs);
		if(mysqli_num_rows($res)>0){
			echo '202';			
		}else{
			$s = "INSERT INTO `user`(`name`, `mobile`, `state`, `district`, `email`, `password`) VALUES ('$name','$mobile','$state','$district','$email','$password')";
			if(mysqli_query($db,$s)){
				echo '200';
			}else{
				echo'201';
			}
		}
		
	}



?>