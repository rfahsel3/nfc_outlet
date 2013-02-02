<?php
//This function takes a username, password, and nfcid. It looks to see if those credentials match to that tool.
//It returns 1,toolName,description on success and sets 
//It returns a message with error if fail
require('res/setup.php');
$nfcid=$_REQUEST[nfcid];
$username=$_REQUEST[username];
$password=$_REQUEST[password];
$query=mysql_query("SELECT Tools.toolName, Tools.description, Tools.tid FROM Permissions join Tools on Permissions.tid=Tools.tid join Users on Permissions.uid=Users.uid WHERE Users.username='$username' AND Users.password='$password' AND Tools.nfcid='$nfcid'");
if($query)	{
	$result=mysql_fetch_array($query);
	if(mysql_num_rows($query)==1)	{
		echo "1".",".$result[2].",".$result[0].",".$result[1];
		mysql_query("UPDATE Tools SET enable=1 WHERE nfcid='$nfcid'");
	}
	else	{
		echo "You are not authorized to use this tool. If you believe this is in error please contact an administrator.";
	}
}
else	{
	die("There was an error with the query. Contact an admin to get this fixed");
}
?>
