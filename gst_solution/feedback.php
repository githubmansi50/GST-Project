<?php
	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$name = $_GET['name'];
		$mobile = $_GET['mobile'];
		$message = $_GET['message'];
		$email = $_GET['email'];
		
		
	$s = "INSERT INTO `user`(`name`, `mobile`, `email`, `message`) VALUES ('$name','$mobile','$email','$message')";
			if(mysqli_query($db,$s)){
				echo '200';
			}else{
				echo'201';
			}



?>