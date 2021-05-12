<?php
	date_default_timezone_set('Asia/Kolkata');
	define( 'API_ACCESS_KEY', 'AAAASYUdCs8:APA91bH81oSqG2u5IpO_dQsk9dJUSpuaml9wVQbA26C-UCPrwZv9kw9qhDJ5u6BfePzceJ2IrWvDr3rhODw97GibFC2rKCkI7RF_OE1WEe8jXSHsgWDtZ-JqHOYAELR8JVWgXOUAKQwE');
	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$rid = $_POST['rid'];
		$mobile = $_POST['mobile'];
		$token = $_POST['token'];
		$speed = $_POST['speed'];
		$lat1 = $_POST['lat'];
		$lon1 = $_POST['lon'];
		$vehicle = $_POST['vehicle1'];
		$ctime = Date('H:i');
		$cdate = Date("d/m/Y");
		$tnotice = $_POST['tnotice'];
		$notice = array();
		for($i=0; $i<count($tnotice); $i++){
			array_push($notice,$tnotice[$i]);
		}
		$checkflag = 0;
		
		if($rid == '0'){
			$sremove = "delete from `realtime` where vehicle='$vehicle'";
			mysqli_query($db,$sremove);
			
			
			$sql = "INSERT INTO `realtime`(`vehicle`, `token`, `mobile`, `lat`, `lon`, `speed`, `realtime`, `realdate`, `trafficwarn`, `accidentwarn`, `accidentst`) VALUES ('$vehicle','$token','$mobile','$lat1','$lon1','$speed','$ctime','$cdate',0,0,0)";
			if(mysqli_query($db,$sql)){
				$sh = "select * from `realtime` where vehicle='$vehicle'";
				$tr = mysqli_fetch_array(mysqli_query($db,$sh));
				$rid = $tr['id'];
				$response['rid'] = $rid;
				
			}else{
				$response['rid'] = $rid;
				$response['success'] = "201";
				$response['message'] = "Error in updating database!";
			}
			$stq = "SELECT *, SQRT(POW(69.1 * (lat - $lat1), 2) + POW(69.1 * ($lon1 - lon) * COS(lat / 57.3), 2)) AS distance FROM realtime_notification HAVING distance < 2 and traffic > 0 ORDER BY distance";
			$ts = mysqli_query($db,$stq);
			if(mysqli_num_rows($ts)>0){
				while($row = mysqli_fetch_array($ts)) {
					
							if(count($tnotice)>0){
								for($i = 0; $i<count($tnotice); $i++){
									if($row['id']==$tnotice[$i])
										$checkflag = 1;
								}
							}
							if($checkflag==0){
								array_push($notice,$row['id']);
								
								$res1['data']['title'] = "Traffic Notification";
								$res1['data']['message'] = "Traffic located near by your location!";
								$res1['data']['tnotice'] = $notice;
								$res1['data']['lat'] = $row['lat'];
								$res1['data']['lon'] = $row['lon'];
								$res1['data']['accflag'] = "0";
								
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
								//echo $result;
								
								$response['success'] = "200";
								$response['message1'] = $result;
							}
							
					
				}
			}
		}else{
		
			$chq = "select * from realtime where vehicle='$vehicle'";
			$res = mysqli_query($db,$chq);
			if(mysqli_num_rows($res)>0){
				$data = mysqli_fetch_array($res);
				$lastspeed = $data['speed'];
				$lastlat = $data['lat'];
				$lastlon = $data['lon'];
				
				$update = "update `realtime` set token='$token',lat='$lat1',lon='$lon1',speed='$speed',realtime='$ctime',realdate='$cdate' where vehicle='$vehicle' and id='$rid'";
				mysqli_query($db,$update);
					
					$checktraffic = "SELECT *, SQRT(POW(69.1 * (lat - '$lat1'), 2) + POW(69.1 * ('$lon1' - lon) * COS(lat / 57.3), 2)) AS distance FROM realtime_notification where traffic > 0 HAVING distance < 2 ORDER BY distance";
					$ren = mysqli_query($db,$checktraffic);
					if((mysqli_num_rows($ren)>0)){
						while($row = mysqli_fetch_array($ren)){
							
							for($i = 0; $i<count($tnotice) ; $i++){
								if($row['id']==$tnotice[$i])
									$checkflag=1;
							}
							
							if($checkflag==0){
								
								array_push($tnotice,$row['id']);
								
								$res1['data']['title'] = "Traffic Notification";
								$res1['data']['message'] = "Traffic located near by your location!";
								$res1['data']['tnotice'] = $tnotice;
								$res1['data']['lat'] = $row['lat'];
								$res1['data']['lon'] = $row['lon'];
								$res1['data']['accflag'] = "0";
								
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
								//echo $result;
								
								$response['success'] = "200";
								$response['message1'] = $result;
							
							}
							
						}
					}
				if($speed < 20 && $lastspeed < 20){
					
					$chreltraffic = "select *, SQRT(POW(69.1 * (lat - $lat1), 2) + POW(69.1 * ($lon1 - lon) * COS(lat / 57.3), 2)) AS distance FROM `realtime` having distance < 0.150 and speed<20";
					$dtr = mysqli_query($db,$chreltraffic);
					if(mysqli_num_rows($dtr)>0){
						$counts = mysqli_num_rows($dtr);
						
						$newcheck = "select *, SQRT(POW(69.1 * (lat - $lat1), 2) + POW(69.1 * ($lon1 - lon) * COS(lat / 57.3), 2)) AS distance FROM `realtime_notification` having distance < 0.150";
						$newr = mysqli_query($db,$newcheck);
						if(mysqli_num_rows($newr)==0 && $counts>1){
							$newtraffic = "INSERT INTO `realtime_notification`(`vehicle`, `traffic`, `accident`, `lat`, `lon`, `incident_time`, `incident_date`) VALUES ('$vehicle','$counts','0','$lat1','$lon1','$ctime','$cdate')";
							mysqli_query($db,$newtraffic);
						}else{
							$row1 = mysqli_fetch_array($newr);
							$nid = $row1['id'];
							$query = "update `realtime_notification` set traffic='$counts' where id='$nid'";
							mysqli_query($db,$query);
						}
					}
				}	
			}
		
		}
	
	$response['rid'] = $rid;
	die(print_r(json_encode($response),true));
	}
	/*function send_android_notification($tok, $notice){
		
		
	}*/

?>