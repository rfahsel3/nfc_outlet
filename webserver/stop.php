<?php
//This is for turning a tool off. You pass it a tid, and it turns off the tool by updating Tools>enable with a 0
require('res/setup.php');
$tid=$_REQUEST[tid];
$query=mysql_query("UPDATE Tools SET enable=0 WHERE tid=$tid");
if(mysql_affected_rows()==0)	{
	echo "The tool appears to already be off. If it is not, please contact an administrator";
}
if(!$query)	{
	echo "There was a problem turning the tool off. Please tell the administrator the following: ".mysql_error;
}
?>
