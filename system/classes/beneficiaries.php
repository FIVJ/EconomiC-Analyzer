<?php

class beneficiaries
{
    private $id_beneficiaries;
    private $str_nis;
    private $str_name_person;

    public function __construct($id_beneficiaries, $str_nis, $str_name_person)
    {
        $this->id_beneficiaries = $id_beneficiaries;
        $this->str_nis = $str_nis;
        $this->str_name_person = $str_name_person;
    }

    public function getIdBeneficiaries()
    {
        return $this->id_beneficiaries;
    }
    public function setIdBeneficiaries($id_beneficiaries)
    {
        $this->id_beneficiaries = $id_beneficiaries;
    }

    public function getStrNis()
    {
        return $this->str_nis;
    }
    public function setStrNis($str_nis)
    {
        $this->str_nis = $str_nis;
    }


    public function getStrNamePerson()
    {
        return $this->str_name_person;
    }

    public function setStrNamePerson($str_name_person)
    {
        $this->str_name_person = $str_name_person;
    }

}