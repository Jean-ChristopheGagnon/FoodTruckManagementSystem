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
	header("location: all.php?error=" . urlencode("An supply must be specified"));
	exit();
}

$id = intval($_GET['id']);

$supplyController = new SupplyController();

try {
	$supply = $supplyController->getSupply($id, $parentId);
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&error=".urlencode($e->getMessage()));
	exit();
}

if (!$form->submit()) {
	header("location: edit.php?parentId=".$parentId."&id=".$id);
}

$quantity = $form->post('quantity');
$typeId = $form->post('type');

$supplyTypeController = new SupplyTypeController();

try {
	$type = $supplyTypeController->getSupplyType($typeId);
} catch (Exception $e) {
	header("location: edit.php?parentId=".$parentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

if(!$form->errors()) {
	$supplyController = new SupplyController();
	try {
		$supplyController->editSupply($quantity, $type, $id, $parentId);
	} catch (Exception $e) {
		header("location: edit.php?parentId=".$parentId."&id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId);
	exit();
} else {
	header("location: edit.php?parentId=".$parentId."&id=".$id."&error=" . urlencode($form->messages()));
}