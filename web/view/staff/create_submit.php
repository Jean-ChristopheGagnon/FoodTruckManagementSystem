<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!$form->submit()) {
	header("location: create.php");
}

$name = $form->post('name');
$job = $form->post('job');
if(!$form->errors()) {
	$staffController = new StaffController();
	try {
		$staffController->createStaff($name, $job, []);
	} catch (Exception $e) {
		header("location: create.php?error=" . urlencode($e->getMessage()));
		exit();
	}
	header("location: all.php");
} else {
	header("location: create.php?error=" . urlencode($form->messages()));
}