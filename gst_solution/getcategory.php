<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$field = $_GET['field'];
		$result = array();
		
		$s = "select distinct(productcategory) from calculatordata where field='$field'";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data = mysqli_fetch_array($res)){
				array_push($result,$data['productcategory']);
			}
			$response['success'] = "200";
			$response['category'] = $result;
			die(print_r(json_encode($response),true));
		}else{
			echo 'No category found!';
		}
	}

?>