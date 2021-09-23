<?php

class CRUDController {
	
	/** An instance of the PersistenceFoodTruckManager */
	private $persistence;
	/** An instance of the FoodTruckManagementSystem */
	private $manager;
	
	private $className;
	private $classReadableName;
	private $parentClassName;
	private $parentClassReadableName;
	private $grandParentClassName;
	private $grandParentClassReadableName;
	
	/**
	 * The class constructor to be used by the child controllers.
	 * 
	 * @param string $className the name of the model class in lowercase.
	 * @param string $classReadableName the name of the model that will be used in the exception message.
	 * @param string $parentClassName the name of the model's parent class in lowercase. If null, it is the FoodTruckManagementSystem.
	 * @param string $parentClassReadableName the name of the model's parent class that will be used in the exception message. If null, it is the FoodTruckManagementSystem.
	 * @param string $parentClassName the name of the model's parent's parent class in lowercase. If null, it is the FoodTruckManagementSystem.
	 * @param string $parentClassReadableName the name of the model's parent's parent class that will be used in the exception message. If null, it is the FoodTruckManagementSystem.
	 */
	public function __construct($className, $classReadableName, $parentClassName = null, $parentClassReadableName = null, $grandParentClassName = null, $grandParentClassReadableName = null) {
		
		$this->className = $className;
		$this->classReadableName = $classReadableName;
		$this->parentClassName = $parentClassName;
		$this->parentClassReadableName = $parentClassReadableName;
		$this->grandParentClassName = $grandParentClassName;
		$this->grandParentClassReadableName = $grandParentClassReadableName;
		
	}
	
	/**
	 * Create an element.
	 * 
	 * @param mixed $element the element to create (a model instance).
	 * @param int $parentId the id of the containing object (if null, it is the FoodTruckManagementSystem).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 */
	public function create($element, $parentId = null, $grandParentId = null) {
		
		$this->loadData();
		
		$functionName = 'add'.$this->className;
		
		$parentObj = $this->getParent($parentId, $grandParentId);
		
		$parentObj->$functionName($element);
		
		$this->saveData();
		
	}
	
	/**
	 * Get an element based on his id.
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @param int $id the id of the element.
	 * @param int $parentId the id of the containing object (if null, it is the FoodTruckManagementSystem).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 * 
	 * @return mixed the object associated with the id.
	 */
	public function read($id, $parentId = null, $grandParentId = null) {
		
		$this->loadData();
		
		$this->checkElementExist($id, $parentId, $grandParentId);
		
		$functionName = 'get'.$this->className.'_index';
		
		$parentObj = $this->getParent($parentId, $grandParentId);
		
		return $parentObj->$functionName($id);
		
	}
	
	/**
	 * Get all the existing objects.
	 * 
	 * @param int $parentId the id of the containing object (if null, it is the FoodTruckManagementSystem).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 * 
	 * @throws Exception contains an error message that can be directly presented to the user.
	 */
	public function readAll($parentId = null, $grandParentId = null) {
		
		$this->loadData();
		
		$functionName = 'get'.$this->className;
		
		$parentObj = $this->getParent($parentId, $grandParentId);
		
		// If this method name does not exist, it means that it needs to be pluralized
		if (!method_exists($parentObj, $functionName)) {
			$functionName .= 's';
			if (!method_exists($parentObj, $functionName)) {
				$functionName = substr($functionName, 0, strlen($functionName)-2) . 'ies';
			}
		}
		
		return $parentObj->$functionName();
		
	}
	
	/**
	 * Modify the property of an object.
	 * 
	 * @param string $propertyName the name of the property in lowercase.
	 * @param mixed $value the new value to set the property to.
	 * @param int $elementId the id of the object to modify.
	 * @param int $parentId the id of the containing object (if null, it is the FoodTruckManagementSystem).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 */
	public function modify($propertyName, $value, $elementId, $parentId = null, $grandParentId = null) {
		
		$this->loadData();
		
		$this->checkElementExist($elementId, $parentId, $grandParentId);
		
		$functionName = 'get'.$this->className.'_index';
		
		$parentObj = $this->getParent($parentId, $grandParentId);
		
		$object = $parentObj->$functionName($elementId);
		
		// If the value is an array, it has to be treated differently.
		if (is_array($value)) {
			$propertyName = ucfirst($propertyName);
			$propertyNameSingular = substr($propertyName, 0, strlen($propertyName)-1);
			$hasFunctionName = 'has'.$propertyName;
			$removeFunctionName = 'remove'.$propertyNameSingular;
			$getFunctionName = 'get'.$propertyNameSingular.'_index';
			while ($object->$hasFunctionName()) {
				$element = $object->$getFunctionName(0);
				$object->$removeFunctionName($element);
			}
			$object->delete();
			$addFunctionName = 'add'.$propertyNameSingular;
			foreach ($value as $element) {
				$object->$addFunctionName($element);
			}
			$this->saveData();
			return;
		}
		
		$functionName = 'set' . ucfirst($propertyName);
		
		$object->$functionName($value);
		
		$this->saveData();
		
	}
	
