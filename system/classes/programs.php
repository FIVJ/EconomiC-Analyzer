<?php

class programs
{
    private $id_program;
    private $str_cod_program;
    private $str_name_program;

    public function __construct($id_program, $str_cod_program, $str_name_program)
    {
        $this->id_program = $id_program;
        $this->str_cod_program = $str_cod_program;
        $this->str_name_program = $str_name_program;
    }

    public function getIdProgram()
    {
        return $this->id_program;
    }

    public function setIdProgram($id_program)
    {
        $this->id_program = $id_program;
    }

    public function getStrCodProgram()
    {
        return $this->str_cod_program;
    }

    public function setStrCodProgram($str_cod_program)
    {
        $this->str_cod_program = $str_cod_program;
    }

    public function getStrNameProgram()
    {
        return $this->str_name_program;
    }

    public function setStrNameProgram($str_name_program)
    {
        $this->str_name_program = $str_name_program;
    }

}