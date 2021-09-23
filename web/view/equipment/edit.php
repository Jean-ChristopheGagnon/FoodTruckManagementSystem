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
	header("location: ../equipment/all.php?error=" . urlencode("An equipment must be specified"));
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

$equipementTypeController = new EquipmentTypeController();

try {
	$equipementTypes = $equipementTypeController->getEquipmentTypes();
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

try {
	$equipmentTypeId = $equipementTypeController->getEquipmentTypeId($equipment->getEquipmentType());
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&id=".$id."&error=".urlencode($e->getMessage()));
	exit();
}

$typeSelect = [];

foreach ($equipementTypes as $typeId => $type) {
	$typeSelect[$typeId] = $type->getName();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Edit an equipment - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Edit an equipment - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','', "edit_submit.php?parentId=".$parentId."&id=".$id);
    echo $form->input_text('quantity', 'Quantity:', $equipment->getQuantity());
    echo $form->input_text('date', 'Purchase Date:', $equipment->getPurchaseDate(), '', '', 'Format: YYYY-MM-DD');
    echo $form->input_select('type','Type: ','','','','',$equipmentTypeId, $typeSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?= $parentId ?>">Back</a>
</div></div></body>
</html>
