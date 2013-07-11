<?php

	include 'print.php';
	include '../loadconnection.php';
	
	if($connection = loadConnection())
	{
		$con = mysqli_connect("localhost",$connection[1],$connection[2],$connection[0]);
		if(!mysqli_connect_errno())
		{
			echo printTables($con);
		}
		else
		{
			echo "ERROR";
		}
	}
?>