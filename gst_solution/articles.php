<?php

	require 'dbconnect.php';
	date_default_timezone_set('Asia/Kolkata');
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$cdate = Date("d/m/Y");
		$result = array();
		
		$s = "select * from articles";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data = mysqli_fetch_array($res)){
				array_push($result,array("image"=>$data['image'],
										"heading"=>$data['heading'],
										"body"=>$data['body'],
										"caption"=>$data['caption'],
										"id"=>$data['id']));
			}
			
			$response['success'] = '200';
			$response['result'] = $result;
			die(print_r(json_encode($response),true));
		}else{
			$response['success'] = '201';
			$response['result'] = $result;
			die(print_r(json_encode($response),true));
		}
	}

?>