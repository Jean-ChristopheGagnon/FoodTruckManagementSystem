<?php

include '../../autoload.php';

$foodTruckController = new FoodTruckController();

if (isset($_GET['id'])) {

	try {
		$foodTruckController->deleteFoodTruck($_GET['id']);
	} catch (Exception $e) {
		header("location: all.php?error=" . urlencode($e->getMessage()));
		exit();
	}

	header('location: all.php');
	exit();
} else {
	header("location: all.php?error=" . urlencode("No food truck specified"));
}
