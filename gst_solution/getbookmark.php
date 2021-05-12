<?php

	require 'dbconnect.php';
	if($_SERVER['REQUEST_METHOD']=='GET'){
		$name = $_GET['name'];
		$id = $_GET['id'];
		$uid = $_GET['uid'];
		$type = $_GET['type'];
		$flag = $_GET['flag'];
		
		if($flag=='0'){
		
			$s = "select * from bookmarks where id='$id' and name='$name' and uid='$uid'";
			$res = mysqli_fetch_array(mysqli_query($db,$s));
			$type = $res['type'];
			$bid = $res['bid'];
		}else{
			$bid = $id;
		}
			
			if($type=='2'){
				$response['image'] = "no image";
				
				$sq = "select * from knowledge_solution where id='$bid'";
				$res1 = mysqli_query($db,$sq);
				if(mysqli_num_rows($res1)>0){
					$data = mysqli_fetch_array($res1);
					$response['success'] = '200';
					$response['heading'] = $data['question'];
					$response['body'] = $data['answer'];
					die(print_r(json_encode($response),true));
				}else{
					$response['heading'] = "Page error 404";
					$response['body'] = "Page erro 404";
					die(print_r(json_encode($response),true));
				}
			}else if($type=='1'){
				$sq = "select * from news where id='$bid'";
				$res1 = mysqli_query($db,$sq);
				if(mysqli_num_rows($res1)>0){
					$data = mysqli_fetch_array($res1);
					$response['success'] = '200';
					$response['heading'] = $data['heading'];
					$response['body'] = $data['body'];
					$image = $data['image'];
					if($image!="no image"){
						$response['image'] = "news/".$data['image'];
					}else{
						$response['image'] = $data['image'];
					}
					die(print_r(json_encode($response),true));
				}else{
					$response['heading'] = "Page error 404";
					$response['body'] = "Page erro 404";
					$response['image'] = "no image";
					die(print_r(json_encode($response),true));
				}
			}else if($type=='3'){
				$sq = "select * from articles where id='$bid'";
				$res1 = mysqli_query($db,$sq);
				if(mysqli_num_rows($res1)>0){
					$data = mysqli_fetch_array($res1);
					$response['success'] = '200';
					$response['heading'] = $data['heading'];
					$response['body'] = $data['body'];
					$image = $data['image'];
					if($image!="no image"){
						$response['image'] = "articles/".$data['image'];
					}else{
						$response['image'] = $data['image'];
					}
					die(print_r(json_encode($response),true));
				}else{
					$response['heading'] = "Page error 404";
					$response['body'] = "Page erro 404";
					$response['image'] = "no image";
					die(print_r(json_encode($response),true));
				}
			}
		
	}


?>