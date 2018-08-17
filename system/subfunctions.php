<?php

require_once "classes/template.php";

require_once "dao/subfunctionsDAO.php";
require_once "classes/subfunctions.php";

$object = new subfunctionsDAO();

$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_cod_subfunction = (isset($_POST["str_cod_subfunction"]) && $_POST["str_cod_subfunction"] != null) ? $_POST["str_cod_subfunction"] : "";
    $str_name_subfunction = (isset($_POST["str_name_subfunction"]) && $_POST["str_name_subfunction"] != null) ? $_POST["str_name_subfunction"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_cod_subfunction = NULL;
    $str_name_subfunction = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $subfunctions = new subfunctions($id, '', '');

    $resultado = $object->atualizar($subfunctions);
    $str_cod_subfunction = $resultado->getStrCodSubfunction();
    $str_name_subfunction = $resultado->getStrNameSubfunction();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_name_subfunction != "" && $str_cod_subfunction != "") {
    $subfunctions = new subfunctions($id, $str_cod_subfunction, $str_name_subfunction);

    $msg = $object->salvar($subfunctions);
    $id = null;
    $str_cod_subfunction = null;
    $str_name_subfunction = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $subfunctions = new subfunctions($id, '', '');

    $msg = $object->remover($subfunctions);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>Subfunctions</h4>
                        <p class='category'>List of system subfunctions</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Name:
                            <input class="form-control" type="text" name="str_name_subfunction" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_name_subfunction) && ($str_name_subfunction != null || $str_name_subfunction != "")) ? $str_name_subfunction : '';
                            ?>"/>
                            <br/>
                            Code:
                            <input class="form-control" type="text" maxlength="4" name="str_cod_subfunction" value="<?php
                            // Preenche o sigla no campo sigla com um valor "value"
                            echo (isset($str_cod_subfunction) && ($str_cod_subfunction != null || $str_cod_subfunction != "")) ? $str_cod_subfunction : '';
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
