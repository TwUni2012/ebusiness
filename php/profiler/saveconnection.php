<?php

	$database = $_POST["database"];
	$user = $_POST["user"];
	$password = $_POST["password"];
	
	file_put_contents("data/connection",$database.":".$user.":".$password);

	header( 'Location: connection.php' ) ;

?>