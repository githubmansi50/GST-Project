<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$word = $_GET['searchword'];
		$flag = 0;
		
		$result = array();
		
		$s = "select * from news where heading like'%$word%' or body like'%$word%'";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($row = mysqli_fetch_array($res)){
				$flag=1;
				array_push($result,
					array('name'=>$row['heading'],
					'id'=>$row['id'],
					'type'=>'1'
				));
			}
		}
		
		$s = "select * from knowledge_solution where question like'%$word%' or answer like'%$word%'";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($row = mysqli_fetch_array($res)){
				$flag=1;
				array_push($result,
					array('name'=>$row['question'],
					'id'=>$row['id'],
					'type'=>'2'
				));
			}
		}
		
		$s = "select * from articles where heading like'%$word%' or body like'%$word%'";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($row = mysqli_fetch_array($res)){
				$flag=1;
				array_push($result,
					array('name'=>$row['heading'],
					'id'=>$row['id'],
					'type'=>'2'
				));
			}
		}
		
		if($flag==1){
			$response['success'] = '200';
			$response['result'] = $result;
			die(print_r(json_encode($response),true));
		}else{
			$response['success'] = '201';
			die(print_r(json_encode($response),true));
		}
	}

?>