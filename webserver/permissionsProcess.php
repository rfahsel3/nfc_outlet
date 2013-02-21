<?php
require("res\setup.php");
$uid = $_POST['uid']; 
$tid = $_POST['tid']; 
//mysql_query("INSERT INTO muc.Permissions (uid, tid) VALUES ('$_REQUEST[user]', '$_REQUEST[tool]')");
mysql_query("INSERT INTO muc.Permissions (uid, tid) VALUES ('$uid', '$tid')");
echo "INSERT INTO muc.Permissions (uid, tid) VALUES ('$uid', '$tid')";
echo "</br>You have now added a new permission!</br>";
$goBack = "<input type='button' id='back_button' value='Back to Permissions'>";
echo "<a href='permissions.php'>".$goBack."</a></br>";
?>