<?php
include '../../autoload.php';

$foodTruckController = new FoodTruckController();

$elements = $foodTruckController->getFoodTrucks();

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Food Trucks - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Food Trucks - Food Truck Management System</h1>
    <?php if (count($elements) == 0) { ?>
        <p>There is no food truck yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Location</th>
			    <th>Menu</th>
			    <th></th>
			    <th></th>
			    <th></th>
			    <th></th>
			    <th></th>
		    </tr>
	    </thead>
	    <tbody>
        <?php foreach ($elements as $elementId => $element){ ?>
            <tr>
                <td><?= $element->getLocation() ?></td>
                <td><?= $element->getMenu()->getName() ?></td>
                <td><a href="edit.php?id=<?=$elementId?>">Edit</a></td>
                <td><a href="../order/all.php?parentId=<?=$elementId?>">See orders</a></td>
                <td><a href="../equipment/all.php?parentId=<?=$elementId?>">See equipments</a></td>
                <td><a href="../supply/all.php?parentId=<?=$elementId?>">See supplies</a></td>
                <td><a href="delete.php?id=<?=$elementId?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php">Create a foodtruck</a>
    <br>
    <a href="/index.php">Main Menu</a>
</div></div></body>
</html>
