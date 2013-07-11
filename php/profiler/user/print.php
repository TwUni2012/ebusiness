<?php

	$celloffset = 0;
	$rowoffset = 0;

	function printTable($con,$table,$t){
	
		global $celloffset,$rowoffset;
	
		if(strlen($table)>0){
		
			$rows = mysqli_query($con,"DESCRIBE ".$table);
			
			$out = "<h2>".$table."</h2><form class=\"tabledata\" id=\"table_".$table."_data\" action=\"savetable.php\" method=\"post\"><table><tr>";
						
			//tabellenkopf
			
			while($row = mysqli_fetch_array($rows)){
				$out = $out."<td><b>$row[0]</b></td>";
			}
			$out = $out."</tr>";
			
			//tabelleninhalt
			$sql = "SELECT * FROM ".$table." ORDER BY userid,timestamp";
			$rows = mysqli_query($con,$sql);
			
			$n = 0;
			$cols = 0;
			while($row = mysqli_fetch_array($rows)){
				$out = $out."<tr>";
				for($i=0;$i<($cols = count($row)/2);$i++){
					if($i === 0){
						$out = $out."<td><a href=\"../graph/printgraphs.php?userid=".$row[$i]."\">".$row[$i]."</a></td>";
					}
					else{
						$out = $out."<td>".$row[$i]."</td>";
					}
				}
				$out = $out."</tr>";
				$n = $n+1;
			}
			$out = $out."</table></form>";
			
			echo $out;
		}
	}
	
	function printTables($con){
		
		global $celloffset,$rowoffset;
		$celloffset = 0;
		$rowoffset = 0;
		
		$tables = mysqli_query($con,"SHOW TABLES");
		$t = 0;
		while($table = mysqli_fetch_array($tables)){
			if(strcmp($table[0],"uservalues") === 0)
			{
				echo printTable($con,$table[0],$t);
				$t = $t+1;
			}

		}
	}
	
?>