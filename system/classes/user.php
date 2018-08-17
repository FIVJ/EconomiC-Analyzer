<?php

class user
{

    private $idUsuario;
    private $Login;
    private $Senha;
    private $Nome;
    private $Email;
    private $Resetar;
    private $Perfil;
    
    function __construct($idUsuario, $Login, $Senha, $Nome, $Email, $Resetar, $Perfil) {
        $this->idUsuario = $idUsuario;
        $this->Login = $Login;
        $this->Senha = $Senha;
        $this->Nome = $Nome;
        $this->Email = $Email;
        $this->Resetar = $Resetar;
        $this->Perfil = $Perfil;
    }

    function getIdUsuario() {
        return $this->idUsuario;
    }

    function getLogin() {
        return $this->Login;
    }

    function getSenha() {
        return $this->Senha;
    }

    function getNome() {
        return $this->Nome;
    }

    function getEmail() {
        return $this->Email;
    }

    function getResetar() {
        return $this->Resetar;
    }

    function getPerfil() {
        return $this->Perfil;
    }

    function setIdUsuario($idUsuario) {
        $this->idUsuario = $idUsuario;
    }

    function setLogin($Login) {
        $this->Login = $Login;
    }

    function setSenha($Senha) {
        $this->Senha = $Senha;
    }

    function setNome($Nome) {
        $this->Nome = $Nome;
    }

    function setEmail($Email) {
        $this->Email = $Email;
    }

    function setResetar($Resetar) {
        $this->Resetar = $Resetar;
    }

    function setPerfil($Perfil) {
        $this->Perfil = $Perfil;
    }

}