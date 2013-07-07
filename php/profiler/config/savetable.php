
<?php

	include 'print.php';

	$n = 0;
	$tablename = "";
	$keys = array();
	$prefix = "";
	
	foreach($_POST as $key => $value)  
	{ 
		if($n == 0){
			$tablename = substr($key,-(strlen($key)-strpos($key,"_")-1));
			$tablename = substr($tablename,-strlen($tablename),-(strlen($tablename)-strpos($tablename,"_")));
			$prefix = "num_".$tablename;
			$keys[] = $key;
		}
		else
		{
			if(strpos($key, $prefix, 0) === 0){
				$keys[] = $key;
			}
		}
		$n = $n+1;
	}
	
	$con = mysqli_connect("localhost","testuser","testpw","configtest");
	if (mysqli_connect_errno())
	{
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else
	{
		$rows = mysqli_query($con,"SELECT * FROM ".$tablename);
		$colq = mysqli_query($con,"DESCRIBE ".$tablename);
		$cols = array();
		mysqli_fetch_array($colq);
		while($col = mysqli_fetch_array($colq))
		{
			$cols[]=$col[0];
		}
		
		$n = 0;
		while($row = mysqli_fetch_array($rows))
		{
			foreach($cols as $col){
				$sql = "UPDATE ".$tablename." SET ".$col."=".$_POST[$keys[$n]]." WHERE category='".$row[0]."'";
				mysqli_query($con,$sql);
				$n = $n+1;
			}
		}
	}
	
	header( 'Location: index.html' ) ;
?>