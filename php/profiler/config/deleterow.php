
<?php

	include 'print.php';

	$table = $_GET["tablename"];
	$row = $_GET["row"];
	
	if(strlen($table)>0 and strlen($row)>0){
	
		$con = mysqli_connect("localhost","testuser","testpw","configtest");
		if (mysqli_connect_errno())
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		$sql = "DELETE FROM ".$table." WHERE category = '$row'";

		if (mysqli_query($con,$sql))
		{
			echo printTables($con);
		}
		else
		{
			echo "ERROR: " . mysqli_error($con);
		}
	}
?>