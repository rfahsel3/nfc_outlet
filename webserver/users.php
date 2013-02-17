<?php
require("res/setup.php");
$query = mysql_query("SELECT username from muc.Users");
echo "<table border=1>";
echo "<tr><th>Username</th></tr>";
while($rows = mysql_fetch_array($query)){
	echo "<tr><td>".$rows['username']."</td></tr>";
}
echo "</table>";
echo "<form action='userProcess.php' method='post'>";
echo "Username: <input type='text' name='username'></br>";
echo "Password: <input type='password' name='password'></br>";
echo "<input type='submit'>";

?>