	/**
	 * Deletes an object.
	 * 
	 * @param unknown $id the id of the object to delete.
	 * @param unknown $parentId the id of the containing object (if null, it is the FoodTruckManagementSystem).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 */
	public function delete($id, $parentId = null, $grandParentId = null) {
		
		$this->loadData();
		
		$element = $this->read($id, $parentId, $grandParentId);
		
		$parentObj = $this->getParent($parentId, $grandParentId);
		
		$functionName = 'remove'.$this->className;
		
		$parentObj->$functionName($element);
		
		$this->saveData();
		
	}
	
	/**
	 * Get the id associated with an object.
	 * 
	 * @param mixed $element the object.
	 * @param int $parentId the id of the containing object (if null, it is the FoodTruckManagementSystem).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 */
	public function getId($element, $parentId = null, $grandParentId = null) {
		
		$this->loadData();
		
		$parentObj = $this->getParent($parentId, $grandParentId);
		
		$functionName = 'indexOf'.$this->className;
		
		$id = $parentObj->$functionName($element);
		
		if ($id === -1) {
			throw new Exception("The element does not exist");
		}
		
		return $id;
		
	}
	
	/**
	 * Clean and validates an input to be used as a object property. Throws an exception if the data is not valid.
	 * 
	 * This function is to be used by child controllers to validate the user inputs.
	 * 
	 * @param mixed $value the user input.
	 * @param string $name the name of the element's property associated with this value.
	 * @param string $type the type of the property. The check and validation depends on the type (supported types : 'int', 'double', 'string', 'date', 'time').
	 * @throws Exception thrown when the input is not valid (depends on the property type).
	 * @return mixed the cleaned value.
	 */
	public function validateAndCleanProperty($value, $name, $type) {
	
		$value = Helpers::cleanInputString($value);
	
		$exceptionBaseMessage = "The " . $this->classReadableName . " " . $name;
	
		if ($value === null || $value === "") {
			if (!($type === "boolean" || $type === "bool")) {
				throw new Exception($exceptionBaseMessage . " cannot be empty");
			}
			$value = false;
		}
	
		switch ($type) {
			case 'int':
			case 'integer':
				if (!ctype_digit($value)) {
					throw new Exception($exceptionBaseMessage . " needs to be a valid integer");
				}
				// Throught the app, all the numbers to be treated are positives
				if ($value < 0) {
					throw new Exception($exceptionBaseMessage . " needs to be a positive integer");
				}
				break;
			case 'double':
			case 'float':
				if (!is_numeric($value)) {
					throw new Exception($exceptionBaseMessage . " needs to be a valid decimal number");
				}
				// Throught the app, all the numbers to be treated are positives
				if ($value < 0) {
					throw new Exception($exceptionBaseMessage . " needs to be a positive number");
				}
				break;
			case 'boolean':
			case 'bool':
				if ($value !== true && $value !== false && $value != 1 && $value != 0) {
					throw new Exception($exceptionBaseMessage . " needs to be true or false");
				}
				break;
			case 'date':
				$value = strval($value);
				$d = DateTime::createFromFormat('Y-m-d', $value);
				if (!$d || $d->format('Y-m-d') !== $value) {
					throw new Exception($exceptionBaseMessage . " must be a valid date in format YYYY-MM-DD");
				}
				break;
			case 'time':
				$dateObj = DateTime::createFromFormat('d.m.Y H:i', "01.01.2001 " . $value);
				// DateTime accepts both 00:00 and 24:00. We removed 24:00 for consistency.
				if ($dateObj === false || $value === "24:00") {
					throw new Exception($exceptionBaseMessage . " must be a valid time in 24-hour format HH:MM");
				}
				break;
		}
	
		return $value;
	
	}
	
