<?php

require_once "classes/template.php";

require_once "dao/beneficiariesDAO.php";
require_once "classes/beneficiaries.php";


$object = new beneficiariesDAO();



$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_nis = (isset($_POST["str_nis"]) && $_POST["str_nis"] != null) ? $_POST["str_nis"] : "";
    $str_name_person = (isset($_POST["str_name_person"]) && $_POST["str_name_person"] != null) ? $_POST["str_name_person"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_nis = NULL;
    $str_name_person = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $beneficiaries = new beneficiaries($id, '', '');

    $resultado = $object->atualizar($beneficiaries);
    $str_nis = $resultado->getStrNis();
    $str_name_person = $resultado->getStrNamePerson();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_name_person != "" && $str_nis!= "") {
    $beneficiaries = new beneficiaries($id, $str_nis, $str_name_person);
    $msg = $object->salvar($beneficiaries);
    $id = null;
    $str_nis = null;
    $str_name_person = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $beneficiaries = new beneficiaries($id, '', '');
    $msg = $object->remover($beneficiaries);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>Beneficiaries</h4>
                        <p class='category'>List of beneficiaries of the system</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Name:
                            <input class="form-control" type="text" name="str_name_person" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_name_person) && ($str_name_person != null || $str_name_person != "")) ? $str_name_person : '';
                            ?>"/>
                            <br/>
                            NIS:
                            <input class="form-control" type="text" maxlength="11" name="str_nis" placeholder="Enter numbers only" value="<?php
                            // Preenche o sigla no campo sigla com um valor "value"
                            echo (isset($str_nis) && ($str_nis != null || $str_nis != "")) ? $str_nis : '';
                            ?>"/>
                            <br/>
                            <input class="btn btn-success" type="submit" value="REGISTER">
                            <hr>
                        </form>


                        <?php
                        echo (isset($msg) && ($msg != null || $msg != "")) ? $msg : '';
                        //chamada a paginação
                        $object->tabelapaginada();

                        ?>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<?php
$template->footer();
?>
