<?php

require_once "classes/template.php";

require_once "dao/functionsDAO.php";
require_once "classes/functions.php";

$object = new functionsDAO();

$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_cod_function = (isset($_POST["str_cod_function"]) && $_POST["str_cod_function"] != null) ? $_POST["str_cod_function"] : "";
    $str_name_function = (isset($_POST["str_name_function"]) && $_POST["str_name_function"] != null) ? $_POST["str_name_function"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_cod_function = NULL;
    $str_name_function = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $functions = new functions($id, '', '');

    $resultado = $object->atualizar($functions);
    $str_cod_function = $resultado->getStrCodFunction();
    $str_name_function = $resultado->getStrNameFunction();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_name_function != "" && $str_cod_function != "") {
    $functions = new functions($id, $str_cod_function, $str_name_function);

    $msg = $object->salvar($functions);
    $id = null;
    $str_cod_function = null;
    $str_name_function = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $functions = new functions($id, '', '');

    $msg = $object->remover($functions);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>Function</h4>
                        <p class='category'>List of system functions</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Name:
                            <input class="form-control" type="text" name="str_name_function" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_name_function) && ($str_name_function != null || $str_name_function != "")) ? $str_name_function : '';
                            ?>"/>
                            <br/>
                            Function Code:
                            <input class="form-control" type="text" maxlength="4" name="str_cod_function" value="<?php
                            // Preenche o sigla no campo sigla com um valor "value"
                            echo (isset($str_cod_function) && ($str_cod_function != null || $str_cod_function != "")) ? $str_cod_function : '';
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
