<?php
require("res\setup.php");
echo "</br>You have successfully deleted this permission!</br>";
$user = $_POST['user'];
$tool = $_POST['tool']; 
mysql_query("DELETE FROM Permissions WHERE Permissions.uid='$user' AND Permissions.tid='$tool'");
$goBack = "<input type='button' id='back_button' value='Back to Permissions'>";
echo "<a href='permissions.php'>".$goBack."</a></br>";
?>