<?php
//Include this php file whenever doing database io
$con = mysql_connect("ryanjfahsel.com","admin","admin");
if (!$con)
  {
  die('Yikes! Cannot connect to the database: ' . mysql_error());
  }
//Use correct databse
mysql_select_db("muc", $con);
?>
