<?php

require_once '../../autoload.php';

if (!isset($_GET['parentId'])) {
	header("location: ../staff/all.php?error=" . urlencode("An employee must be specified"));
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
	header('location: ../workshift/all.php?parentId='.$parentId.'&error='.urlencode('No workshift specified'));
	exit();
}

$id = intval($_GET['id']);

$workShiftController = new WorkShiftController();

try {
	$workShiftController->deleteWorkShift($id, $parentId);
} catch (Exception $e) {
	header("location: ../workshift/all.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
	exit();
}

header("location: ../workshift/all.php?parentId=".$parentId);