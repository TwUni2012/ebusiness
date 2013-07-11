<?php // content="text/plain; charset=utf-8"
	require_once ('jpgraph-3.5.0b1/src/jpgraph.php');
	require_once ('jpgraph-3.5.0b1/src/jpgraph_line.php');
	include '../loadconnection.php';

	$userid = $_GET["userid"];
	$table = $_GET["table"];

	if($connection = loadConnection())
	{

		$con = mysql_connect("localhost", $connection[1], $connection[2])
			or die("Unable to connect to MySQL");
			
		$selected_db = mysql_select_db($connection[0],$con)
			or die("Could not select examples");	

		$result = mysql_query("SELECT history_".$table.", timestamp  FROM `uservalues` WHERE userid='".$userid."' ORDER BY timestamp DESC");
		
		$input_data_array = array();
		$timestamp_data_array = array();

		while ($row = mysql_fetch_array($result)) {
			$input_data_array[] = $row['history_'.$table];
			$timestamp_data_array[] = $row['timestamp'];
		}

		$timestamp_count = count($timestamp_data_array);

		for($i=0; $i< $timestamp_count; $i++) {
		   $offset = 10;
		   $part1 = substr($timestamp_data_array[$i], 0, $offset);
		   $part2 = substr($timestamp_data_array[$i], $offset);
		 
		   $part1 = $part1 . "\n";
		   $timestamp_data_array[$i] = $part1 . $part2; 
		}
		  
		$data_size = count($input_data_array);

		$graph = new Graph($data_size * 100 + 100,500);
		$graph->SetScale("textlin");

		$theme_class=new UniversalTheme;

		$graph->SetTheme($theme_class);
		$graph->img->SetAntiAliasing(false);
		$graph->title->Set('Diagramm - '.$table);
		$graph->SetBox(false);
		$graph->img->SetMargin(50,50,50,50);

		$graph->img->SetAntiAliasing();

		$graph->yaxis->HideLine(false);
		$graph->yaxis->HideTicks(false,false);

		$graph->xgrid->Show();
		$graph->xgrid->SetLineStyle("solid");

		$graph->xaxis->SetTickLabels($timestamp_data_array);
		$graph->xgrid->SetColor('#E3E3E3');
		
		$colors = array("#001493","#A0A00A","#FF00FF","#FF0A0F","#0F0A0F","#00FF00");

		$n = 0;
		if($cols = mysql_query("DESCRIBE ".$table))
		{
			mysql_fetch_array($cols);
			while($col = mysql_fetch_array($cols))
			{
					$data = array();
					for($i = 0; $i < $data_size; $i++) {
						if($n === 0)
						{
							$data[$i] = 0.001;
						}
						else
						{
							$data[$i] = $n-0.5;
						}
					}
					$p = new LinePlot($data);
					$graph->Add($p);
					$p->SetColor($colors[$n%6]);
					$p->SetLegend($col[0]);
					
					$n = $n + 1;
			}
		}

		$p1 = new LinePlot($input_data_array);
		$graph->Add($p1);
		$p1->SetColor("#6495ED");
		$p1->SetLegend($userid);
		
		$graph->legend->SetFrameWeight(1);

		$graph->Stroke();
	}
?>