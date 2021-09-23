<?php
include '../../autoload.php';

if (!isset($_GET['parentId'])) {
	header('location: ../foodtruck/all.php?error='.urlencode('No foodtruck specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

$supplyController = new SupplyController();

$elements = $supplyController->getSupplies($parentId);

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Menus - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Supplies - Food Truck Management System</h1>
    <?php if (count($elements) == 0) { ?>
        <p>There are no supplies yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Supply Type</th>
			    <th>Quantity</th>
			    <th></th>
			    <th></th>
	    </thead>
	    <tbody>
        <?php foreach ($elements as $elementId => $element){ ?>
            <tr>
	            <td><?= $element->getSupplyType()->getName() ?></td>
                <td><?= $element->getQuantity() ?></td>
                <td><a href="edit.php?id=<?=$elementId?>&parentId=<?=$parentId?>">Edit</a></td>
                <td><a href="delete.php?id=<?=$elementId?>&parentId=<?=$parentId?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php?parentId=<?=$parentId?>">Create an supply</a>
    <br>
    <a href="../foodtruck/all.php">Back</a>
</div></div></body>
</html>
