<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['id'])) {
	header("location: all.php?error=" . urlencode("No employee specified"));
	exit();
}

$id = intval($_GET['id']);

$staffController = new StaffController();

$staff = $staffController->getStaff($id);

if (!$form->submit()) {
	header("location: edit.php?id=".$id);
}

$name = $form->post('name');
$job = $form->post('job');
if(!$form->errors()) {
	$staffController = new StaffController();
	try {
		$staff = $staffController->getStaff($id);
		$staffController->editStaff($name, $job, $staff->getWorkShifts(), $id);
	} catch (Exception $e) {
		header("location: edit.php?id=".$id."&error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
} else {
	header("location: edit.php?id=".$id."error=" . urlencode($form->messages()));
}