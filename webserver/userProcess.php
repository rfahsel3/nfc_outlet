<?php
require("res\setup.php");
mysql_query("INSERT INTO muc.Users (username, password, admin) VALUES ('$_REQUEST[username]', '$_REQUEST[password]', 0)");
echo "</br>Congratulations! You have now created an account!</br>";
$goBack = "<input type='button' id='back_button' value='Back to Users'>";
echo "<a href='users.php'>".$goBack."</a></br>";
?>