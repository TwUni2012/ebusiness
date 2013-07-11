<?php

	include 'loadconnection.php';

	echo "<link href='style.css' rel='stylesheet'><link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>";
	echo "<h1>Connection to Database</h1>";
	
	if($parts = loadConnection("data/connection"))
	{
		echo "<h2>Current Connection</h2>";
		echo "<table><tr><td><b>Database:</b></td><td>".$parts[0];
		echo "</td></tr><tr><td><b>User:</b></td><td>".$parts[1];
		echo "</td></tr><tr><td><b>Password:</b></td><td>".$parts[2];
		echo "</td></tr></table><hr>";
	}

	echo "<h2>New Connection</h2>";
	echo "<p>(current Connection will be replaced)</p>";
	echo "<form id=\"newconnection\" action=\"saveconnection.php\" method=\"post\"><table><tr><td><b>Database with User- and Configuration Data</b></td><td><input name=\"database\" type=\"text\"/>";
	echo "</td></tr><tr><td><b>User:</b></td><td><input name=\"user\" type=\"text\"/>";
	echo "</td></tr><tr><td><b>Password:</b></td><td><input name=\"password\" type=\"text\"/>";
	echo "</td></tr><td></td><td><input type=\"submit\" value=\"Save\"></table></form>";


?>