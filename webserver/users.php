<?php
require("res/setup.php");
$query = mysql_query("SELECT * from muc.Users");
echo "<table border=1 cellpadding=5 cellspacing=2>";
echo "<tr><th>Username</th></tr>"; //<th></th></tr>";
$num=0;
while($rows = mysql_fetch_array($query)){
	$num++;
	$value=$rows['uid'];
	echo "<form action='delete.php' method='post'>";
	echo "<input type=hidden id=$value name='id' value='$value'>";
	$delete = "<input type='submit' value='Delete'></form>";
	echo "<tr><td>".$rows['username']."</td><td><a href='users.php'>".$delete."</a></td></tr>";
}

echo "</table>";
echo "<form action='userProcess.php' method='post'>";
echo "</br>Username: <input type='text' name='username'></br>";
echo "Password: <input type='password' name='password'></br>";
echo "<input type='submit'>";
$goBack = "<input type='button' id='back_button' value='Back to Home'>";
echo "<a href='index.php'>".$goBack."</a></br>";
?>
