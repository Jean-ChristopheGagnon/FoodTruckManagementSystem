<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

$foodTruckController = new FoodTruckController();

if (isset($_GET['id'])) {
	$elementId = intval($_GET['id']);
	try {
		$element = $foodTruckController->getFoodTruck($elementId);
	} catch (Exception $e) {
		header("location: all.php?error=" . urlencode($e->getMessage()));
		exit();
	}
} else {
	header("location: all.php?error=" . urlencode("No foodtruck specified"));
	exit();
}

$menuController = new MenuController();

$menus = $menuController->getMenus();

$menuSelect = [];
foreach ($menus as $id => $menu) {
	$menuSelect[$id] = $menu->getName();
}

// TODO : Could raise an uncaught exception if the menu does not exist
$menuId = $menuController->getMenuId($element->getMenu());

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Edit a Food Truck - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Edit a Food Truck - Food Truck Management System</h1>
    <?php
    echo $form->form_open('', '', "edit_submit.php?id=".$elementId);
    echo $form->input_text('location', 'Location:', $element->getLocation());
    echo $form->input_select('menu', 'Menu: ','','','','',$menuId,$menuSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php">Back</a>
</div></div></body>
</html>
