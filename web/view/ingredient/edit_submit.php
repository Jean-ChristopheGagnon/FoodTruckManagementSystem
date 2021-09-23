<?php

require_once '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['grandParentId'])) {
	header('location: ../menu/all.php?error='.urlencode('No menu specified'));
	exit();
}

$grandParentId = intval($_GET['grandParentId']);

if (!isset($_GET['parentId'])) {
    header("location: ../menuitem/all.php?parentId=".$grandParentId."&error=" . urlencode("A menu item must be specified"));
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
    header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=" . urlencode("An ingredient must be specified"));
}

$id = intval($_GET['id']);

$quantity = $form->post('quantity');
$typeId = $form->post('type');

$ingredientTypeController = new SupplyTypeController();

try {
	$type = $ingredientTypeController->getSupplyType($typeId);
} catch (Exception $e) {
	header("location: edit.php?parentId=".$parentId."&grandParentId=".$grandParentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

if(!$form->errors()) {
	$ingredientController = new IngredientController();
	try {
		$ingredientController->editIngredient($quantity, $type, $id, $parentId, $grandParentId);
	} catch (Exception $e) {
		header("location: edit.php?parentId=".$parentId."&grandParentId=".$grandParentId."&id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId);
	exit();
} else {
	header("location: edit.php?parentId=".$parentId."&grandParentId=".$grandParentId."&id=".$id."&error=" . urlencode($form->messages()));
}