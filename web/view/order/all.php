<?php
include '../../autoload.php';

if (!isset($_GET['parentId'])) {
	header('location: ../foodtruck/all.php?error='.urlencode('No foodtruck specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

$orderController = new OrderController();

$elements = $orderController->getOrders($parentId);

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Menus - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Orders - Food Truck Management System</h1>
    <?php if (count($elements) == 0) { ?>
        <p>There are no orders yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Date</th>
			    <th>Time</th>
			    <th>Paid</th>
			    <th>Served</th>
			    <th>Items Ordered</th>
			    <th></th>
			    <th></th>
		    </tr>
	    </thead>
	    <tbody>
        <?php foreach ($elements as $elementId => $element){ ?>
            <tr>
                <td><?= $element->getOrderDate() ?></td>
                <td><?= $element->getOrderTime() ?></td>
                <td><?= $element->getPaid() ? "Yes":"No" ?></td>
                <td><?= $element->getServed() ? "Yes":"No" ?></td>
                <td>
                <?php
                    foreach($element->getMenuItems() as $menuItem) {
                        echo $menuItem->getName().'<br>';
                    }
                ?>
                </td>
                <td><a href="edit.php?id=<?=$elementId?>&parentId=<?=$parentId?>">Edit</a></td>
                <td><a href="delete.php?id=<?=$elementId?>&parentId=<?=$parentId?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php?parentId=<?=$parentId?>">Create an order</a>
    <br>
    <a href="../foodtruck/all.php">Back</a>
</div></div></body>
</html>
