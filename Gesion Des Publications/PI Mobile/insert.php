<?php
require_once('connect.php');

$content=$_GET['content'];
$time=$_GET['time'];
$media=$_GET['media'];

$sql = "INSERT INTO posts ( content,time,media)
VALUES ( '$content','$time','$media')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>