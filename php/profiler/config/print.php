<?php

	$celloffset = 0;
	$rowoffset = 0;

	function printTable($con,$table,$t){
	
		global $celloffset,$rowoffset;
	
		if(strlen($table)>0){
		
			$rows = mysqli_query($con,"DESCRIBE ".$table);
			
			$out = "<h2>".$table."</h2><form class=\"tabledata\" id=\"table_".$table."_data\" action=\"savetable.php\" method=\"post\"><table border=1><tr><td></td>";
			
			//tabellenkopf
			
			//ersten eintrag erster zeile ignorieren
			$row = mysqli_fetch_array($rows);
			while($row = mysqli_fetch_array($rows)){
				$out = $out."<td><b>$row[0]</b></td>";
			}
			$out = $out."<td></td><td></td></tr>";
			
			//tabelleninhalt
			$rows = mysqli_query($con,"SELECT * FROM ".$table);
			
			$n = 0;
			$cols = 0;
			while($row = mysqli_fetch_array($rows)){
				$out = $out."<tr>";
				for($i=0;$i<($cols = count($row)/2);$i++){
					if($i == 0)
						$out = $out."<td><b>".$row[$i]."</b></td>";
					else
						$out = $out."<td><input class=\"numberinput\" onchange=\"checkRowSum(".$t.",".$n.",".($cols-1).",".$celloffset.",".$rowoffset.");\" type=\"number\" size=4 name=\"num_".$table."_".$n."_".$i."\" min=\"0\" max=\"100\" value=".$row[$i]."></td>";
				}
				$out = $out."<td style=\"text-align:left;\"><button type=\"button\" style=\"background:#f00\" onclick=\"deleteRow('".$table."','".$row[0]."');\">X</button></td><td><div style=\"color:#f00;\" class=\"tablestatus\" id=\"status_".$t."_".$n."\"></div></td></tr>";
				$n = $n+1;
			}
			$out = $out."<tr><td></td>";

			$celloffset = $celloffset + $n*($cols-1);
			$rowoffset = $rowoffset + $n;
			
			$rows = mysqli_query($con,"DESCRIBE ".$table);
			$row = mysqli_fetch_array($rows);
			while($row = mysqli_fetch_array($rows)){
				$out = $out."<td><button type=\"button\" style=\"background:#f00\" onclick=\"deleteCol('".$table."','".$row[0]."');\">X</button></td>";
			}
			$out = $out."<td style=\"text-align:left;\"></td></tr><table><br><table><tr><td><input type=\"submit\" value=\"Tabelle speichern\"></td><td><button type=\"button\" onclick=\"deleteTable('".$table."');\">Tabelle l&ouml;schen</button></td></tr></table></form>";
			
			$out = $out."<form name=\"table_".$table."_form\"><table><tr><td style=\"text-align:right;\"><p>Neue Kategorie (Spalte):</td><td><input type=\"text\" class=\"inputcategory\" name=\"newcategory\"></td><td style=\"text-align:center;\"><button type=\"button\" name=\"addaction\" onclick=\"addCategory('table_".$table."_form','".$table."',".$t.");\">Hinzuf&uuml;gen</button></td></tr>";
			$out = $out."<tr><td style=\"text-align:right;\"><p>Neue Useraktion (Zeile):</td><td><input class=\"inputuseraction\" type=\"text\" name=\"newaction\"></td><td style=\"text-align:center;\"><button type=\"button\" name=\"addaction\" onclick=\"addAction('table_".$table."_form','".$table."',".$t.");\">Hinzuf&uuml;gen</button></td></tr></table></form>";
			
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
			echo printTable($con,$table[0],$t);
			$t = $t+1;
		}
	}
	
?>