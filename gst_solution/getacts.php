<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$result = array();
		$s = "select * from acts";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data = mysqli_fetch_array($res)){
				array_push($result,$data['heading']."\nAct : ".$data['act']."\nSection : ".$data['section']);
			}
			
			$response['success'] = "200";
			$response['result'] = $result;
			die(print_r(json_encode($response),true));
		}else{
			$response['success'] = "201";
			die(print_r(json_encode($response),true));
		}
	}

?>