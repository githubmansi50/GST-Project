<?php

	define( 'API_ACCESS_KEY', 'AAAASYUdCs8:APA91bH81oSqG2u5IpO_dQsk9dJUSpuaml9wVQbA26C-UCPrwZv9kw9qhDJ5u6BfePzceJ2IrWvDr3rhODw97GibFC2rKCkI7RF_OE1WEe8jXSHsgWDtZ-JqHOYAELR8JVWgXOUAKQwE');
	require 'dbconnect.php';
	date_default_timezone_set('Asia/kolkata');
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$uid = $_GET['uid'];
		$uname = $_GET['uname'];
		$msg = $_GET['msg'];
		
		$cdate = Date("d/m/Y");
		$ctime = Date("H:i");
		$ttime = Date("d/m/Y H:i");
		
		$s = "INSERT INTO `discussion`(`post`, `uid`, `uname`, `daten`, `timen`, `combinetime`) VALUES ('$msg','$uid','$uname','$cdate','$ctime','$ttime')";
		if(mysqli_query($db,$s)){
			$sq = "select * from user where not token='not generated'";
			$res = mysqli_query($db,$sq);
			if(mysqli_num_rows($res)>0){
				while($data = mysqli_fetch_array($res)){
					$token = $data['token'];
					
								$res1['data']['title'] = "Discussion post";
								$res1['data']['message'] = $uname." : ".$msg;
								
								
								$fields = array(
									 'to' => $token,
									 'data' => $res1
								);

						
						 
								$headers = array
								(
									'Authorization: key=' . API_ACCESS_KEY,
									'Content-Type: application/json'
								);
								 
								$ch = curl_init();
								curl_setopt( $ch,CURLOPT_URL, 'https://android.googleapis.com/gcm/send' );
								curl_setopt( $ch,CURLOPT_POST, true );
								curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
								curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
								curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
								curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
								$result = curl_exec($ch );
								curl_close( $ch );
				}
				
				echo '200';
			}
		}
	}

?>