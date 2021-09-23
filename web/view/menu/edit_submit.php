<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

$menuController = new MenuController();

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("No menu specified"));
	exit();
}

$id = intval($_GET['id']);

try {
	$menu = $menuController->getMenu($id);
} catch (Exception $e) {
	header("location: all.php?error=" . urlencode($e->getMessage()));
	exit();
}

if (!$form->submit()) {
	header("location: edit.php?id=".$id);
}
$name = $form->post('name');
$menuId = $form->post('menuId');
if(!$form->errors()) {
	$menuController = new MenuController();
	try {
		$menuController->editMenu($menuId, $name);
	} catch (Exception $e) {
		header("location: edit.php?id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
	exit();
} else {
	header("location: edit.php?id=".$id."&error=" . urlencode($form->messages()));
}