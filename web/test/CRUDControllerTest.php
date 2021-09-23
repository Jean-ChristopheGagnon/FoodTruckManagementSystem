<?php

require_once __DIR__.'/../autoload.php';

class CRUDControllerTest extends PHPUnit_Framework_TestCase {

    protected $controller;
    protected $persistence;
    protected $manager;

    protected function setUp() {
        $this->controller = new CRUDController('','');
        $this->persistence = new PersistenceFoodTruckManager();
        $this->manager = $this->persistence->load();
        $this->manager->delete();
        $this->persistence->store($this->manager);
    }
    
    protected tearDown() {
	    // To be sure nothing is remaining from the tests
	    $this->manager->delete();
        $this->persistence->store($this->manager);
    }
    
    public function testInputValidation() {
	    
	    // All the type of validation to test
	    $types = ['int', 'double', 'string', 'date', 'time'];
	    
	    // The values to validate (per type)
	    $values = [['10', '-10', '10.1', 'aaa', null, '   '],
	               ['10', '-10', '10.1', '-10.9', '10.a', 'aa', null, '   '],
	               ['teststring', null, '    '],
	               ['2011-01-01', '2011-01-23', '2011-23-01', '2013/01/01', '1-1-1', null, '   '],
	               ['10:23', '23:59', '24:00', '00:00', 'aa:10', null, '    ']];
	    
	    // Tells whether a validation test case should raise an exception or not
	    $expectException = [[false, true, true, true, true, true],
	                        [false, true, false, true, true, true, true, true],
	                        [false, true, true],
	                        [false, false, true, true, true, true, true],
	                        [false, false, true, false, true, true, true]];
	    
	    // Random value for the property name. Does not need to be tested since it is only for the exception message.
	    $name = "randomname";
    	
    	// Run every test case
    	foreach ($types as $i => $type) {
	    	for ($j = 0; $j < count($values[$i]); $j++){
		    	try {
			    	$this->controller->validateAndCleanProperty($values[$i][$j], $name, $type);
		    	} catch (Exception $e) {
			    	if (!$expectException[$i][$j]) {
				    	$this->fail('Raised an exception on a valid value. The value: ['.$values[$i][$j].']. The type to test: ['.$type.']');
			    	}
			    	continue;
		    	}
		    	if ($expectException[$i][$j]) {
			    	$this->fail('Raised no exception on an valid value. The value: ['.$values[$i][$j].']. The type to test: ['.$type.']');
		    	}
	    	}
    	}
    	
    }

}
