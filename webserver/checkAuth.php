<?php
$con = mysql_connect("localhost","admin","admin");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("muc", $con);
//echo $_REQUEST[username].$_REQUEST[password];
$result = mysql_query("SELECT count(*) FROM Users WHERE username='$_REQUEST[username]' AND password='$_REQUEST[password]'");
while($row = mysql_fetch_array($result))
  {
  	echo $row[0];
  }

mysql_close($con);
?>
