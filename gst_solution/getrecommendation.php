<?php

	require 'dbconnect.php';
	
	$admin = $_GET['admin'];
	
	$result = array();
	
	$sql1 = "select * from material";
				$res1 = mysqli_query($db,$sql1);
				if(mysqli_num_rows($res1)>0){
					while($data1 = mysqli_fetch_array($res1)){
						array_push($result,array('title'=>$data1['title'],
													'id'=>$data1['id'],
													'type'=>$data1['type'],
													'attachment'=>$data1['attachment']));
					}
					$response['success'] = '200';
					$response['result'] = $result;
				}else{
				
					$response['success'] = '201';
					$response['result'] = "No data found!";
				}
		die(print_r(json_encode($response),true));




?>