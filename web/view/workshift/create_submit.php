<?php

require_once '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['parentId'])) {
	header("location: ../staff/all.php?error=" . urlencode("An employee must be specified"));
}

$parentId = intval($_GET['parentId']);

$staffController = new StaffController();

try {
	$staff = $staffController->getStaff($parentId);
} catch (Exception $e) {
	header("location: ../staff/all.php?error=" . urlencode("An employee must be specified"));
}

$workShiftController = new WorkShiftController();

if (!$form->submit()) {
	header("location: create.php?parentId=".$parentId);
}

$date = $form->post('date');
$startTime = $form->post('startTime');
$endTime = $form->post('endTime');
$foodTruckId = $form->post('foodTruck');

$foodTruckController = new FoodTruckController();

try {
	$foodTruck = $foodTruckController->getFoodTruck($foodTruckId);
} catch (Exception $e) {
	header('location: create.php?parentId='.$parentId.'&error='.urlencode('This selected food truck does not exist'));
	exit();
}

if(!$form->errors()) {
	try {
		$workShiftController->createWorkShift($date, $startTime, $endTime, $foodTruck, $parentId);
	} catch (Exception $e) {
		header("location: create.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: ../workshift/all.php?parentId=".$parentId);
	exit();
} else {
	header("location: create.php?parentId=".$parentId."&error=" . urlencode($form->messages()));
}