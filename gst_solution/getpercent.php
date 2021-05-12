<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$field = $_GET['field'];
		$service = $_GET['service'];
		$category = $_GET['category'];
		$price = $_GET['price'];
		$qauntity = $_GET['qauntity'];
		
		$s = "select * from calculatordata where field='$field' and productcategory='$category' and minimum_price<='$price' and max_price>='$price' and min_qauntity<='$qauntity' and max_qauntity>='$qauntity'";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			$data = mysqli_fetch_array($res);
			if($service=='CGST'){
				$percent = $data['cgst_percentage'];
			}else if($service=='SGST'){
				$percent = $data['sgst_percentage'];
			}else{
				$percent = $data['cgst_sgst_percent'];
			}
		
			$response['success'] = "200";
			$response['percent'] = $percent;
			die(print_r(json_encode($response),true));
		}else{
			echo 'No data found!';
		}
	}

?>