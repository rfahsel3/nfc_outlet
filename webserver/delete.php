<?php
require("res/setup.php");
echo "</br>You have successfully deleted this user!</br>";
$value = $_POST['id']; 
mysql_query("DELETE FROM muc.Users WHERE Users.uid='$value'");
$goBack = "<input type='button' id='back_button' value='Back to Users'>";
echo "<a href='users.php'>".$goBack."</a></br>";
?>
