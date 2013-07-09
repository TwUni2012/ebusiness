
<?php

	include 'print.php';

	$userid = $_GET["userid"];
	$actionlist = explode(",",$_GET["action"]);
	
	if(strlen($userid)>0 and strlen($_GET["action"])>0){
	
		$con = mysqli_connect("localhost","testuser","testpw","configtest");
		if (mysqli_connect_errno())
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		if ($colq = mysqli_query($con,"DESCRIBE uservalues"))
		{
			$cols = array();
			
			//erste 3 spalten ignorieren
			mysqli_fetch_array($colq);
			mysqli_fetch_array($colq);
			mysqli_fetch_array($colq);

			//namen der konfigurationstabellen auslesen
			while($col = mysqli_fetch_array($colq))
			{
				$colname = $col[0];
				$colname = substr($colname,-(strlen($colname)-strpos($colname,"_")-1));
				$cols[] = $colname;
			}
			
			$middlevalues = array();	//werte addieren, am ende durchschnitt bilden
			$m = 0;
			//je spalte mit einer user profil kategorie / konfigurationstabelle
			foreach($cols as $col)
			{
				$initlist = true;
				$middlevalues[] = array();
				//alle übergebenen werte die die gesamte useraktion beschreiben durchlaufen
				foreach($actionlist as $action)
				{
					//echo "<b>".$action."</b><br>";
				
					//echo "<i>".$col."</i><br>";
					
					if($values = mysqli_query($con,"SELECT * FROM ".$col." WHERE category='".$action."'")){
						
						$row = mysqli_fetch_array($values);	//zeile der konfigurationstabelle mit den werten für diese useraktion
						$i = 0;
						$n = 0;
						
						// alle konfigurationswerte durchlaufen und auf die durchschnittswerte addieren
						foreach($row as $val){
							if($i > 1 and $i % 2 === 1){
								if($initlist)
								{
									//mittelwertliste mit ersten werten initialisieren
									//echo $n.". ".intval($val)." + 0";
									$middlevalues[$m][] = intval($val);
								}
								else
								{
									//echo $n.". ".intval($val)." + ".$middlevalues[$m][$n];
									$middlevalues[$m][$n] = $middlevalues[$m][$n] + intval($val);
								}
								//echo " = ".$middlevalues[$m][$n]."<br>";
								$n = $n+1;
							}
							$i = $i+1;
						}
					}
					$initlist = false;
				}
				//echo "<br>";
				$m = $m+1;
			}
			
			//höchstwerte bestimmen und index der entsprechenden kategorie speichern
			$m = 0;
			$maxindices = array();
			foreach($cols as $col)
			{
				$max = 0;
				//echo "<b>".$col."</b><br>";
				for($i=0; $i<count($middlevalues[$m]); $i++)
				{
					$maxindices[] = 0;
					//echo $middlevalues[$m][$i]." ";
					if($middlevalues[$m][$i] > $max)
					{
						$max = $middlevalues[$m][$i];
						$maxindices[$m] = $i;
					}
				}
				//echo " => ".$maxindices[$m]."<br>";
				$m = $m+1;
			}
			//echo "<br>";
			
			//maximale indices mit den letzten 5 maximalindices in der history verrechnen => neues aktuelles profil
			$sql = "SELECT ";
			$middlevalues = array();
			for($i=0; $i<count($cols); $i++)
			{
				$sql = $sql."history_".$cols[$i];
				if($i < count($cols)-1){
					$sql = $sql.",";
				}
				$middlevalues[] = $maxindices[$i];
			}
			$sql = $sql." FROM uservalues WHERE userid='".$userid."' ORDER BY timestamp DESC LIMIT 5;";
			if($history = mysqli_query($con,$sql))
			{				
				$m = 0;
				while($historyentry = mysqli_fetch_array($history)){
					for($i=0; $i<count($cols); $i++)
					{
						$middlevalues[$i] = $middlevalues[$i] + $historyentry[$i];
						//echo $historyentry[$i]." ";
					}
					//echo "<br>";
					$m = $m+1;
				}
				//echo "<br>";
				$m = $m+1;
				for($i=0; $i<count($cols); $i++)
				{
					//echo $middlevalues[$i]." / ".$m;
					$middlevalues[$i] = $middlevalues[$i] / $m;
					//echo " = ".$middlevalues[$i]." | ";
				}
			}
			else
			{
				echo "ERROR: " . mysqli_error($con);
			}
			
			//neues profil in die tabelle einfügen
			$sql = "INSERT INTO uservalues VALUES('".$userid."',NOW(),'".$_GET["action"]."'";
			
			foreach($middlevalues as $val)
			{
				$sql = $sql.",".$val;
			}
			
			$sql = $sql.")";
			
			if(mysqli_query($con,$sql))
			{
				echo printTables($con);
			}
			else
			{
				echo "ERROR: " . mysqli_error($con);
			}
		}
		else
		{
			echo "ERROR: " . mysqli_error($con);
		}
	}
?>