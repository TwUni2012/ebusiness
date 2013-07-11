<?php

	include 'loadconnection.php';

	echo "<link href='style.css' rel='stylesheet'><link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>";
	echo "<h1>Datenbankverbindung</h1>";
	
	if($parts = loadConnection("data/connection"))
	{
		echo "<h2>Gespeicherte Verbindung</h2>";
		echo "<table><tr><td><b>Datenbank mit User- und Konfigurationsdaten:</b></td><td>".$parts[0];
		echo "</td></tr><tr><td><b>Benutzer:</b></td><td>".$parts[1];
		echo "</td></tr><tr><td><b>Passwort:</b></td><td>".$parts[2];
		echo "</td></tr></table><hr>";
	}

	echo "<h2>Neue Verbindung anlegen</h2>";
	echo "<p>(bestehende Verbindung wird ersetzt)</p>";
	echo "<form id=\"newconnection\" action=\"saveconnection.php\" method=\"post\"><table><tr><td><b>Datenbank mit User- und Konfigurationsdaten:</b></td><td><input name=\"database\" type=\"text\"/>";
	echo "</td></tr><tr><td><b>Benutzer:</b></td><td><input name=\"user\" type=\"text\"/>";
	echo "</td></tr><tr><td><b>Passwort:</b></td><td><input name=\"password\" type=\"text\"/>";
	echo "</td></tr><td></td><td><input type=\"submit\" value=\"Speichern\"></table></form>";


?>