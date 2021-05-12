<?php
	
	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$states = array();
		$s = "select distinct(state) from `states`";
		$res= mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data= mysqli_fetch_array($res)){
				array_push($states,$data['state']);
			}
			$response['success'] = '200';
			$response['state'] = $states;
			die(print_r(json_encode($response),true));
		}else{
			$response['success'] = '201';
			die(print_r(json_encode($response),true));
		}
	}


?>