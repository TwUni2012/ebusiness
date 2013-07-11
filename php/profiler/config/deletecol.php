
<?php

	include 'print.php';
	include '../loadconnection.php';

	$table = $_GET["tablename"];
	$col = $_GET["col"];
	
	if(strlen($table)>0 and strlen($col)>0){
	
		if($connection = loadConnection())
		{
			$con = mysqli_connect("localhost",$connection[1],$connection[2],$connection[0]);
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
	}
?>