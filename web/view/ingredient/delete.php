<?php

require_once '../../autoload.php';

if (!isset($_GET['grandParentId'])) {
	header('location: ../menu/all.php?error='.urlencode('No menu specified'));
	exit();
}

$grandParentId = intval($_GET['grandParentId']);

if (!isset($_GET['parentId'])) {
    header("location: ../menuitem/all.php?parentId=".$grandParentId."error=" . urlencode("A menu item must be specified"));
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
    header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=" . urlencode("An ingredient must be specified"));
}

$id = intval($_GET['id']);

$ingredientController = new IngredientController();

try {
	$ingredientController->deleteIngredient($id, $parentId, $grandParentId);
} catch (Exception $e) {
	header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId."&error=" . urlencode($e->getMessage()));
	exit();
}

header("location: all.php?parentId=".$parentId."&grandParentId=".$grandParentId);