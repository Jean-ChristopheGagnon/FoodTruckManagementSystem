<?php

require_once '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

if (!isset($_GET['parentId'])) {
    header("location: ../menu/all.php?error=" . urlencode("A menu must be specified"));
}

$parentId = intval($_GET['parentId']);

if (!isset($_GET['id'])) {
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

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Menu item - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Menu item - Food Truck Management System</h1>
    <h2>Menu : <?=$menu->getName() ?></h2>
    <?php
    echo $form->form_open('', '', "edit_submit.php?parentId=".$parentId."&id=".$id);
    echo $form->input_text('name', 'Name:', $menuItem->getName());
    echo $form->input_text('price', 'Price:', $menuItem->getPrice());
    echo $form->input_hidden('menuId', $parentId);
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php?parentId=<?=$parentId?>">Back</a>
</div></div></body>
</html>
