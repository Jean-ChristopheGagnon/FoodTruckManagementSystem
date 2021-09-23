<?php

include '../../autoload.php';

$supplyTypeController = new SupplyTypeController();

if (isset($_GET['id'])) {

    try {
        $supplyTypeController->deleteSupplyType(intval($_GET['id']));
    } catch (Exception $e) {
        header("location: all.php?error=" . urlencode($e->getMessage()));
        exit();
    }

    header('location: all.php');
    exit();
} else {
    header("location: all.php?error=" . urlencode("No supply type specified"));
}
