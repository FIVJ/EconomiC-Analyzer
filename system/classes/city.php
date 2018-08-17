<?php

class city
{
    private $id_city;
    private $str_name_city;
    private $str_cod_siafi_city;
    private $tb_state_id_state;

    public function __construct($id_city, $str_name_city, $str_cod_siafi_city, $tb_state_id_state)
    {
        $this->id_city = $id_city;
        $this->str_name_city = $str_name_city;
        $this->str_cod_siafi_city = $str_cod_siafi_city;
        $this->tb_state_id_state = $tb_state_id_state;
    }

    public function getIdCity()
    {
        return $this->id_city;
    }

    public function setIdCity($id_city)
    {
        $this->id_city = $id_city;
    }

    public function getStrNameCity()
    {
        return $this->str_name_city;
    }

    public function setStrNameCity($str_name_city)
    {
        $this->str_name_city = $str_name_city;
    }

    public function getStrCodSiafiCity()
    {
        return $this->str_cod_siafi_city;
    }

    public function setStrCodSiafiCity($str_cod_siafi_city)
    {
        $this->str_cod_siafi_city = $str_cod_siafi_city;
    }

    public function getTbStateIdState()
    {
        return $this->tb_state_id_state;
    }

    public function setTbStateIdState($tb_state_id_state)
    {
        $this->tb_state_id_state = $tb_state_id_state;
    }

}