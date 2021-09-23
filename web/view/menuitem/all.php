<?php

include '../../autoload.php';

if (!isset($_GET['parentId'])) {
	header('location: ../menu/all.php?error='.urlencode('No menu specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

$menuItemController = new MenuItemController();

try {
	$items = $menuItemController->getMenuItems($parentId);
} catch (Exception $e) {
	header('location: ../menu/all.php?error='.urlencode($e->getMessage()));
	exit();
}

$menuController = new MenuController();

try {
	$menu = $menuController->getMenu($parentId);
} catch (Exception $e) {
	header('location: ../menu/all.php?error='.urlencode($e->getMessage()));
	exit();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Menu items - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Menu items - Food Truck Management System</h1>
    <h2>Menu : <?=$menu->getName() ?></h2>
    <?php if (count($items) == 0) { ?>
        <p>There are no items in this menu yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Name</th>
			    <th>Price ($)</th>
			    <th>Ingredients</th>
			    <th></th>
			    <th></th>
			    <th></th>
		    </tr>
	    </thead>
	    <tbody>
        <?php foreach ($items as $itemId => $item){ ?>
            <tr>
                <td><?= $item->getName() ?></td>
                <td><?= $item->getPrice() ?></td>
                <td><?php
	                foreach ($item->getSupplies() as $supply) {
		                echo $supply->getSupplyType()->getName() . ' : ' . $supply->getQuantity() . '<br>';
	                }
                ?></td>
                <td><a href="../ingredient/all.php?grandParentId=<?=$parentId?>&parentId=<?=$itemId?>">Edit ingredients</a></td>
                <td><a href="edit.php?parentId=<?=$parentId?>&id=<?=$itemId?>">Edit</a></td>
                <td><a href="delete.php?parentId=<?=$parentId?>&id=<?=$itemId?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php?parentId=<?=$parentId?>">Create menu item</a><br>
    <a href="../menu/all.php">Back</a>
</div></div></body>
</html>
