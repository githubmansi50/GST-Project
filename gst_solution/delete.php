<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$name = $_GET['name'];
		$id = $_GET['id'];
		$uid = $_GET['uid'];
		
		$s = "delete from bookmarks where uid='$uid' and id='$id' and name='$name'";
		if(mysqli_query($db,$s)){
			echo '200';
		}
	}

?>