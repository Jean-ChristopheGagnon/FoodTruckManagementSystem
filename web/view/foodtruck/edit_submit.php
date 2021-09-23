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

if (!$form->submit()) {
	header("location: edit.php?id=".$id);
}

$location = $form->post('location');
$menuId = $form->post('menu');
$menuController = new MenuController();
try {
	$menu = $menuController->getMenu($menuId);
} catch (Exception $e) {
	header("location: edit.php?id=".$id."error=" . urlencode($e->getMessage()));
	exit();
}
if(!$form->errors()) {
	$foodTruckController = new FoodTruckController();
	try {
		$foodTruckController->editFoodTruck($location, $menu, $elementId);
	} catch (Exception $e) {
		header("location: edit.php?id=".$id."error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
} else {
	header("location: edit.php?id=".$id."error=" . urlencode($form->messages()));
}