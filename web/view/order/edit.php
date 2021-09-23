<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = 'date,time,menuItems[]';

if (!isset($_GET['parentId'])) {
	header("location: ../foodtruck/all.php?error=" . urlencode("A food truck must be specified"));
	exit();
}

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("An order must be specified"));
	exit();
}

$id = intval($_GET['id']);

$parentId = intval($_GET['parentId']);

$orderController = new OrderController();

$order = $orderController->getOrder($id, $parentId);

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
    <title>Edit an order - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Edit an order - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','', "edit_submit.php?parentId=".$parentId."&id=".$id);
    echo $form->input_text('date', 'Date:', $order->getOrderDate(), '', '', 'Format: YYYY-MM-DD');
    echo $form->input_text('time', 'Time:', $order->getOrderTime(), '', '', 'Format: HH:MM in 24-hour');
    echo $form->input_checkbox('paid', 'Paid', 'paid', '', '', '', boolval($order->isPaid()));
    echo $form->input_checkbox('served', 'Served', 'served', '', '', '', boolval($order->isServed()));
    echo $form->label('', 'Items to include');
    foreach ($menuItems as $id => $menuItem) {
	    $selected = false;
	    foreach ($order->getMenuItems() as $item) {
		    if ($item->getName() === $menuItem->getName()) {
			    $selected = true;
		    }
	    }
    	echo $form->input_checkbox('menuItems[]', $menuItem->getName().' - $'.$menuItem->getPrice(), "id:".$id, '', '', '', $selected);
    }
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?= $parentId ?>">Back</a>
</div></div></body>
</html>
