<?php

class region
{
    private $id_region;
    private $str_name_region;

    public function __construct($id_region, $str_name_region)
    {
        $this->id_region = $id_region;
        $this->str_name_region = $str_name_region;
    }

    public function getIdRegion()
    {
        return $this->id_region;
    }

    public function setIdRegion($id_region)
    {
        $this->id_region = $id_region;
    }

    public function getStrNameRegion()
    {
        return $this->str_name_region;
    }

    public function setStrNameRegion($str_name_region)
    {
        $this->str_name_region = $str_name_region;
    }

}