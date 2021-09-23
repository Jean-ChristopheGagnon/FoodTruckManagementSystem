<?php

class OrderController extends CRUDController {
	
	public function __construct() {
	
		parent::__construct('Order', 'Order', 'FoodTruck', 'Food Truck');
	
	}
	
	public function createOrder($date, $time, $paid, $served, array $menuitems, $foodTruckId) {
		
		$paid = $paid === "paid" ? true : false;
		$paid = $served === "served" ? true : false;
		
		$date = $this->validateAndCleanProperty($date, 'order date', 'date');
		$time = $this->validateAndCleanProperty($time, 'order time', 'time');
		$paid = $this->validateAndCleanProperty($paid, 'paid', 'boolean');
		$served = $this->validateAndCleanProperty($served, 'served', 'boolean');
		
		$order = new Order($date, $time, $paid, $served);
		
		foreach ($menuitems as $menuItem) {
			$order->addMenuItem($menuItem);
		}
		
		$this->create($order, $foodTruckId);
		
		return $order;
		
	}
	
	public function editOrder($date, $time, $paid, $served, array $menuitems, $orderId, $foodTruckId) {
		
		$date = $this->validateAndCleanProperty($date, 'order date', 'date');
		$time = $this->validateAndCleanProperty($time, 'order time', 'time');
		$paid = $this->validateAndCleanProperty($paid, 'paid', 'boolean');
		$served = $this->validateAndCleanProperty($served, 'served', 'boolean');
		
		$this->modify('orderDate', $date, $orderId, $foodTruckId);
		$this->modify('orderTime', $time, $orderId, $foodTruckId);
		$this->modify('paid', $paid, $orderId, $foodTruckId);
		$this->modify('served', $served, $orderId, $foodTruckId);
		$this->modify('menuItems', $menuitems, $orderId, $foodTruckId);
		
	}
	
	public function getOrder($id, $foodTruckId) {
		
		return $this->read($id, $foodTruckId);
		
	}
	
	public function getOrders($foodTruckId) {
		
		return $this->readAll($foodTruckId);
		
	}
	
	public function deleteOrder($id, $foodTruckId) {
		
		$this->delete($id, $foodTruckId);
		
	}
	
	public function getOrderId(Order $order, $foodTruckId) {
		
		return $this->getId($order, $foodTruckId);
		
	}
	
}