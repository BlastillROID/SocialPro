<?php
require_once('connect.php');

$id=$_GET['id'];
$signal=$_GET['signal'];
$sql="UPDATE `posts` SET `signal`='$signal' WHERE `id`='$id'";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>