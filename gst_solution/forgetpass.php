<?php
	require 'dbconnect.php';
	if(isset($_GET["contact"]))
	{
		$mob=$_GET['contact'];
		$ck_query='select * from user where mobile="'.$mob.'"'; 
		$result=mysqli_query($db,$ck_query);
		if(mysqli_num_rows($result)>0)
		{
				   $data=mysqli_fetch_array($result);
				   
				   $msgC = "Your Password For GST Application is ".$data['password'].". Do Not Share Your Password With Anyone.";
			$myURL = "http://www.smswave.in/panel/sendsms.php";

			$user = "vinodotp";
			$password = "123123";
			$sender = "CAPTCH";
			
			
			$ch = curl_init();

			curl_setopt($ch, CURLOPT_URL,"http://www.smswave.in/panel/sendsms.php?");
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS,
						"PhoneNumber=".$mob."&Text=".$msgC."&user=vinodotp&password=123123&sender=CAPTCH");

			// Get server response ...
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

			$result = curl_exec ($ch);

			curl_close ($ch);

			// further processing ....
			if ($result == "OK") 
			{ 
				$response['message'] = "Password Send Successfully"; 
			} 
			else 
			{
				$response['message'] = "Faild To Send Password"; 
			}
		}
		else
		{
				   $response['success'] = "201";
			       $response['message'] = "Invalid Mobile Number";
		}
		die(print_r(json_encode($response),true));
   
    }
	else
	{
	               $response['success'] = "202";
			       $response['message'] = "Enter Mobile No";
				   die(print_r(json_encode($response),true));
	}
	
?>