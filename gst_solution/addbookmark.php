<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$bid = $_GET['bid'];
		$type = $_GET['type'];
		$uid = $_GET['uid'];
		$name = $_GET['name'];
		
		$chsq = "select * from bookmarks where uid='$uid' and type='$type' and bid='$bid'";
		$res = mysqli_query($db,$chsq);
		if(mysqli_num_rows($res)>0){
			$s = "update bookmarks set name='$name' where uid='$uid' and type='$type' and bid='$bid'";
			if(mysqli_query($db,$s)){
				echo '202';
			}else{
				echo '201';
			}
		}else{
			$s = "INSERT INTO `bookmarks`(`type`, `name`, `bid`, `uid`) VALUES ('$type','$name','$bid','$uid')";
			if(mysqli_query($db,$s)){
				echo '200';
			}else{
				echo '201';
			}
		}
	}

?>