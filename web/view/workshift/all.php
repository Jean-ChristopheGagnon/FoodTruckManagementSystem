<?php

include '../../autoload.php';

if (!isset($_GET['parentId'])) {
	header('location: ../staff/all.php?error='.urlencode('No menu specified'));
	exit();
}

$parentId = intval($_GET['parentId']);

$workShiftController = new WorkShiftController();

try {
	$workShifts = $workShiftController->getWorkShifts($parentId);
} catch (Exception $e) {
	header('location: ../staff/all.php?error='.urlencode($e->getMessage()));
	exit();
}

$staffController = new StaffController();

try {
	$staff = $staffController->getStaff($parentId);
} catch (Exception $e) {
	header('location: ../staff/all.php?error='.urlencode($e->getMessage()));
	exit();
}

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Workshifts - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Workshifts - Food Truck Management System</h1>
    <h2>Employee : <?= $staff->getName() ?></h2>
    <?php if (count($workShifts) == 0) { ?>
        <p>There are no workshift for this employee</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Food Truck</th>
			    <th>Date</th>
			    <th>Start Time</th>
			    <th>End Time</th>
			    <th></th>
			    <th></th>
	    </thead>
	    <tbody>
        <?php foreach ($workShifts as $id => $workShift){ ?>
            <tr>
                <td><?= $workShift->getFoodTruck()->getLocation() ?></td>
                <td><?= $workShift->getWorkDate() ?></td>
                <td><?= $workShift->getStartTime() ?></td>
                <td><?= $workShift->getEndTime() ?></td>
                <td><a href="edit.php?parentId=<?=$parentId?>&id=<?=$id?>">Edit</a></td>
                <td><a href="delete.php?parentId=<?=$parentId?>&id=<?=$id?>">Delete</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php?parentId=<?=$parentId?>">Create a workshift</a><br>
    <a href="../staff/all.php">Back</a>
</div></div></body>
</html>

