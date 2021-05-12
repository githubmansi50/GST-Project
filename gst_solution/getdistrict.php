<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$state = $_GET['state'];
		
		$districts = array();
		$s = "select distinct(district) from `states` where state='$state'";
		$res= mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data= mysqli_fetch_array($res)){
				array_push($districts,$data['district']);
			}
			$response['success'] = '200';
			$response['district'] = $districts;
			die(print_r(json_encode($response),true));
		}else{
			$response['success'] = '201';
			die(print_r(json_encode($response),true));
		}
	}

?>