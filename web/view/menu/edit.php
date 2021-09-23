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

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Change menu name - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Change menu name - Food Truck Management System</h1>
    <?php 
    echo $form->form_open('', '', "edit_submit.php?id=".$id);
    echo $form->input_hidden('menuId', $id);
    echo $form->input_text('name', 'Name:', $menu->getName());
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php">Back</a>
</div></div></body>
</html>
