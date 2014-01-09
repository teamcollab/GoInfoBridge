<?php


$clientId = "clientId";
$clientSecret = "clientSecret";
$postArgs = 'grant_type=client_credentials';
$tokenUrl = 'http://60.251.234.221:8080/oauth/token'; 
$url="http://60.251.234.221:8080/rest/api/";

function get_access_token($url, $postdata) {
        global $clientId, $clientSecret;
        $curl = curl_init($url); 
        curl_setopt($curl, CURLOPT_POST, true); 
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_USERPWD, $clientId . ":" . $clientSecret);
        curl_setopt($curl, CURLOPT_HEADER, false); 
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true); 
        curl_setopt($curl, CURLOPT_POSTFIELDS, $postdata); 
        $response = curl_exec( $curl );
        $jsonResponse = json_decode( $response );
        return $jsonResponse->access_token;
}

function callApi($url, $postdata) {
        global $token;
        $curl = curl_init(); 
        

        curl_setopt($curl, CURLOPT_POST, true);
        curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($postdata));
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_HEADER, false);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array(
                                'Authorization: Bearer '.$token,
                                'Content-Type: text/plain'
                                ));
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_VERBOSE, true);
        $response = curl_exec( $curl );


        return $response;
}


$token = get_access_token($tokenUrl,$postArgs);

echo "Got OAuth Token: ".$token , "\n";

//傳入參數定義
$values = new stdClass();
$values -> Code = "";

$message_body = array(
    //若沒有定義 connectname 預設為 major
    "connectname" => "major",   //連線到 60.251.234.221/test db
    //"connectname" => "minor", //連線到 118.163.139.167test2 db
    "username" => "admin",
    "queryname" => "selectall",
    "action" => "query",
    "password" => "password",
    "values" => $values
);


echo " [.] Got ", callApi($url,$message_body), "\n";                //取得 資料內容

?>
