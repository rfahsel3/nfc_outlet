<?php
require("res/setup.php");
echo "<table border=1>";
echo "<tr><th>Username</th><th>Tool Name</th><th>Permissions Id</th></tr>";
$query=mysql_query("SELECT Users.username, Tools.toolName, Permissions.pid FROM Permissions JOIN Users ON Permissions.uid=Users.uid JOIN Tools ON Permissions.tid=Tools.tid");
$num = mysql_num_rows($query);
while($rows = mysql_fetch_array($query)){
	echo "<tr><td>".$rows['username']."</td><td>".$rows['toolName']."</td><td>".$rows['pid']."</td></tr>";
}
?>