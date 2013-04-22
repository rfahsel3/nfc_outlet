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
echo "</br><input type='submit' value='Check Specific Tool Usage'>";


echo "</br></br></br><b>Here's a summary of all tool usage.</br></b></br>";
echo "<table border=1 cellpadding=5 cellspacing=2>";
echo "<tr><th>Tool</th><th>Total Uses</th></tr>";
$query=mysql_query("SELECT Tools.toolName, sum(Permissions.count) as s FROM Permissions JOIN Users ON Permissions.uid=Users.uid JOIN Tools ON Permissions.tid=Tools.tid GROUP BY Tools.tid ORDER BY Tools.toolName ASC");
$num = mysql_num_rows($query);
while($rows=mysql_fetch_array($query)){
	echo "<tr><td>".$rows['toolName']."</td><td>".$rows['s']."</td></tr>";
}
echo "</table></br>";




$goBack = "<input type='button' id='back_button' value='Back to Home'>";
echo "<a href='index.php'>".$goBack."</a></br>";
?>
