<?php

include '../../autoload.php';

if (!isset($_GET['grandParentId'])) {
	header('location: ../menu/all.php?error='.urlencode('No menu specified'));
	exit();
}

$grandParentId = intval($_GET['grandParentId']);

if (!isset($_GET['parentId'])) {
	header('location: ../menuitem/all.php?parentId='.$grandParentId.'&error='.urlencode('No menu item specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

$menuItemController = new MenuItemController();

try {
	$menuItem = $menuItemController->getMenuItem($parentId, $grandParentId);
} catch (Exception $e) {
	header('location: ../menuitem/all.php?parentId='.$grandParentId.'&error='.urlencode($e->getMessage()));
	exit();
}

$ingredientController = new IngredientController();

try {
	$items = $ingredientController->getIngredients($parentId, $grandParentId);
} catch (Exception $e) {
	header('location: ../menuitem/all.php?parentId='.$grandParentId.'&error='.urlencode($e->getMessage()));
	exit();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Ingredients - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Ingredients - Food Truck Management System</h1>
    <h2>Menu item : <?=$menuItem->getName() ?></h2>
    <?php if (count($items) == 0) { ?>
        <p>There are no ingredients in this menu item yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Type</th>
			    <th>Quantity</th>
			    <th></th>
			    <th></th>
		    </tr>
	    </thead>
	    <tbody>
        <?php foreach ($items as $itemId => $item){ ?>
            <tr>
                <td><?= $item->getSupplyType()->getName() ?></td>
                <td><?= $item->getQuantity() ?></td>
                <td><a href="edit.php?grandParentId=<?=$grandParentId?>&parentId=<?=$parentId?>&id=<?=$itemId?>">Edit</a></td>
                <td><a href="delete.php?grandParentId=<?=$grandParentId?>&parentId=<?=$parentId?>&id=<?=$itemId?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php?parentId=<?=$parentId?>&grandParentId=<?=$grandParentId?>">Add an ingredient</a><br>
    <a href="../menuitem/all.php?parentId=<?=$grandParentId?>">Back</a>
</div></div></body>
</html>