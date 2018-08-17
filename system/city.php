<?php

require_once "classes/template.php";

require_once "dao/cityDAO.php";
require_once "classes/city.php";

$object = new cityDAO();

$template = new Template();

$template->header();
$template->sidebar();
$template->mainpanel();


// Verificar se foi enviando dados via POST
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = (isset($_POST["id"]) && $_POST["id"] != null) ? $_POST["id"] : "";
    $str_name_city = (isset($_POST["str_name_city"]) && $_POST["str_name_city"] != null) ? $_POST["str_name_city"] : "";
    $str_cod_siafi_city = (isset($_POST["str_cod_siafi_city"]) && $_POST["str_cod_siafi_city"] != null) ? $_POST["str_cod_siafi_city"] : "";
    $tb_state_id_state = (isset($_POST["tb_state_id_state"]) && $_POST["tb_state_id_state"] != null) ? $_POST["tb_state_id_state"] : "";
} else if (!isset($id)) {
    // Se não se não foi setado nenhum valor para variável $id
    $id = (isset($_GET["id"]) && $_GET["id"] != null) ? $_GET["id"] : "";
    $str_name_city = NULL;
    $str_cod_siafi_city = NULL;
    $tb_state_id_state = NULL;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "upd" && $id != "") {

    $city = new city($id, '', '', '');

    $resultado = $object->atualizar($city);
    $str_name_city = $resultado->getStrNameCity();
    $str_cod_siafi_city = $resultado->getStrCodSiafiCity();
    $tb_state_id_state = $resultado->getTbStateIdState();

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "save" && $str_name_city != "" && $str_cod_siafi_city!= "") {
    $city = new city($id, $str_name_city, $str_cod_siafi_city, $tb_state_id_state);
    $msg = $object->salvar($city);
    $id = null;
    $str_name_city = null;
    $str_cod_siafi_city = null;
    $tb_state_id_state = null;

}

if (isset($_REQUEST["act"]) && $_REQUEST["act"] == "del" && $id != "") {
    $city = new city($id, '', '', '');
    $msg = $object->remover($city);
    $id = null;
}

?>

<div class='content' xmlns="http://www.w3.org/1999/html">
    <div class='container-fluid'>
        <div class='row'>
            <div class='col-md-12'>
                <div class='card'>
                    <div class='header'>
                        <h4 class='title'>City</h4>
                        <p class='category'>List of cities of the system</p>

                    </div>
                    <div class='content table-responsive'>

                        <form action="?act=save&id=" method="POST" name="form1">

                            <input type="hidden" name="id" value="<?php
                            // Preenche o id no campo id com um valor "value"
                            echo (isset($id) && ($id != null || $id != "")) ? $id : '';
                            ?>"/>
                            Name:
                            <input class="form-control" type="text" name="str_name_city" value="<?php
                            // Preenche o nome no campo nome com um valor "value"
                            echo (isset($str_name_city) && ($str_name_city != null || $str_name_city != "")) ? $str_name_city : '';
                            ?>"/>
                            <br/>
                            City Code:
                            <input class="form-control" type="text" maxlength="4" name="str_cod_siafi_city" value="<?php
                            // Preenche o sigla no campo sigla com um valor "value"
                            echo (isset($str_cod_siafi_city) && ($str_cod_siafi_city != null || $str_cod_siafi_city != "")) ? $str_cod_siafi_city : '';
                            ?>"/>
                            <br/>
                            State:
                            <select class="form-control" name="tb_state_id_state">
                                <?php
                                $query = "SELECT * FROM tb_state order by str_name;";
                                $statement = $pdo->prepare($query);
                                if ($statement->execute()) {
                                    $result = $statement->fetchAll(PDO::FETCH_OBJ);
                                    foreach ($result as $rs) {
                                        if ($rs->id_state == $tb_state_id_state) {
                                            echo "<option value='$rs->id_state' selected>$rs->str_name</option>";
                                        } else {
                                            echo "<option value='$rs->id_state'>$rs->str_name</option>";
                                        }
                                    }
                                } else {
                                    throw new PDOException("<script> alert('Não foi possível executar a declaração SQL !'); </script>");
                                }
                                ?>
                            </select>
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