	/**
	 * Throws an exception if there is no element associated with the id. Returns void otherwise.
	 * 
	 * @param integer $id the id of the element.
	 * @param integer $parentId the id of the parent element (null if the parent is the manager).
	 * @param int $grandParentId the id of the object containing the parent object (if null, it is the FoodTruckManagementSystem).
	 * 
	 * @throws Exception if there is no element (or parent object) associated with the id.
	 */
	private function checkElementExist($id, $parentId = null, $grandParentId = null) {
	
		$parentObj = $this->getParent($parentId, $grandParentId);
	
		// Getting the number of element in the manager/parent
		$countFunctionName = 'numberOf'.$this->className;
		// If this method name does not exist, it means that it needs to bu pluralized
		if (!method_exists($parentObj, $countFunctionName)) {
			$countFunctionName .= 's';
			if (!method_exists($parentObj, $countFunctionName)) {
				$countFunctionName = substr($countFunctionName, 0, strlen($countFunctionName)-2) . 'ies';
			}
		}
		$count = $parentObj->$countFunctionName();
	
		// Creating the message for the potential exception
		$exceptionMessage = "There is no " . $this->classReadableName . " associated with id " .  $id;
		// Add a description of the parent object if there is one
		if ($parentId !== null) {
			$exceptionMessage .= " in " . $this->parentClassReadableName . " with id " . $parentId;
		}
		// Add a description of the grand parent object if there is one
		if ($grandParentId !== null) {
			$exceptionMessage .= " in " . $this->grandParentClassReadableName . " with id " . $grandParentId;
		}
	
		// Throw an exception if there is no element associated with the given id
		if ($id >= $count || $id < 0) {
			throw new Exception($exceptionMessage);
		}
	
	}
	
	/**
	 * Returns the parent object associated with the id.
	 * 
	 * @param integer $parentId the id of the parent object (null for the manager).
	 * @throws Exception if there is not parent object associated with this id.
	 * @return Object the parent object associated with the id, or the manager object.
	 */
	private function getParent($parentId, $grandParentId) {
		
		// If there is no parent, return the manager
		if ($parentId === null) {
			return $this->manager;
		}
		
		$grandParentObject = $this->getGrandParent($grandParentId);
		
		// Checking if a parent object is associated with this id
		$countFunctionName = 'numberOf'.$this->parentClassName;
		// If this method name does not exist, it means that it needs to bu pluralized
		if (!method_exists($grandParentObject, $countFunctionName)) {
			$countFunctionName .= 's';
			if (!method_exists($grandParentObject, $countFunctionName)) {
				$countFunctionName = substr($countFunctionName, 0, strlen($countFunctionName)-2) . 'ies';
			}
		}
		$count = $grandParentObject->$countFunctionName();
		if ($parentId >= $count || $parentId < 0) {
			// Throws an exception if there is no parent associated
			throw new Exception("The associated " . $this->parentClassReadableName . " does not exist");
		}
		
		$functionName = 'get'.$this->parentClassName.'_index';
		return $grandParentObject->$functionName($parentId);
		
	}
	
	private function getGrandParent($grandParentId) {
		
		// If there is no grand parent, return the manager
		if ($grandParentId === null) {
			return $this->manager;
		}
		
		// Checking if a grand parent object is associated with this id
		$countFunctionName = 'numberOf'.$this->grandParentClassName;
		// If this method name does not exist, it means that it needs to bu pluralized
		if (!method_exists($this->manager, $countFunctionName)) {
			$countFunctionName .= 's';
			if (!method_exists($this->manager, $countFunctionName)) {
				$countFunctionName = substr($countFunctionName, 0, strlen($countFunctionName)-2) . 'ies';
			}
		}
		$count = $this->manager->$countFunctionName();
		if ($grandParentId >= $count || $grandParentId < 0) {
			// Throws an exception if there is no grand parent parent associated
			throw new Exception("The associated " . $this->grandParentClassReadableName . " does not exist");
		}
		
		$functionName = 'get'.$this->grandParentClassName.'_index';
		return $this->manager->$functionName($grandParentId);
	}
	
	/**
	 * Load the data from the persistence.
	 */
	private function loadData() {
	
		$this->persistence = new PersistenceFoodTruckManager();
		$this->manager = $this->persistence->load();
	
	}
	
	/**
	 * Store the data to the persistence.
	 */
	private function saveData() {
		
		$this->persistence->store($this->manager);
		
	}
	
}