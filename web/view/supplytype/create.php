<?php

include '../../autoload.php';
require_once '../lib/formr/class.formr.php';

$form = new Formr('bootstrap');

$form->required = '*';

include '../include/error.php';

?>

<!DOCTYPE html>
<html>
<head>
    <title>Create a supply type - Food Truck Management System</title>
    <?php include '../include/header.php';?>
</head>
<body><div class="container"><div class="col-xs-12">
    <h1>Create a supply type - Food Truck Management System</h1>
    <?php
    echo $form->form_open('','','create_submit.php');
    echo $form->input_text('name', 'Name:');
    echo $form->input_submit();
    echo $form->form_close();
    ?>
    <a href="all.php">Back</a>
</div></div></body>
</html>
