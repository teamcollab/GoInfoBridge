<?php

require_once __DIR__ . '/vendor/autoload.php';
use PhpAmqpLib\Connection\AMQPConnection;
use PhpAmqpLib\Message\AMQPMessage;

// 呼叫 mq 取得資料類別

class FibonacciRpcClient {
	private $connection;
	private $channel;
	private $callback_queue;
	private $response;
	private $corr_id;

	public function __construct() {
		$this->connection = new AMQPConnection(
			//'localhost', 			//mq server ip
			'60.251.234.221', 	//mq server ip
			5672, 					//mq server port
			'guest', 'guest');
		$this->channel = $this->connection->channel();
		list($this->callback_queue, ,) = $this->channel->queue_declare(
			"", false, false, true, false);
		$this->channel->basic_consume(
			$this->callback_queue, '', false, false, false, false,
			array($this, 'on_response'));
	}
	public function on_response($rep) {
		if($rep->get('correlation_id') == $this->corr_id) {
			$this->response = $rep->body;
		}
	}

	public function call($message_body) {

		$this->response = null;
		$this->corr_id = uniqid();


		$msg = new AMQPMessage(
			json_encode($message_body),
			array('correlation_id' => $this->corr_id,
			      'reply_to' => $this->callback_queue,
			      'content_type' => 'text/plain',
			      'content_encoding' => 'utf-8')
			);
		$this->channel->basic_publish($msg, '', 'spring-boot');
		while(!$this->response) {
			$this->channel->wait();
		}
		return $this->response;
	}
};

// 實際使用範例
$fibonacci_rpc = new FibonacciRpcClient(); 	//建立連線

//傳入參數定義
$values = new stdClass();
$values -> Wcode = "02B91002";
$values -> Sdate = "2012-12-15";
$values -> Price = "12.5";
$values -> Hcode = "A-10-2";
$message_body = array(
	//若沒有定義 connectname 預設為 major
	"connectname" => "major",	//連線到 60.251.234.221/test db
	//"connectname" => "minor", //連線到 118.163.139.167test2 db
	"username" => "admin",
	"queryname" => "updatemanytype",
	"action" => "update",
	"password" => "password",
	"values" => $values
);

$response = $fibonacci_rpc->call($message_body);//進行 request 資料，傳入參數
echo " [.] Got ", $response, "\n";				//取得 資料內容

?>
