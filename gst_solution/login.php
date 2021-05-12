<?php
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$userid = $_GET['uid'];
		$password = $_GET['password'];
		$table = "user";
	
	
		if($userid == "" || $password == ""){
			echo 'Please enter values of userID and password';
		}
		
		require_once 'dbconnect.php';
		$query = "select * from ".$table." where mobile='".$userid."' and password='".$password."'";
		
		$result = mysqli_query($db,$query);
		
		if(mysqli_num_rows($result)>0){
			$data = mysqli_fetch_array($result);
			$response['success'] = "200";
			$response['message'] = "Login done successfully";
			$response['id'] = $data['id'];
			$response['name'] = $data['name'];
			$response['mobile'] = $data['mobile'];
			$response['state'] = $data['state'];
			$response['district'] = $data['district'];
			$response['email'] = $data['email'];
			
			mysqli_close($db);
			die(print_r(json_encode($response),true));
		}else{
			$response['success'] = "201";
			$response['message'] = "Username / Password is wrong!";
			
			mysqli_close($db);
			die(print_r(json_encode($response),true));
			
		}

	}else{
		$response['success'] = "201";
		$response['message'] = "Server Method Error!";
		
		mysqli_close($db);
		die(print_r(json_encode($response),true));
	}

?>