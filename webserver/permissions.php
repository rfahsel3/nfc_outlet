<?php
require("res/setup.php");
echo "</br>Here's a list of all of the permissions. Each of these users are authorized to use the tools listed below.</br></br>";
echo "<table border=1 cellpadding=5 cellspacing=2>";
echo "<tr><th>Username</th><th>Authorized Tools</th></tr>";
$query=mysql_query("SELECT Users.username, Users.uid, Tools.toolName, Tools.tid FROM Permissions JOIN Users ON Permissions.uid=Users.uid JOIN Tools ON Permissions.tid=Tools.tid");
$num = mysql_num_rows($query);
while($rows = mysql_fetch_array($query)){
	$user=$rows['uid'];
	$tool=$rows['tid'];
	echo "<form action='deletePermission.php' method='post'>";
	echo "<input type=hidden id=$user name='user' value='$user'>";
	echo "<input type=hidden id=$tool name='tool' value='$tool'>";
	$delete = "<input type='submit' value='Delete'></form>";
	echo "<tr><td>".$rows['username']."</td><td>".$rows['toolName']."</td><td><a href='permissions.php'>".$delete."</a></td></tr>";
}
echo "</table></br>";
echo "Give users new permissions to tools: </br>";
echo "<form action='permissionsProcess.php' method='post'>";

$queryUsers=mysql_query("SELECT * from muc.Users");
echo "Username: <select name='user' value='Username'>";
while ($rowsUsers=mysql_fetch_array($queryUsers)) {
	$user = $rowsUsers['username'];
	$uid = $rowsUsers['uid'];
	echo "<option name='uid' id=$uid value=".$uid.">".$user."</option>";
}
echo "</select></br>";

$queryTools=mysql_query("SELECT * from muc.Tools");
echo "Tool Name: <select name='tool' value='Toolname'>";
while ($rowsTools=mysql_fetch_array($queryTools)) {
	$tool = $rowsTools['toolName'];
	$tid = $rowsTools['tid'];
	echo "<option name='tid' id=$tid value=".$tid.">".$tool."</option>";
}
echo "</select></br>";

echo "</br><input type='submit' value='Add New Permission'>";
$goBack = "<input type='button' id='back_button' value='Back to Home'>";
echo "<a href='index.php'>".$goBack."</a></br>";
?>