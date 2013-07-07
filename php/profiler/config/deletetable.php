<?php

	include 'print.php';

	$table = $_GET["tablename"];
	
	if(strlen($table)>0){
	
		$sql = "DROP TABLE $table";
		
		$con = mysqli_connect("localhost","testuser","testpw","configtest");
		if (mysqli_connect_errno())
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		if (mysqli_query($con,$sql))
		{
			echo printTables($con);
		}
		else
		{
			echo "Error creating table: " . mysqli_error($con);
		}
	}
?>