<?php
/*
    NOTICE : this is a modified version of PersistenceEventRegistration.php
    given in the assignment 1 of ECSE 321.
*/
class PersistenceFoodTruckManager {

	private $filename;

	function __construct($filename = __DIR__.'/data.txt') {
		$this->filename = $filename;
	}

	function load() {
		if (file_exists($this->filename)) {
			$str = file_get_contents($this->filename);
			$ftm = unserialize($str);
		} else {
			$ftm = FoodTruckManagementSystem::getInstance();
		}
		return $ftm;
	}

	function store($ftm) {
		$str = serialize($ftm);
		file_put_contents($this->filename, $str);
	}
	
	function getFileName() {
		return $this->filename;
	}
}
