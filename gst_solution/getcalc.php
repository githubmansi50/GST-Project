<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		
		$result = array();
		
		$s = "select distinct(field) from calculatordata";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data = mysqli_fetch_array($res)){
				array_push($result,$data['field']);
			}
			$response['success'] = "200";
			$response['field'] = $result;
			die(print_r(json_encode($response),true));
		}else{
			echo 'No category found!';
		}
	}

?>