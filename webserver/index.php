<?php
//echo $_REQUEST['nfcid'].",".$_REQUEST['username'].",".$_REQUEST['password'];
echo "This is the homepage for the NFC server. It is up and working";
echo "<p>Here, you will be able to add users and manage permissions.</p>";

$addUser = "<input type='button' id='php_button' value='Add User'>";
echo $addUser."</br>";

$manage = "<input type='button' id='php_button' value='Manage Permissions'>";
echo $manage."</br>";

require("res\setup.php");
$sql="SELECT * FROM muc.Permissions";
$result=mysql_query($sql);
$num = mysql_num_rows($result);

mysql_query($sql);

while($rows=mysql_fetch_array($result)){
echo $rows['pid'];
echo $rows['uid'];
echo $rows['tid'];
echo "</br>";
}

?>
