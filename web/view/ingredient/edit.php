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
    header("location: ../menuitem/all.php?parentId=".$grandParentId."error=" . urlencode("A menu item must be specified"));
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
    header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=" . urlencode("An ingredient must be specified"));
}

$id = intval($_GET['id']);

$ingredientTypeController = new SupplyTypeController();

try {
	$ingredientTypes = $ingredientTypeController->getSupplyTypes();
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=".urlencode($e->getMessage()));
	exit();
}

$typeSelect = [];

foreach ($ingredientTypes as $ingredientTypeId => $type) {
	$typeSelect[$ingredientTypeId] = $type->getName();
}

$ingredientController = new IngredientController();

try {
	$ingredient = $ingredientController->getIngredient($id, $parentId, $grandParentId);
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=".urlencode($e->getMessage()));
	exit();
}

try {
	$ingredientTypeId = $ingredientTypeController->getSupplyTypeId($ingredient->getSupplyType());
} catch (Exception $e) {
	// Default valuye if the selected type does not exist anymore
	$ingredientTypeId = 0;
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Modify an ingredient - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Modify an ingredient - Food Truck Management System</h1>
    <?php
    echo $form->form_open('', '', "edit_submit.php?parentId=".$parentId."&grandParentId=".$grandParentId);
    echo $form->input_select('type','Type:','','','','', $ingredientTypeId, $typeSelect);
    echo $form->input_text('quantity', 'Quantity:', $ingredient->getQuantity());
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?grandParentId=<?=$grandParentId?>&parentId=<?=$parentId?>">Back</a>
</div></div></body>
</html>
