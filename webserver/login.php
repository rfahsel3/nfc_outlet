<?php
//This returns a 0 if the user is not in the databse, a 1 if they are.
//Parameters are username,password
include('res/setup.php');
$result=mysql_query("SELECT COUNT(*) FROM Users WHERE username='$_REQUEST[username]' AND password='$_REQUEST[password]' ");
$result=mysql_fetch_array($result);
echo $result[0];
?>
