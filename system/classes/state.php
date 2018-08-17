<?php

class state
{
    private $id_state;
    private $str_uf;
    private $str_name;
    private $tb_region_id_region;

    public function __construct($id_state, $str_uf, $str_name, $tb_region_id_region)
    {
        $this->id_state = $id_state;
        $this->str_uf = $str_uf;
        $this->str_name = $str_name;
        $this->tb_region_id_region = $tb_region_id_region;
    }

    public function getIdState()
    {
        return $this->id_state;
    }

    public function setIdState($id_state)
    {
        $this->id_state = $id_state;
    }

    public function getStrUf()
    {
        return $this->str_uf;
    }

    public function setStrUf($str_uf)
    {
        $this->str_uf = $str_uf;
    }

    public function getStrName()
    {
        return $this->str_name;
    }

    public function setStrName($str_name)
    {
        $this->str_name = $str_name;
    }

    public function getTbRegionIdRegion()
    {
        return $this->tb_region_id_region;
    }

    public function setTbRegionIdRegion($tb_region_id_region)
    {
        $this->tb_region_id_region = $tb_region_id_region;
    }

}