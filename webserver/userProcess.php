<?php
require("res\setup.php");
mysql_query("INSERT INTO muc.Users (username, password, admin) VALUES ('$_REQUEST[username]', '$_REQUEST[password]', 0)");
echo "Congratulations! You have now created an account!";
?>