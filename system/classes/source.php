<?php

class source
{
    private $id_source;
    private $str_goal;
    private $str_origin;
    private $str_periodicity;

    public function __construct($id_source, $str_goal, $str_origin, $str_periodicity)
    {
        $this->id_source = $id_source;
        $this->str_goal = $str_goal;
        $this->str_origin = $str_origin;
        $this->str_periodicity = $str_periodicity;
    }

    public function getIdSource()
    {
        return $this->id_source;
    }

    public function setIdSource($id_source)
    {
        $this->id_source = $id_source;
    }

    public function getStrGoal()
    {
        return $this->str_goal;
    }

    public function setStrGoal($str_goal)
    {
        $this->str_goal = $str_goal;
    }

    public function getStrOrigin()
    {
        return $this->str_origin;
    }

    public function setStrOrigin($str_origin)
    {
        $this->str_origin = $str_origin;
    }

    public function getStrPeriodicity()
    {
        return $this->str_periodicity;
    }

    public function setStrPeriodicity($str_periodicity)
    {
        $this->str_periodicity = $str_periodicity;
    }

}