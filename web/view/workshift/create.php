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

$foodTruckController = new FoodTruckController();

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
    <title>Create workshift - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Create workshift - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','','create_submit.php?parentId='.$parentId);
    echo $form->input_text('date', 'Date:','', '', '', 'Format: YYYY-MM-DD');
    echo $form->input_text('startTime', 'Start Time:','', '', '', 'Format: HH:MM in 24-hour');
    echo $form->input_text('endTime', 'End Time:','', '', '', 'Format: HH:MM in 24-hour');
    echo $form->input_select('foodTruck', 'Food Truck:', '', '', '', '', '', $foodTruckSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?=$parentId ?>">Back</a>
</div></div></body>
</html>
