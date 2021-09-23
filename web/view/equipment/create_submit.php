<?php
	
include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');
$form->required = '*';
	
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
$quantity = $form->post('quantity');
$typeId = $form->post('type');

$equipmentTypeController = new EquipmentTypeController();

try {
	$type = $equipmentTypeController->getEquipmentType($typeId);
} catch (Exception $e) {
	header("location: create.php?parentId=".$parentId."&error=".urlencode($e->getMessage()));
	exit();
}

if(!$form->errors()) {
	$equipmentController = new EquipmentController();
	try {
		$equipmentController->createEquipment($quantity, $date, $type, $parentId);
	} catch (Exception $e) {
		header("location: create.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId);
	exit();
} else {
	header("location: create.php?parentId=".$parentId."&error=" . urlencode($form->messages()));
}