<?php

require_once "classes/template.php";

require_once "dao/programsDAO.php";
require_once "classes/programs.php";


$object = new programsDAO();



$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_cod_program = (isset($_POST["str_cod_program"]) && $_POST["str_cod_program"] != null) ? $_POST["str_cod_program"] : "";
    $str_name_program = (isset($_POST["str_name_program"]) && $_POST["str_name_program"] != null) ? $_POST["str_name_program"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_cod_program = NULL;
    $str_name_program = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $programs = new programs($id, '', '');

    $resultado = $object->atualizar($programs);
    $str_cod_program = $resultado->getStrCodProgram();
    $str_name_program = $resultado->getStrNameProgram();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_name_program != "" && $str_cod_program!= "") {
    $programs = new programs($id, $str_cod_program, $str_name_program);
    $msg = $object->salvar($programs);
    $id = null;
    $str_cod_program = null;
    $str_name_program = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $programs = new programs($id, '', '');
    $msg = $object->remover($programs);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>Program</h4>
                        <p class='category'>List of programs of system</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Name:
                            <input class="form-control" type="text" name="str_name_program" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_name_program) && ($str_name_program != null || $str_name_program != "")) ? $str_name_program : '';
                            ?>"/>
                            <br/>
                            Program Code:
                            <input class="form-control" type="text" name="str_cod_program" value="<?php
                            // Preenche o sigla no campo sigla com um valor "value"
                            echo (isset($str_cod_program) && ($str_cod_program != null || $str_cod_program != "")) ? $str_cod_program : '';
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
