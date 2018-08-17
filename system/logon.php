<?php

require_once "db/connection.php";

session_start();
$login = $_POST['login'];
$passwd = $_POST['password'];
$iduser = null;
$name = null;
$loginDB = null;
$pass = null; 
$profile = null;

try {
    $statement = $pdo->prepare("SELECT iduser, login, senha, nome, perfil FROM tb_user WHERE login = :login and senha = :pass; ");
    $statement->bindValue(":login", $login);
    $statement->bindValue(":pass", sha1($passwd));
    if ($statement->execute()) {
        $rs = $statement->fetch(PDO::FETCH_OBJ);
        
        $iduser = $rs->iduser;
        $loginDB = $rs->login;
        $name = $rs->nome;
        $pass = $rs->senha;
        $profile = $rs->perfil;
        
        if( $loginDB != null and $pass != null)
        {
            $_SESSION['iduser'] = $iduser;
            $_SESSION['login'] = $loginDB;
            $_SESSION['password'] = $pass;
            $_SESSION['name'] = $name;
            $_SESSION['perfil'] = $profile; 
            
            header('location:index.php');
        }
        else{
            unset ($_SESSION['iduser']);
            unset ($_SESSION['login']);
            unset ($_SESSION['password']);
            unset ($_SESSION['name']);
            unset ($_SESSION['perfil']);
            echo "<script> alert('Usuario ou pass incorretos !'); </script>";
            
            header('location:index.php');

        }
    } else {
        throw new PDOException("Erro: Não foi possível executar a declaração sql");
    }
} catch (PDOException $erro) {
    echo "Erro: ".$erro->getMessage();
}

?>