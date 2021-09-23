<?php

require_once '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['parentId'])) {
    header("location: ../menu/all.php?error=" . urlencode("A menu must be specified"));
}

$parentId = intval($_GET['parentId']);

$menuController = new MenuController();

try {
	$menu = $menuController->getMenu($parentId);
} catch (Exception $e) {
	header("location: ../menu/all.php?error=" . urlencode("A menu must be specified"));
}

$menuItemController = new MenuItemController();

if (!$form->submit()) {
	header("location: create.php?parentId=".$parentId);
}

$name = $form->post('name');
$price = $form->post('price');
$parentId = $form->post('menuId');

if(!$form->errors()) {
	try {
		$menuItemController->createMenuItem($name, $price, $parentId);
	} catch (Exception $e) {
		header("location: create.php?parentId=".$parentId."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: ../menuitem/all.php?parentId=".$parentId);
	exit();
} else {
	header("location: create.php?parentId=".$parentId."&error=" . urlencode($form->messages()));
}