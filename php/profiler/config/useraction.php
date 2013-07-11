<?php

	include 'print.php';
	include '../loadconnection.php';

	$table = $_GET["tablename"];
	$action = $_GET["action"];
	
	if(strlen($table)>0 and strlen($action)>0){
	
		if($connection = loadConnection())
		{
			$con = mysqli_connect("localhost",$connection[1],$connection[2],$connection[0]);
			if (mysqli_connect_errno())
			{
				echo "Failed to connect to MySQL: " . mysqli_connect_error();
			}

			$categories = mysqli_num_rows(mysqli_query($con,"DESCRIBE ".$table)) - 1;
			
			$sql = "INSERT INTO $table VALUES('$action',";
			for($i = 0;$i < $categories;$i++){
				$sql = $sql."0";
				if($i < $categories - 1)
					$sql = $sql.",";
			}
			$sql = $sql.")";

			if (mysqli_query($con,$sql))
			{
				echo printTables($con);
			}
			else
			{
				echo "Error (UserAction): " . mysqli_error($con);
			}
		}
	}
?>