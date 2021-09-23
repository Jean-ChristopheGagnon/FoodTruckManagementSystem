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

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("An equipment must be specified"));
	exit();
}

$id = intval($_GET['id']);

$equipmentController = new EquipmentController();

try {
	$equipment = $equipmentController->getEquipment($id, $parentId);
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&error=".urlencode($e->getMessage()));
	exit();
}

if (!$form->submit()) {
	header("location: edit.php?parentId=".$parentId."&id=".$id);
}

$date = $form->post('date');
$quantity = $form->post('quantity');
$typeId = $form->post('type');

$equipmentTypeController = new EquipmentTypeController();

try {
	$type = $equipmentTypeController->getEquipmentType($typeId);
} catch (Exception $e) {
	header("location: edit.php?parentId=".$parentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

if(!$form->errors()) {
	$equipmentController = new EquipmentController();
	try {
		$equipmentController->editEquipment($quantity, $date, $type, $id, $parentId);
	} catch (Exception $e) {
		header("location: edit.php?parentId=".$parentId."&id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId);
	exit();
} else {
	header("location: edit.php?parentId=".$parentId."&id=".$id."&error=" . urlencode($form->messages()));
}