<?php

	include '../loadconnection.php';
	$userid = $_GET["userid"];
	
	if(strlen($userid)>0){

		if($connection = loadConnection())
		{
			$con = mysqli_connect("localhost",$connection[1],$connection[2],$connection[0]);

			$tables = mysqli_query($con,"SHOW TABLES");
			$t = 0;
			while($table = mysqli_fetch_array($tables)){
				if(strcmp($table[0],"uservalues") !== 0)
				{
					echo "<iframe frameborder=no width=100% height=400 id=\"graph".$t."\" src=\"diagram.php?userid=".$userid."&table=".$table[0]."\"></iframe><br>";
					$t = $t+1;
				}
			}
		}
	}

?>