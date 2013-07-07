<?php

	include 'print.php';

	$table = $_GET["tablename"];
	$cat = $_GET["category"];
	
	if(strlen($table)>0 and strlen($cat)>0){
	
		$con = mysqli_connect("localhost","testuser","testpw","configtest");
		if (mysqli_connect_errno())
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		//alter table wert add p int
		$sql = "ALTER TABLE $table ADD $cat INT";
		
		if (mysqli_query($con,$sql))
		{
			$sql = "UPDATE $table SET $cat=0";
			if (mysqli_query($con,$sql))
			{
				$sql = "UPDATE $table SET $cat=0";
			
				echo printTables($con);
			}
			else
			{
				echo "ERROR: " . mysqli_error($con);
			}
		}
		else
		{
			echo "ERROR: " . mysqli_error($con);
		}
	}
?>