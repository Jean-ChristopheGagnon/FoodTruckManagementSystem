<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!$form->submit()) {
	header('location: create.php');
}

$location = $form->post('location');
$menuId = $form->post('menu');
$menuController = new MenuController();
try {
	$menu = $menuController->getMenu($menuId);
} catch (Exception $e) {
	header("location: create.php?error=" . urlencode($e->getMessage()));
	exit();
}
if(!$form->errors()) {
	$foodTruckController = new FoodTruckController();
	try {
		$foodTruckController->createFoodTruck($location, $menu);
	} catch (Exception $e) {
		header("location: create.php?error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
} else {
	header("location: create.php?error=" . urlencode($form->messages()));
}