<?php
//echo $_REQUEST['nfcid'].",".$_REQUEST['username'].",".$_REQUEST['password'];
echo "This is the homepage for the NFC server. It is up and working";
echo "<p>Here, you will be able to add users and manage permissions.</p>";

$addUser = "<input type='button' id='php_button' value='Add User'>";
echo $addUser;

$manage = "<input type='button' id='php_button' value='Manage Permissions'>";
echo $manage;
?>
