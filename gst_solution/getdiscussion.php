<?php

	require 'dbconnect.php';
	date_default_timezone_set('Asia/Kolkata');
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$cdate = Date("d/m/Y");
		$cvdate = Date("d");
		
		$result = "";
		$s = "select * from discussion where daten='$cdate'";
		$res = mysqli_query($db,$s);
		if(mysqli_num_rows($res)>0){
			while($data = mysqli_fetch_array($res)){
				$result = $result."\n".$data['uname']." : ".$data['post'];
			}
			
			echo $result;
		}else{
			
			while($result==""){
				$cvdate = $cvdate - 1;
				$tdate = Date("/m/Y");
				$tdate = $cvdate.$tdate;
				
				$s = "select * from discussion where daten='$tdate'";
				$res = mysqli_query($db,$s);
				if(mysqli_num_rows($res)>0){
					while($data = mysqli_fetch_array($res)){
						$result = $result."\n".$data['uname']." : ".$data['post'];
					}
				}
			}
			
			echo $result;
			
		}
		
	}


?>