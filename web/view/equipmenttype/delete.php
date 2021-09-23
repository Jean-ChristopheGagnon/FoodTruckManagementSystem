<?php

include '../../autoload.php';

$equipmentTypeController = new EquipmentTypeController();

if (isset($_GET['id'])) {

    try {
        $equipmentTypeController->deleteEquipmentType(intval($_GET['id']));
    } catch (Exception $e) {
        header("location: all.php?error=" . urlencode($e->getMessage()));
        exit();
    }

    header('location: all.php');
    exit();
} else {
    header("location: all.php?error=" . urlencode("No equipment type specified"));
}
