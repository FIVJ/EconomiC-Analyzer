<?php

class action
{
    private $id_action;
    private $str_cod_action;
    private $str_name_action;

    public function __construct($id_action, $str_cod_action, $str_name_action)
    {
        $this->id_action = $id_action;
        $this->str_cod_action = $str_cod_action;
        $this->str_name_action = $str_name_action;
    }

    public function getIdAction()
    {
        return $this->id_action;
    }
    public function setIdAction($id_action)
    {
        $this->id_action = $id_action;
    }

    public function getStrCodAction()
    {
        return $this->str_cod_action;
    }
    public function setStrCodAction($str_cod_action)
    {
        $this->str_cod_action = $str_cod_action;
    }

    public function getStrNameAction()
    {
        return $this->str_name_action;
    }
    public function setStrNameAction($str_name_action)
    {
        $this->str_name_action = $str_name_action;
    }

}