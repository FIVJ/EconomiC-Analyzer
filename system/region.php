<?php

require_once "classes/template.php";

require_once "dao/regionDAO.php";
require_once "classes/region.php";


$object = new regionDAO();



$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_name_region = (isset($_POST["str_name_region"]) && $_POST["str_name_region"] != null) ? $_POST["str_name_region"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_name_region = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $region = new region($id, '');

    $resultado = $object->atualizar($region);
    $str_nis = $resultado->getStrNis();
    $str_name_person = $resultado->getStrNamePerson();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_name_region!= "") {
    $region = new region($id, $str_name_region);
    $msg = $object->salvar($region);
    $id = null;
    $str_name_region = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $region = new region($id, '');
    $msg = $object->remover($region);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>Region</h4>
                        <p class='category'>List of regions of system</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Name:
                            <input class="form-control" type="text" size="50" name=" str_name_region" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_name_region) && ($str_name_region != null || $str_name_region != "")) ? $str_name_region : '';
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
