<?php

include '../../autoload.php';

$menuController = new MenuController();

if (isset($_GET['id'])) {

    // All the error handling is already done by the MenuController
    try {
        $menuController->deleteMenu($_GET['id']);
    } catch (Exception $e) {
        header("location: all.php?error=" . urlencode($e->getMessage()));
        exit();
    }

    header('location: all.php');
    exit();
} else {
    header("location: all.php?error=" . urlencode("No menu specified"));
}
