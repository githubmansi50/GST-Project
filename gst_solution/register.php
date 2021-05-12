<?php
	require 'dbconnect.php';
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$fname = $_REQUEST['fname'];
		$lname = $_REQUEST['lname'];
		$password = $_REQUEST['password'];
		$mob = $_REQUEST['mobile'];
		$address = $_REQUEST['address'];
		$email = $_REQUEST['email'];
		$gender = $_REQUEST['gender'];
		$dob = $_REQUEST['dob'];
		$adhar = $_REQUEST['adhar'];
		$image = $_REQUEST['image'];
	

		//echo '$name';
		
			require_once('dbconnect.php');
			$sql = "SELECT * FROM `user_info` WHERE `mobno`='$mob' or `aadhar`='$adhar'";
			
			$check = mysqli_query($db,$sql);
			
			if(mysqli_num_rows($check)>0){
				$response['success'] = "201";
				$response['message'] = "Mobile no or Aadhar Number already registered!";
				mysqli_close($db);
				die(print_r(json_encode($response),true));
			}else{				
					$s = "select max(id) from `user_info`";
					$res = mysqli_query($db,$s);
					$data = mysqli_fetch_array($res);
					$id = $data[0]+1;
				$path = "uploads/$id.png";
				$imagename="$id.png";
				file_put_contents($path,base64_decode($image));
				
				$sql = "INSERT INTO `user_info`(`fname`, `lname`, `gender`, `age`, `email`, `mobno`, `password`, `aadhar`, `ppicture`) VALUES ('$fname','$lname','$gender','$dob','$email','$mob','$password','$adhar','$imagename')";
				if(mysqli_query($db,$sql))
				{
					$s = "select max(id) from `user_info`";
					$res = mysqli_query($db,$s);
					$data = mysqli_fetch_array($res);
					$response['id'] = $data[0];
					$response['success'] = "200";
					$response['message'] = "Registration done.";
					mysqli_close($db);
					die(print_r(json_encode($response),true));
				}else{
					$response['success'] = "201";
					$response['message'] = "Error in inserting values in database.";
					mysqli_close($db);
					die(print_r(json_encode($response),true));
				}
			}
}else{
					$response['success'] = "201";
					$response['message'] = "Server Method Error!";
					mysqli_close($db);
					die(print_r(json_encode($response),true));
}
	


?>