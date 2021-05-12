<?php

	require 'dbconnect.php';
	$mobile = $_REQUEST['mobile'];
	$fid = $_REQUEST['fid'];
	
			$myfile = fopen("newfile.txt","w") or die("Unable to open file!");
			fwrite($myfile, $fid);
			fclose($myfile);

			$s = "update user set token='$fid' where mobile='$mobile'";
			if(mysqli_query($db,$s)){
				echo '200';
			}else{
				echo '201';
			}

	
?>