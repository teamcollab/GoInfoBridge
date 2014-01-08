<?php

$username="user";
$password="password";
$url="https://localhost:8080/rest/api/";

function callApi($url, $data = false)
{
    global $username, $password;

    $curl = curl_init();

    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true); 
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($curl, CURLOPT_HTTPHEADER,     array('Content-Type: text/plain')); 
    // Optional Authentication:
    curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
    curl_setopt($curl, CURLOPT_USERPWD, $username . ":" . $password);
    
    curl_setopt($curl, CURLOPT_VERBOSE, true); // Display communication with server
    curl_setopt($curl, CURLOPT_URL, $url);

    return curl_exec($curl);
}

//傳入參數定義
$values = new stdClass();
$values -> Code = "";

$message_body = json_encode(array(
    //若沒有定義 connectname 預設為 major
    "connectname" => "major",   //連線到 60.251.234.221/test db
    //"connectname" => "minor", //連線到 118.163.139.167test2 db
    "username" => "admin",
    "queryname" => "selectall",
    "action" => "query",
    "password" => "password",
    "values" => $values
));




echo " [.] Got ", callApi($url, $message_body), "\n";				//取得 資料內容

?>
