<?php

	include 'print.php';
	include '../loadconnection.php';

	$table = $_GET["tablename"];
	
	if(strlen($table)>0){
	
		if($connection = loadConnection())
		{
			$sql = "DROP TABLE $table";
			
			$con = mysqli_connect("localhost",$connection[1],$connection[2],$connection[0]);
			if (mysqli_connect_errno())
			{
				echo "Failed to connect to MySQL: " . mysqli_connect_error();
			}

			if (mysqli_query($con,$sql))
			{
				if (mysqli_query($con,"ALTER TABLE uservalues DROP history_".$table ))
				{
					echo printTables($con);
				}
				else
				{
					echo "Error creating table: " . mysqli_error($con);
				}
			}
			else
			{
				echo "Error creating table: " . mysqli_error($con);
			}
		}
	}
?>