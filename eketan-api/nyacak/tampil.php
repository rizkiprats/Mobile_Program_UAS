<?php
$host = "localhost";
$username = "root";
$password = "";
$dbname = "flaskdb";
$koneksi = new mysqli($host,$username,$password,$dbname);
header('Content-Type:application/json');

$query = "SELECT * FROM dosen";
$obj_query = $koneksi->query($query);

if($obj_query){
    $result = array();
    while($row = mysqli_fetch_array($obj_query)){
        array_push($result, array(
            'id_dosen'=>$row['id_dosen'],
            'nama_dosen'=>$row['nama_dosen']
        ));
    }
    echo json_encode(array('list_data'=>$result));
}
?>