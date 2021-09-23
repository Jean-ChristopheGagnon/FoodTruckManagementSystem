<?php

abstract class Helpers {

    /**
     * This function cleans an input string.
     * Prevents XSS and trims the input.
     *
     * @param string $input The string to clean
     * @return string
    */
    public static function cleanInputString($input) {
        return htmlspecialchars(stripslashes(trim($input)));
    }

}
