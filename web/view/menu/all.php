<?php
include '../../autoload.php';

$menuController = new MenuController();

$menus = $menuController->getMenus();

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Menus - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Menus - Food Truck Management System</h1>
    <?php if (count($menus) == 0) { ?>
        <p>There are no menu yet</p>
    <?php } ?>
    <table class="table">
	    <thead>
		    <tr>
			    <th>Name</th>
			    <th></th>
			    <th></th>
			    <th></th>
		    </tr>
	    </thead>
	    <tbody>
        <?php foreach ($menus as $menuId => $menu){ ?>
            <tr>
                <td><?= $menu->getName() ?></td>
                <td><a href="edit.php?id=<?=$menuId?>">Edit</a></td>
                <td><a href="../menuitem/all.php?parentId=<?=$menuId?>">See menu items</a></td>
                <td><a href="delete.php?id=<?=$menuId?>">Delete Menu</a></td>
            </tr>
        <?php } ?>
	    </tbody>
    </table>
    <a href="create.php">Create a menu</a>
    <br>
    <a href="/index.php">Main Menu</a>
</div></div></body>
</html>
