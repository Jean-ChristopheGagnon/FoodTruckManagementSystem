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

$quantity = $form->post('quantity');
$typeId = $form->post('type');

$ingredientTypeController = new SupplyTypeController();

try {
	$type = $ingredientTypeController->getSupplyType($typeId);
} catch (Exception $e) {
	header("location: create.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=".urlencode($e->getMessage()));
	exit();
}

if(!$form->errors()) {
	$ingredientController = new IngredientController();
	try {
		$ingredientController->createIngredient($quantity, $type, $parentId, $grandParentId);
	} catch (Exception $e) {
		header("location: create.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId);
	exit();
} else {
	header("location: create.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=" . urlencode($form->messages()));
}