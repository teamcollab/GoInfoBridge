<?php

// Method: POST, PUT, GET etc
// Data: array("param" => "value") ==> index.php?param=value

function CallAPI($method, $url, $data = false)
{
    $curl = curl_init();

    switch ($method)
    {
        case "POST":
            curl_setopt($curl, CURLOPT_POST, 1);

            if ($data)
                curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
            break;
        case "PUT":
            curl_setopt($curl, CURLOPT_PUT, 1);
            break;
        default:
            if ($data)
                $url = sprintf("%s?%s", $url, http_build_query($data));
    }

    // Optional Authentication:
	$headr = array();
	// $headr[] = 'Authorization: Bearer b1908644-c249-4b94-a21d-471c008bc46e';
	$headr[] = 'Authorization: Basic dXNlcjpwYXNzd29yZA==';

	curl_setopt($crl, CURLOPT_HTTPHEADER,$headr);

    curl_setopt($curl, CURLOPT_URL, $url);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);

    return curl_exec($curl);
}


echo " [.] Got ", CallAPI("GET","http://localhost:8889/test/sendMsg"), "\n";				//取得 資料內容

?>
