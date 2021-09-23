<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = 'date,time,menuItems[]';

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

$menuItems = $foodTruck->getMenu()->getMenuItems();

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Create an order - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Create an order - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','', "create_submit.php?parentId=".$parentId);
    echo $form->input_text('date', 'Date:', date('Y-m-d'), '', '', 'Format: YYYY-MM-DD');
    echo $form->input_text('time', 'Time:', date('G:i'), '', '', 'Format: HH:MM in 24-hour');
    echo $form->input_checkbox('paid', 'Paid', 'paid', '', '', '', false);
    echo $form->input_checkbox('served', 'Served', 'served', '', '', '', false);
    echo $form->label('', 'Items to include');
    foreach ($menuItems as $id => $menuItem) {
    	echo $form->input_checkbox('menuItems[]', $menuItem->getName().' - $'.$menuItem->getPrice(), "id:".$id);
    }
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?= $parentId ?>">Back</a>
</div></div></body>
</html>
