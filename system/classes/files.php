<?php

class files
{
    private $id_file;
    private $str_name_file;
    private $str_month;
    private $str_year;

    public function __construct($id_file, $str_name_file, $str_month, $str_year)
    {
        $this->id_file = $id_file;
        $this->str_name_file = $str_name_file;
        $this->str_month = $str_month;
        $this->str_year = $str_year;
    }

    public function getIdFile()
    {
        return $this->id_file;
    }

    public function setIdFile($id_file)
    {
        $this->id_file = $id_file;
    }

    public function getStrNameFile()
    {
        return $this->str_name_file;
    }

    public function setStrNameFile($str_name_file)
    {
        $this->str_name_file = $str_name_file;
    }

    public function getStrMonth()
    {
        return $this->str_month;
    }

    public function setStrMonth($str_month)
    {
        $this->str_month = $str_month;
    }

    public function getStrYear()
    {
        return $this->str_year;
    }

    public function setStrYear($str_year)
    {
        $this->str_year = $str_year;
    }

}