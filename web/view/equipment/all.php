<?php
include '../../autoload.php';

if (!isset($_GET['parentId'])) {
	header('location: ../foodtruck/all.php?error='.urlencode('No foodtruck specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

$equipmentController = new EquipmentController();

$elements = $equipmentController->getEquipments($parentId);

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Menus - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Equipments - Food Truck Management System</h1>
    <?php if (count($elements) == 0) { ?>
        <p>There are no equipments yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
        	<tr>
	        	<th>Type</th>
	        	<th>Quantity</th>
	        	<th>Purchase Date</th>
	        	<th></th>
	        	<th></th>
        	</tr>
    	</thead>
    	<tbody>
        <?php foreach ($elements as $elementId => $element){ ?>
            <tr>
	            <td><?= $element->getEquipmentType()->getName() ?></td>
                <td><?= $element->getQuantity() ?></td>
                <td><?= $element->getPurchaseDate() ?></td>
                <td><a href="edit.php?id=<?=$elementId?>&parentId=<?=$parentId?>">Edit</a></td>
                <td><a href="delete.php?id=<?=$elementId?>&parentId=<?=$parentId?>">Delete</a></td>
            </tr>
        <?php } ?>
        </tbody>
    </table>
    <a href="create.php?parentId=<?=$parentId?>">Create an equipment</a>
    <br>
    <a href="../foodtruck/all.php">Back</a>
</div></div></body>
</html>
