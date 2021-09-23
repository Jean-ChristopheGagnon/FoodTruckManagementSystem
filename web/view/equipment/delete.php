<?php

include '../../autoload.php';

$equipmentController = new EquipmentController();

if (!isset($_GET['parentId'])) {
	header("location: ../foodtruck/all.php?error=" . urlencode("No food truck specified"));
	exit();
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
	header("location: all.php?parentId=".$parentId."&error=" . urlencode("No equipment specified"));
	exit();
}

$id = intval($_GET['id']);
	
try {
	$equipmentController->deleteEquipment($id, $parentId);
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
	exit();
}

header('location: all.php?parentId='.$parentId);
exit();
