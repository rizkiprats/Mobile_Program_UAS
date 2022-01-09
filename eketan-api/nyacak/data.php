<?php
$host = "localhost";
$username = "root";
$password = "";
$dbname = "nyacak";
$koneksi = new mysqli($host,$username,$password,$dbname);

if($koneksi->connect_error){
    die ("Tidak Tersambung".$koneksi->connect_error);
} 

$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM tb_login where username='$username' AND password='$password'";
$obj_query = $koneksi->query($query);
$data = mysqli_fetch_assoc($obj_query);


if($data){
    echo json_encode(
        array(
            'response'=>true,
            'payload'=>array(
                "username" => $data["username"],
                "password" => $data["password"]
            )
        )
    );
    }else{
        echo json_encode(
            array(
                'response'=>false,
                'payload'=>null
            )
        );
    }
?>