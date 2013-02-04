<?php
require("res/setup.php");
$tid=$_REQUEST[tid];
$query=mysql_query("SELECT enable FROM Tools WHERE tid=$tid");
$result=mysql_fetch_array($query);
echo $result[0];
?>
