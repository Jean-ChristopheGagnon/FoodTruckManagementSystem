<?php

include '../../autoload.php';

$menuItemController = new MenuItemController();

if (isset($_GET['parentId'])) {
	$menuId = intval($_GET['parentId']);
} else {
	header("location: ../menu/all.php?error=" . urlencode("No menu specified"));
	exit();
}

if (isset($_GET['id'])) {
	
	$menuItemId = intval($_GET['id']);

    // All the error handling is already done by the MenuItemController
    try {
        $menuItemController->deleteMenuItem($menuItemId, $menuId);
    } catch (Exception $e) {
        header("location: all.php?parentId=".$menuId."error=" . urlencode($e->getMessage()));
        exit();
    }

    header('location: all.php?parentId='.$menuId);
    exit();
} else {
    header("location: all.php?parentId=".$menuId."error=" . urlencode("No menu item specified"));
    exit();
}
