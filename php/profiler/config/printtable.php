<?php

	include 'print.php';

	$con = mysqli_connect("localhost","testuser","testpw","configtest");
	if(!mysqli_connect_errno())
	{
		echo printTables($con);
	}
	else
	{
		echo "ERROR";
	}

?>