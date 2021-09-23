<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = 'date,time,menuItems[]';

if (!isset($_GET['parentId'])) {
	header("location: ../foodtruck/all.php?error=" . urlencode("A food truck must be specified"));
	exit();
}

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("An order must be specified"));
	exit();
}

$id = intval($_GET['id']);

$parentId = intval($_GET['parentId']);

$orderController = new OrderController();

$order = $orderController->getOrder($id, $parentId);

$foodTruckController = new FoodTruckController();

try {
	$foodTruck = $foodTruckController->getFoodTruck($parentId);
} catch (Exception $e) {
	header("location: ../foodtruck/all.php?error=" . urlencode("A food truck must be specified"));
	exit();
}

$menuItems = $foodTruck->getMenu()->getMenuItems();

if (!$form->submit()) {
	header('location: edit.php?parentId='.$parentId."&id=".$id);
}
$date = $form->post('date');
$time = $form->post('time');
$paid = $form->post('paid');
$served = $form->post('served');
$menuItemIds = $form->post('menuItems[]');

$menuItemsToInclude = [];
if (is_array($menuItemIds)) {
	foreach($menuItemIds as $menuItemId) {
		// Ignore the items that does not exist anymore.
		/* This error is really unlikely to happen.
		 * Happens if someone delete a menu item while someone
		 * else is adding this menu item to the order.
		 */
		 $itemId = substr($menuItemId, 3);
		if (array_key_exists($itemId, $menuItems)) {
			array_push($menuItemsToInclude, $menuItems[$itemId]);
		}
	}
}

if(!$form->errors()) {
	$orderController = new OrderController();
	try {
		$orderController->editOrder($date, $time, $paid, $served, $menuItemsToInclude, $id, $parentId);
	} catch (Exception $e) {
		header("location: edit.php?parentId=".$parentId."&id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId);
	exit();
} else {
	header("location: edit.php?parentId=".$parentId."&id=".$id."&error=" . urlencode($form->messages()));
}