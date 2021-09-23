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

$supplyTypeController = new SupplyTypeController();

try {
	$supplyTypes = $supplyTypeController->getSupplyTypes();
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&error=".urlencode($e->getMessage()));
	exit();
}

$typeSelect = [];

foreach ($supplyTypes as $id => $type) {
	$typeSelect[$id] = $type->getName();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Create an supply - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Create an supply - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','', "create_submit.php?parentId=".$parentId);
    echo $form->input_text('quantity', 'Quantity:', 1);
    echo $form->input_select('type','Type:','','','','','', $typeSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?= $parentId ?>">Back</a>
</div></div></body>
</html>
