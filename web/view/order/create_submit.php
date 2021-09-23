<?php
	
include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');
$form->required = 'date,time,menuItems[]';
	
if (!isset($_GET['parentId'])) {
	header("location: ../foodtruck/all.php?error=" . urlencode("A food truck must be specified"));
	exit();
}

$parentId = intval($_GET['parentId']);

$foodTruckController = new FoodTruckController();

try {
	$foodTruck = $foodTruckController->getFoodTruck($parentId);
} catch (Exception $e) {
	header("location: ../foodtruck/all.php?error=" . urlencode("A food truck must be specified"));
	exit();
}

if (!$form->submit()) {
	header("location: create.php?parentId=".$parentId);
}

$date = $form->post('date');
$time = $form->post('time');
$paid = $form->post('paid') === "paid" ? true : false;
$served = $form->post('served') === "served" ? true : false;
$menuItemIds = $form->post('menuItems[]');

$menuItems = $foodTruck->getMenu()->getMenuItems();

$menuItemsToInclude = [];
if (is_array($menuItemIds)) {
	foreach($menuItemIds as $menuItemId) {
		// Ignore the items that does not exist anymore.
		/* This error is really unlikely to happen.
		 * Happens if someone delete a menu item while someone
		 * else is adding this menu item to the order.
		 */
		 $id = substr($menuItemId, 3);
		if (array_key_exists($id, $menuItems)) {
			array_push($menuItemsToInclude, $menuItems[$id]);
		}
	}
}

if(!$form->errors()) {
	$orderController = new OrderController();
	try {
		$orderController->createOrder($date, $time, $paid, $served, $menuItemsToInclude, $parentId);
	} catch (Exception $e) {
		header("location: create.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId);
	exit();
} else {
	header("location: create.php?parentId=".$parentId."&error=" . urlencode($form->messages()));
}