<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

$menuController = new MenuController();

$menus = $menuController->getMenus();

$menuSelect = [];
foreach ($menus as $id => $menu) {
	$menuSelect[$id] = $menu->getName();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Create a Food Truck - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Create a Food Truck - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','','create_submit.php');
    echo $form->input_text('location', 'Location:');
    echo $form->input_select('menu', 'Menu: ','','','','','0',$menuSelect);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php">Back</a>
</div></div></body>
</html>
