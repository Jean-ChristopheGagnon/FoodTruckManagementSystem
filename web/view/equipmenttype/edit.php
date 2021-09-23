<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

$equipmentTypeController = new EquipmentTypeController();

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("No equipment type specified"));
	exit();
}

$id = intval($_GET['id']);

try {
	$equipmentType = $equipmentTypeController->getEquipmentType($id);
} catch (Exception $e) {
	header("location: all.php?error=" . urlencode($e->getMessage()));
	exit();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Edit equipment type - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Edit equipment type - Food Truck Management System</h1>
    <?php 
    echo $form->form_open('', '', "edit_submit.php?id=".$id);
    echo $form->input_text('name', 'Name:', $equipmentType->getName());
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php">Back</a>
</div></div></body>
</html>
