<?php

class functions
{
    private $id_function;
    private $str_cod_function;
    private $str_name_function;

    public function __construct($id_function, $str_cod_function, $str_name_function)
    {
        $this->id_function = $id_function;
        $this->str_cod_function = $str_cod_function;
        $this->str_name_function = $str_name_function;
    }

    public function getIdFunction()
    {
        return $this->id_function;
    }

    public function setIdFunction($id_function)
    {
        $this->id_function = $id_function;
    }

    public function getStrCodFunction()
    {
        return $this->str_cod_function;
    }

    public function setStrCodFunction($str_cod_function)
    {
        $this->str_cod_function = $str_cod_function;
    }

    public function getStrNameFunction()
    {
        return $this->str_name_function;
    }

    public function setStrNameFunction($str_name_function)
    {
        $this->str_name_function = $str_name_function;
    }

}