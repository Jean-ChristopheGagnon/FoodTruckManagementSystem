<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['parentId'])) {
	header('location: ../staff/all.php?error='.urlencode('No employee specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
	header('location: ../staff/all.php?error='.urlencode('No workshift specified'));
	exit();
}

$id = intval($_GET['id']);

$workShiftController = new WorkShiftController();

try {
	$workShift = $workShiftController->getWorkShift($id, $parentId);
} catch (Exception $e) {
	header('location: all.php?parentId='.$parentId.'&error='.urlencode($e->getMessage()));
	exit();
}

$foodTruckController = new FoodTruckController();

try {
	$foodTruckId = $foodTruckController->getFoodTruckId($workShift->getFoodTruck());
} catch (Exception $e) {
	// Set a default value if the food truck does not exist
	$foodTruckid = 0;
}

try {
	$foodTrucks = $foodTruckController->getFoodTrucks();
} catch (Exception $e) {
	header('location: all.php?parentId='.$parentId.'&error='.urlencode($e->getMessage()));
	exit();
}

$foodTruckSelect = [];

foreach ($foodTrucks as $foodTruckid => $foodTruck) {
	$foodTruckSelect[$foodTruckid] = $foodTruck->getLocation();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Create work shift - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Create work shift - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','','edit_submit.php?parentId='.$parentId."&id=".$id);
    echo $form->input_text('date', 'Date:', $workShift->getWorkDate(), '', '', 'Format: YYYY-MM-DD');
    echo $form->input_text('startTime', 'Start Time:', $workShift->getStartTime(), '', '', 'Format: HH:MM in 24-hour');
    echo $form->input_text('endTime', 'End Time:', $workShift->getEndTime(), '', '', 'Format: HH:MM in 24-hour');
    echo $form->input_select('foodTruck', 'Food Truck:', '', '', '', '', $foodTruckId, $foodTruckSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?=$parentId ?>">Back</a>
</div></div></body>
</html>
