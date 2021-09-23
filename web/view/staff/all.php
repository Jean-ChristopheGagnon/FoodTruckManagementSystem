<?php

include '../../autoload.php';

$staffController = new StaffController();

$staffs = $staffController->getStaffs();

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Staff - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Staff - Food Truck Management System</h1>
    <?php if (count($staffs) == 0) { ?>
        <p>There are no employee yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Name</th>
			    <th>Job</th>
			    <th></th>
			    <th></th>
			    <th></th>
	    </thead>
	    <tbody>
        <?php foreach ($staffs as $id => $staff){ ?>
            <tr>
                <td><?= $staff->getName() ?></td>
                <td><?= $staff->getJob() ?></td>
                <td><a href="edit.php?id=<?=$id?>">Edit</a></td>
                <td><a href="../workshift/all.php?parentId=<?=$id?>">See workshifts</a></td>
                <td><a href="delete.php?id=<?=$id?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php">Create an employee</a>
    <br>
    <a href="/index.php">Main Menu</a>
</div></div></body>
</html>