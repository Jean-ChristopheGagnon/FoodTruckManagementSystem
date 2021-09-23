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
	header("location: ../supply/all.php?error=" . urlencode("An supply must be specified"));
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

$supplyTypeController = new SupplyTypeController();

try {
	$supplyTypes = $supplyTypeController->getSupplyTypes();
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

try {
	$supplyTypeId = $supplyTypeController->getSupplyTypeId($supply->getSupplyType());
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

$typeSelect = [];

foreach ($supplyTypes as $typeId => $type) {
	$typeSelect[$typeId] = $type->getName();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Edit an supply - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Edit an supply - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','', "edit_submit.php?parentId=".$parentId."&id=".$id);
    echo $form->input_text('quantity', 'Quantity:', $supply->getQuantity());
    echo $form->input_select('type','Type: ','','','','',$supplyTypeId, $typeSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?= $parentId ?>">Back</a>
</div></div></body>
</html>
