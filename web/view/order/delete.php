<?php

include '../../autoload.php';

$orderController = new OrderController();

if (!isset($_GET['parentId'])) {
	header("location: ../foodtruck/all.php?error=" . urlencode("No food truck specified"));
	exit();
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
	header("location: all.php?parentId=".$parentId."&error=" . urlencode("No order specified"));
	exit();
}

$id = intval($_GET['id']);
	
try {
	$orderController->deleteOrder($id, $parentId);
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
	exit();
}

header('location: all.php?parentId='.$parentId);
exit();
