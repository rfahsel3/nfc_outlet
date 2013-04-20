<?php
require("res/setup.php");
echo "</br>Here's a list of all of the tools and how often each user used them.</br></br>";
echo "<table border=1 cellpadding=5 cellspacing=2>";
echo "<tr><th>Tool</th><th>Username</th><th>Number of Times Used</th></tr>";
$query=mysql_query("SELECT Users.username, Users.uid, Tools.toolName, Tools.tid, Permissions.count FROM Permissions JOIN Users ON Permissions.uid=Users.uid JOIN Tools ON Permissions.tid=Tools.tid ORDER BY Tools.tid, Permissions.count DESC");
$num = mysql_num_rows($query);
while($rows = mysql_fetch_array($query)){
	$user=$rows['uid'];
	$tool=$rows['tid'];
	echo "<tr><td>".$rows['toolName']."</td><td>".$rows['username']."</td><td>".$rows['count']."</td></tr>";
}
echo "</table></br>";

$goBack = "<input type='button' id='back_button' value='Back to Home'>";
echo "<a href='index.php'>".$goBack."</a></br>";
?>