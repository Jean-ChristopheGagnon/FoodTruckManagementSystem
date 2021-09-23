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

$ingredientTypeController = new SupplyTypeController();

try {
	$ingredientTypes = $ingredientTypeController->getSupplyTypes();
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&error=".urlencode($e->getMessage()));
	exit();
}

$typeSelect = [];

foreach ($ingredientTypes as $id => $type) {
	$typeSelect[$id] = $type->getName();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Add an ingredient - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Add an ingredient - Food Truck Management System</h1>
    <?php
    echo $form->form_open('', '', "create_submit.php?parentId=".$parentId."&grandParentId=".$grandParentId);
    echo $form->input_select('type','Type:','','','','','', $typeSelect);
    echo $form->input_text('quantity', 'Quantity:', 1);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?=$parentId?>&grandParentId=<?=$grandParentId?>">Back</a>
</div></div></body>
</html>
