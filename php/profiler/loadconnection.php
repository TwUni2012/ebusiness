<?php

	function loadConnection($filename = "../data/connection")
	{
		if(file_exists($filename))
		{
			if($connectiondata = file_get_contents($filename))
			{
				return explode(":",$connectiondata);
			}
			echo "Could not read from file '".$filename."'<br>";
			return false;
		}
		echo "File '".$filename."' does not exist<br>";
		return false;
	}

?>