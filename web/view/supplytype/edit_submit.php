<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

$supplyTypeController = new SupplyTypeController();

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("No supply type specified"));
	exit();
}

$id = intval($_GET['id']);

if (!$form->submit()) {
	header("location: edit.php?id=".$id);
	exit();
}
$name = $form->post('name');
if(!$form->errors()) {
	try {
		$supplyTypeController->editSupplyType($name, $id);
	} catch (Exception $e) {
		header("location: edit.php?id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
	exit();
}
header("location: edit.php?id=".$id."&error=" . urlencode($form->messages()));