<?php

require_once "db/connection.php";
require_once "classes/user.php";

class userDAO
{

    public function remover($user)
    {
        global $pdo;
        try {
            $statement = $pdo->prepare("DELETE FROM tb_user WHERE iduser = :id");
            $statement->bindValue(":id", $user->getIdUsuario());
            if ($statement->execute()) {
                return "<script> alert('Registo foi excluído com êxito !'); </script>";
            } else {
                throw new PDOException("<script> alert('Não foi possível executar a declaração SQL !'); </script>");
            }
        } catch (PDOException $erro) {
            return "Erro: " . $erro->getMessage();
        }
    }

    public function salvar($user)
    {
        global $pdo;
        try {
            if ($user->getIdUsuario() != "") {
                $statement = $pdo->prepare("UPDATE tb_user SET login=:login, senha=:senha, nome=:nome, email=:email, resetar=:resetar, perfil=:perfil WHERE iduser = :id;");
                $statement->bindValue(":id", $user->getIdUsuario());
            } else {
                $statement = $pdo->prepare("INSERT INTO tb_user (login, senha, nome, email, resetar, perfil) VALUES (:login, :senha, :nome, :email, :resetar, :perfil)");
            }
            $statement->bindValue(":login", $user->getLogin());
            $statement->bindValue(":senha", sha1($user->getSenha()));
            $statement->bindValue(":nome", $user->getNome());
            $statement->bindValue(":email", $user->getEmail());
            $statement->bindValue(":resetar", $user->getResetar());
            $statement->bindValue(":perfil", $user->getPerfil());
            
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

    public function atualizar($user)
    {
        global $pdo;
        try {
            $statement = $pdo->prepare("SELECT iduser, login, senha, nome, email, resetar, perfil FROM tb_user WHERE iduser = :id");
            $statement->bindValue(":id", $user->getIdUsuario());
            if ($statement->execute()) {
                $rs = $statement->fetch(PDO::FETCH_OBJ);
                $user->setIdUsuario($rs->iduser);
                $user->setLogin($rs->login);
                $user->setSenha($rs->senha);
                $user->setNome($rs->nome);
                $user->setEmail($rs->email);
                $user->setResetar($rs->resetar);
                $user->setPerfil($rs->perfil);
                return $user;
            } else {
                throw new PDOException("<script> alert('Não foi possível executar a declaração SQL !'); </script>");
            }
        } catch (PDOException $erro) {
            return "Erro: " . $erro->getMessage();
        }
    }

    public function recuperaSenha($login, $email){
        //carrega o banco
        global $pdo;

        $sql = "SELECT count(*) as qtd, iduser FROM tb_user WHERE login=:login AND email =:email ";
        $statement = $pdo->prepare($sql);
        $statement->bindValue(":login", $login);
        $statement->bindValue(":email", $email);

        $statement->execute();

        $encontrou = $statement->fetch(PDO::FETCH_OBJ);


        if($encontrou->qtd > 0){
            $statement = $pdo->prepare("UPDATE tb_user SET resetar=:resetar, senha=:senha WHERE iduser =:iduser;");

            $statement->bindValue(":resetar",  true);
            $statement->bindValue(":senha",  sha1(123456));
            $statement->bindValue(":iduser",  $encontrou->iduser);
            $statement->execute();
            return true;
        }else{
            return false;
        }
    }


    public function tabelapaginada()
    {

        //carrega o banco
        global $pdo;

        //endereço atual da página
        $endereco = $_SERVER ['PHP_SELF'];

        /* Constantes de configuração */
        define('QTDE_REGISTROS', 10);
        define('RANGE_PAGINAS', 2);

        /* Recebe o número da página via parâmetro na URL */
        $pagina_atual = (isset($_GET['page']) && is_numeric($_GET['page'])) ? $_GET['page'] : 1;

        /* Calcula a linha inicial da consulta */
        $linha_inicial = ($pagina_atual - 1) * QTDE_REGISTROS;

        /* Instrução de consulta para paginação com MySQL */
        $sql = "SELECT iduser, login, senha, nome, email, resetar, perfil FROM tb_user LIMIT {$linha_inicial}, " . QTDE_REGISTROS;
        $statement = $pdo->prepare($sql);
        $statement->execute();
        $dados = $statement->fetchAll(PDO::FETCH_OBJ);

        /* Conta quantos registos existem na tabela */
        $sqlContador = "SELECT COUNT(*) AS total_registros FROM tb_user";
        $statement = $pdo->prepare($sqlContador);
        $statement->execute();
        $valor = $statement->fetch(PDO::FETCH_OBJ);

        /* Idêntifica a primeira página */
        $primeira_pagina = 1;

        /* Cálcula qual será a última página */
        $ultima_pagina = ceil($valor->total_registros / QTDE_REGISTROS);

        /* Cálcula qual será a página anterior em relação a página atual em exibição */
        $pagina_anterior = ($pagina_atual > 1) ? $pagina_atual - 1 : 0;

        /* Cálcula qual será a pŕoxima página em relação a página atual em exibição */
        $proxima_pagina = ($pagina_atual < $ultima_pagina) ? $pagina_atual + 1 : 0;

        /* Cálcula qual será a página inicial do nosso range */
        $range_inicial = (($pagina_atual - RANGE_PAGINAS) >= 1) ? $pagina_atual - RANGE_PAGINAS : 1;

        /* Cálcula qual será a página final do nosso range */
        $range_final = (($pagina_atual + RANGE_PAGINAS) <= $ultima_pagina) ? $pagina_atual + RANGE_PAGINAS : $ultima_pagina;

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
        <th style='text-align: center; font-weight: bolder;'>Login</th>
        <!--<th style='text-align: center; font-weight: bolder;'>Password</th> -->
        <th style='text-align: center; font-weight: bolder;'>Name</th>
        <th style='text-align: center; font-weight: bolder;'>Email</th>
        <th style='text-align: center; font-weight: bolder;'>Reset</th>
        <th style='text-align: center; font-weight: bolder;'>Profile</th>";
        if($_SESSION['perfil'] == 0){
            echo "<th style='text-align: center; font-weight: bolder;' colspan='2'>Actions</th>";
        }
       echo "</tr>
     </thead>
     <tbody>";
            foreach ($dados as $var):
                echo "<tr>
        <td style='text-align: center'>$var->iduser</td>
        <td style='text-align: center'>$var->login</td>
        <!--<td style='text-align: center'>$var->senha</td>-->
        <td style='text-align: center'>$var->nome</td>
        <td style='text-align: center'>$var->email</td>
        <td style='text-align: center'>";
            echo ($var->resetar == 0)? "No" : "Yes"; 
        echo "</td>
        <td style='text-align: center'>";
           echo ($var->perfil == 0)? "Adm" : "User";
        if($_SESSION['perfil'] == 0){
            echo "</td>
            <td style='text-align: center'><a href='?act=upd&id=$var->iduser' title='Alterar'><i class='ti-reload'></i></a></td>
            <td style='text-align: center'><a href='?act=del&id=$var->iduser' title='Remover'><i class='ti-close'></i></a></td>
            </tr>";

        }else{
            echo "</tr>";
        }
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