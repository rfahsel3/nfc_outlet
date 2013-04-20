<?php
require("res/setup.php");

echo "Check usage for any of the following tools: </br>";
echo "<form action='usageProcess.php' method='post'>";

$queryTools=mysql_query("SELECT * from muc.Tools");
echo "Tool Name: <select name='tool' value='Toolname'>";
while ($rowsTools=mysql_fetch_array($queryTools)) {
	$tool = $rowsTools['toolName'];
	$tid = $rowsTools['tid'];
	echo "<option name='tid' id=$tid value=".$tid.">".$tool."</option>";
}
echo "</select></br>";
echo "</br><input type='submit' value='Check Usage'>";

$goBack = "<input type='button' id='back_button' value='Back to Home'>";
echo "<a href='index.php'>".$goBack."</a></br>";
?>