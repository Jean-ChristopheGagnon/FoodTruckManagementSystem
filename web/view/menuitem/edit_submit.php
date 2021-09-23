<?php

require_once '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['parentId'])) {
    header("location: ../menu/all.php?error=" . urlencode("A menu must be specified"));
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['menuItemId'])) {
	header("location: ../menuitem/all.php?parentId=".$parentId."&error=" . urlencode("A menu item must be specified"));
}

$id = intval($_GET['id']);

$menuController = new MenuController();

try {
	$menu = $menuController->getMenu($parentId);
} catch (Exception $e) {
	header("location: ../menu/all.php?error=" . urlencode("A menu must be specified"));
}

$menuItemController = new MenuItemController();

try {
	$menuItem = $menuItemController->getMenuItem($id, $parentId);
} catch (Exception $e) {
	header("location: ../menuitem/edit.php?parentId=".$parentId."&id=".$id."error=" . urlencode("A menu must be specified"));
}

if ($form->submit()) {
	header("location: ../menuitem/edit.php?parentId=".$parentId."&id=".$id);
}

$name = $form->post('name');
$price = $form->post('price');
$parentId = $form->post('menuId');

if(!$form->errors()) {
	try {
		$menuItemController->editMenuItem($name, $price, $id, $parentId);
	} catch (Exception $e) {
		header("location: ../menuitem/create.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: ../menuitem/all.php?parentId=".$parentId);
	exit();
} else {
	header("location: ../menuitem/create.php?parentId=".$parentId."&error=" . urlencode($form->messages()));
}