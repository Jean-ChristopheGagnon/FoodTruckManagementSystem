<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!$form->submit()) {
	header("location: create.php");
	exit();
}

$name = $form->post('name');
if(!$form->errors()) {
	$equipmentTypeController = new EquipmentTypeController();
	try {
		$equipmentTypeController->createEquipmentType($name);
	} catch (Exception $e) {
		header("location: create.php?error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
} else {
	header("location: create.php?error=" . urlencode($form->messages()));
}