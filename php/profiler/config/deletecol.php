
<?php

	include 'print.php';

	$table = $_GET["tablename"];
	$col = $_GET["col"];
	
	if(strlen($table)>0 and strlen($col)>0){
	
		$con = mysqli_connect("localhost","testuser","testpw","configtest");
		if (mysqli_connect_errno())
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		//alter table test1 drop undefined
		$sql = "ALTER TABLE ".$table." DROP ".$col;

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