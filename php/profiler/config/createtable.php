<?php

	include 'print.php';
	include '../loadconnection.php';

	$table = $_GET["tablename"];
	
	if(strlen($table)>0){
	
		if($connection = loadConnection())
		{
			$sql = "CREATE TABLE $table(category VARCHAR(50) NOT NULL,PRIMARY KEY (category))";
			
			$con = mysqli_connect("localhost",$connection[1],$connection[2],$connection[0]);
			if (mysqli_connect_errno())
			{
				echo "Failed to connect to MySQL: " . mysqli_connect_error();
			}

			if (mysqli_query($con,$sql))
			{
				//uservalues updaten, spalte mit namen neuer tabelle einfügen
				if (mysqli_query($con,"ALTER TABLE uservalues ADD history_$table DOUBLE"))
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