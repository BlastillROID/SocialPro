<?php
require_once('connect.php');

$return_arr = array();

$id=$_GET['id'];
$sql = "SELECT * FROM posts WHERE `id`='$id'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['id'] = $row['id'];
       $row_array['content'] = $row['content'];
       $row_array['media'] = $row['media'];
       $row_array['time'] = $row['time'];
       $row_array['signal'] = $row['signal'];
    array_push($return_arr,$row_array);
    }
} 

echo json_encode($return_arr);

?>