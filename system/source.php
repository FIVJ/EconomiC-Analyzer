<?php

require_once "classes/template.php";

require_once "dao/sourceDAO.php";
require_once "classes/source.php";


$object = new sourceDAO();

$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_goal = (isset($_POST["str_goal"]) && $_POST["str_goal"] != null) ? $_POST["str_goal"] : "";
    $str_origin = (isset($_POST["str_origin"]) && $_POST["str_origin"] != null) ? $_POST["str_origin"] : "";
    $str_periodicity = (isset($_POST["str_periodicity"]) && $_POST["str_periodicity"] != null) ? $_POST["str_periodicity"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_goal = NULL;
    $str_origin = NULL;
    $str_periodicity = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $source = new source($id, '', '', '');

    $resultado = $object->atualizar($source);
    $str_goal = $resultado->getStrGoal();
    $str_origin = $resultado->getStrOrigin();
    $str_periodicity = $resultado->getStrPeriodicity();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_goal != "" && $str_origin!= "") {
    $source = new source($id, $str_goal, $str_origin, $str_periodicity);
    $msg = $object->salvar($source);
    $id = null;
    $str_goal = null;
    $str_origin = null;
    $str_periodicity = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $source = new source($id, '', '', '');
    $msg = $object->remover($source);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>Source</h4>
                        <p class='category'>List of system fonts</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Goal:
                            <input class="form-control" type="text" name="str_goal" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_goal) && ($str_goal != null || $str_goal != "")) ? $str_goal : '';
                            ?>"/>
                            <br/>
                            Origination:
                            <input class="form-control" type="text" name="str_origin" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_origin) && ($str_origin != null || $str_origin != "")) ? $str_origin : '';
                            ?>"/>
                            <br/>
                            Periodicity:
                            <input class="form-control" type="text" maxlength="9" name="str_periodicity" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_periodicity) && ($str_periodicity != null || $str_periodicity != "")) ? $str_periodicity : '';
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
