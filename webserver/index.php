<?php
echo "This is the homepage for the NFC server. It is up and working";
echo "<p>Here, you will be able to add users and manage permissions.</p>";

$addUser = "<input type='button' id='php_button' value='Add User'>";
echo "<a href='users.php'>".$addUser."</a></br>";

$manage = "<input type='button' id='php_button' value='Manage Permissions'>";
echo "<a href='permissions.php'>".$manage."</a></br>";
?>
