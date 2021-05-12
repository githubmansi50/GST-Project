<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$uid = $_GET['uid'];
		$sql = "select * from bookmarks where uid='$uid'";
		 
		$res = mysqli_query($db,$sql);
		 
		$result = array();
		 
		while($row = mysqli_fetch_array($res)){
			array_push($result,
				array('name'=>$row['name'],
				'id'=>$row['id']
			));
		}
		 
		echo json_encode(array("result"=>$result));
		 
		mysqli_close($db);
	}
 
?>