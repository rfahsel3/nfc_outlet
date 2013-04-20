<?php
require("res/setup.php");
echo "</br>Here's a list of all of the tools and which user uses each most frequently.</br></br>";
echo "<table border=1 cellpadding=5 cellspacing=2>";
echo "<tr><th>Tool</th><th>Most Frequent User</th><th>Number of Times Used</th></tr>";
$query=mysql_query("SELECT Users.username, Users.uid, Tools.toolName, Tools.tid, P1.count FROM Permissions P1 JOIN Users ON P1.uid=Users.uid JOIN Tools ON P1.tid=Tools.tid ORDER BY Tools.tid AND P1.count=(SELECT MAX(P2.count) FROM Permissions P2 WHERE P2.tid=P1.tid AND P2.uid=P1.uid)");
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