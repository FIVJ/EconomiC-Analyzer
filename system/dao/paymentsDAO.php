<?php

require_once "db/connection.php";
require_once "classes/payments.php";

class paymentsDAO
{
    public function remover($payment){
        global $pdo;
        try {
            $statement = $pdo->prepare("DELETE FROM tb_payments WHERE id_payment = :id");
            $statement->bindValue(":id", $payment->getIdPayment());
            if ($statement->execute()) {
                return "<script> alert('Registo foi excluído com êxito !'); </script>";
            } else {
                throw new PDOException("<script> alert('Não foi possível executar a declaração SQL !'); </script>");
            }
        } catch (PDOException $erro) {
            return "Erro: ".$erro->getMessage();
        }
    }

    public function salvar($payment){
        global $pdo;
        try {
            if ($payment->getIdPayment() != "") {
                $statement = $pdo->prepare("UPDATE tb_payments SET tb_city_id_city=:tb_city_id_city, tb_functions_id_function=:tb_functions_id_function, tb_subfunctions_id_subfunction=:tb_subfunctions_id_subfunction, 
                                                      tb_program_id_program=:tb_program_id_program, tb_action_id_action=:tb_action_id_action, tb_beneficiaries_id_beneficiaries=:tb_beneficiaries_id_beneficiaries, 
                                                      tb_source_id_source=:tb_source_id_source, tb_files_id_file=:tb_files_id_file, db_value=:db_value WHERE id_payment = :id;");
                $statement->bindValue(":id", $payment->getIdPayment());
            } else {
                $statement = $pdo->prepare("INSERT INTO tb_city (tb_city_id_city, tb_functions_id_function, tb_subfunctions_id_subfunction, tb_program_id_program, tb_action_id_action, tb_beneficiaries_id_beneficiaries, tb_source_id_source, tb_files_id_file, db_value) 
                                                      VALUES (:tb_city_id_city, :tb_functions_id_function, :tb_subfunctions_id_subfunction, :tb_program_id_program, :tb_action_id_action, :tb_beneficiaries_id_beneficiaries, :tb_source_id_source, :tb_files_id_file, :db_value)");
            }
            $statement->bindValue(":tb_city_id_city",$payment->getTbCityIdCity());
            $statement->bindValue(":tb_functions_id_function",$payment->getTbFunctionsIdFunction());
            $statement->bindValue(":tb_subfunctions_id_subfunction",$payment->getTbSubfunctionsIdSubfunction());
            $statement->bindValue(":tb_program_id_program",$payment->getTbProgramIdProgram());
            $statement->bindValue(":tb_action_id_action",$payment->getTbActionIdAction());
            $statement->bindValue(":tb_beneficiaries_id_beneficiaries",$payment->getTbBeneficiariesIdBeneficiaries());
            $statement->bindValue(":tb_source_id_source",$payment->getTbSourceIdSource());
            $statement->bindValue(":tb_files_id_file",$payment->getTbFilesIdFile());
            $statement->bindValue(":db_value",$payment->getDbValue());

            if ($statement->execute()) {
                if ($statement->rowCount() > 0) {
                    return "<script> alert('Dados cadastrados com sucesso !'); </script>";
                } else {
                    return "<script> alert('Erro ao tentar efetivar cadastro !'); </script>";
                }
            } else {
                throw new PDOException("<script> alert('Não foi possível executar a declaração SQL !'); </script>");
            }
        } catch (PDOException $erro) {
            return "Erro: " . $erro->getMessage();
        }
    }

    public function atualizar($payment){
        global $pdo;
        try {
            $statement = $pdo->prepare("SELECT id_payment, tb_city_id_city, tb_functions_id_function, tb_subfunctions_id_subfunction, tb_program_id_program, 
                                                  tb_action_id_action, tb_beneficiaries_id_beneficiaries, tb_source_id_source, tb_files_id_file, db_value 
                                                  FROM tb_payments WHERE id_payment = :id");
            $statement->bindValue(":id", $payment->getIdPayment());
            if ($statement->execute()) {
                $rs = $statement->fetch(PDO::FETCH_OBJ);
                $payment->setIdPayment($rs->id_payment);
                $payment->setTbCityIdCity($rs->tb_city_id_city);
                $payment->setTbFunctionsIdFunction($rs->tb_functions_id_function);
                $payment->setTbSubfunctionsIdSubfunction($rs->tb_subfunctions_id_subfunction);
                $payment->setTbProgramIdProgram($rs->tb_program_id_program);
                $payment->setTbActionIdAction($rs->tb_action_id_action);
                $payment->setTbBeneficiariesIdBeneficiaries($rs->tb_beneficiaries_id_beneficiaries);
                $payment->setTbSourceIdSource($rs->tb_source_id_source);
                $payment->setTbFilesIdFile($rs->tb_files_id_file);
                $payment->setDbValue($rs->db_value);

                return $payment;
            } else {
                throw new PDOException("<script> alert('Não foi possível executar a declaração SQL !'); </script>");
            }
        } catch (PDOException $erro) {
            return "Erro: ".$erro->getMessage();
        }
    }

    public function tabelapaginada() {

        //carrega o banco
        global $pdo;

        //endereço atual da página
        $endereco = $_SERVER ['PHP_SELF'];

        /* Constantes de configuração */
        define('QTDE_REGISTROS', 10);
        define('RANGE_PAGINAS', 1);

        /* Recebe o número da página via parâmetro na URL */
        $pagina_atual = (isset($_GET['page']) && is_numeric($_GET['page'])) ? $_GET['page'] : 1;

        /* Calcula a linha inicial da consulta */
        $linha_inicial = ($pagina_atual -1) * QTDE_REGISTROS;

        /* Instrução de consulta para paginação com MySQL */
        $sql = "SELECT P.id_payment, C.str_name_city as tb_city, P.tb_city_id_city, P.tb_functions_id_function, P.tb_subfunctions_id_subfunction, P.tb_program_id_program, 
                                                  P.tb_action_id_action, P.tb_beneficiaries_id_beneficiaries, P.tb_source_id_source, P.tb_files_id_file, P.db_value 
                                                  FROM tb_payments P INNER JOIN tb_city C ON C.id_city = P.tb_city_id_city LIMIT {$linha_inicial}, " . QTDE_REGISTROS;
        $statement = $pdo->prepare($sql);
        $statement->execute();
        $dados = $statement->fetchAll(PDO::FETCH_OBJ);

        /* Conta quantos registos existem na tabela */
        $sqlContador = "SELECT COUNT(*) AS total_registros FROM tb_payments";
        $statement = $pdo->prepare($sqlContador);
        $statement->execute();
        $valor = $statement->fetch(PDO::FETCH_OBJ);

        /* Idêntifica a primeira página */
        $primeira_pagina = 1;

        /* Cálcula qual será a última página */
        $ultima_pagina  = ceil($valor->total_registros / QTDE_REGISTROS);

        /* Cálcula qual será a página anterior em relação a página atual em exibição */
        $pagina_anterior = ($pagina_atual > 1) ? $pagina_atual -1 : 0 ;

        /* Cálcula qual será a pŕoxima página em relação a página atual em exibição */
        $proxima_pagina = ($pagina_atual < $ultima_pagina) ? $pagina_atual +1 : 0 ;

        /* Cálcula qual será a página inicial do nosso range */
        $range_inicial  = (($pagina_atual - RANGE_PAGINAS) >= 1) ? $pagina_atual - RANGE_PAGINAS : 1 ;

        /* Cálcula qual será a página final do nosso range */
        $range_final   = (($pagina_atual + RANGE_PAGINAS) <= $ultima_pagina ) ? $pagina_atual + RANGE_PAGINAS : $ultima_pagina ;

        /* Verifica se vai exibir o botão "Primeiro" e "Pŕoximo" */
        $exibir_botao_inicio = ($range_inicial < $pagina_atual) ? 'mostrar' : 'esconder';

        /* Verifica se vai exibir o botão "Anterior" e "Último" */
        $exibir_botao_final = ($range_final > $pagina_atual) ? 'mostrar' : 'esconder';

        if (!empty($dados)):
            echo "
     <table class='table table-striped table-bordered'>
     <thead>
       <tr style='text-transform: uppercase;' class='active'>
        <th style='text-align: center; font-weight: bolder;'>Code</th>
        <th style='text-align: center; font-weight: bolder;'>City</th>
        <th style='text-align: center; font-weight: bolder;'>Function</th>
        <th style='text-align: center; font-weight: bolder;'>Sub-Function</th>
        <th style='text-align: center; font-weight: bolder;'>Program</th>
        <th style='text-align: center; font-weight: bolder;'>Action</th>
        <th style='text-align: center; font-weight: bolder;'>Beneficiaries</th>
        <th style='text-align: center; font-weight: bolder;'>Font</th>
        <th style='text-align: center; font-weight: bolder;'>Files</th>
        <th style='text-align: center; font-weight: bolder;'>Value</th>
        <th style='text-align: center; font-weight: bolder;' colspan='2'>Actions</th>
       </tr>
     </thead>
     <tbody>";
            foreach ($dados as $paym):
                echo "<tr>
        <td style='text-align: center'>$paym->id_payment</td>
        <td style='text-align: center'>$paym->tb_city_id_city</td>
        <td style='text-align: center'>$paym->tb_functions_id_function</td>
        <td style='text-align: center'>$paym->tb_subfunctions_id_subfunction</td>
        <td style='text-align: center'>$paym->tb_program_id_program</td>
        <td style='text-align: center'>$paym->tb_action_id_action</td>
        <td style='text-align: center'>$paym->tb_beneficiaries_id_beneficiaries</td>
        <td style='text-align: center'>$paym->tb_source_id_source</td>
        <td style='text-align: center'>$paym->tb_files_id_file</td>
        <td style='text-align: center'>$paym->db_value</td>
        <td style='text-align: center'><a href='?act=upd&id=$paym->id_payment' title='Alterar'><i class='ti-reload'></i></a></td>
        <td style='text-align: center'><a href='?act=del&id=$paym->id_payment' title='Remover'><i class='ti-close'></i></a></td>
       </tr>";
            endforeach;
            echo "
</tbody>
     </table>

    <div class='box-paginacao' style='text-align: center'>
       <a class='box-navegacao  $exibir_botao_inicio' href='$endereco?page=$primeira_pagina' title='Primeira Página'> FIRST  |</a>
       <a class='box-navegacao  $exibir_botao_inicio' href='$endereco?page=$pagina_anterior' title='Página Anterior'> PREVIOUS  |</a>
";

            /* Loop para montar a páginação central com os números */
            for ($i = $range_inicial; $i <= $range_final; $i++):
                $destaque = ($i == $pagina_atual) ? 'destaque' : '';
                echo "<a class='box-numero $destaque' href='$endereco?page=$i'> ( $i ) </a>";
            endfor;

            echo "<a class='box-navegacao $exibir_botao_final' href='$endereco?page=$proxima_pagina' title='Próxima Página'>| NEXT  </a>
                  <a class='box-navegacao $exibir_botao_final' href='$endereco?page=$ultima_pagina'  title='Última Página'>| LAST  </a>
     </div>";
        else:
            echo "<p class='bg-danger'>Nenhum registro foi encontrado!</p>
     ";
        endif;

    }


}