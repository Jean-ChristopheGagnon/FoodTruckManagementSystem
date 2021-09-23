<?php
function foodTruckManagementSystemAutoload($class) {
	
	$folders = ['controller', 'model', 'persistence'];
	
	foreach ($folders as $folder) {
		$path = __DIR__. '/' . $folder.'/'.$class.'.php';
		if (file_exists($path)) {
			include $path;
		}
	}
	
}

spl_autoload_register('foodTruckManagementSystemAutoload');