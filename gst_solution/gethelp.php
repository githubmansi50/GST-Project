<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$result = array();
		$s = "select * from help_services";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data = mysqli_fetch_array($res)){
				array_push($result,$data['name']."\n".$data['body']."\n".$data['contact']."\n".$data['cost']);
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