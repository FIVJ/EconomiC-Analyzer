<?php

ini_set('display_errors', 0);

require_once  "../vendor/autoload.php";
require_once "../dao/reportDAO.php";

session_start();
        if((!isset ($_SESSION['login']) == true) and (!isset ($_SESSION['password']) == true))
        {
            unset($_SESSION['login']);
            unset($_SESSION['password']);
      header('location: ../login.php');
        }else{


$dao = new reportDAO();

$listObjs = $dao->report02();
$dia = $dao ->dateNow();
$hr = $dao ->hourNow();

$html = "<table border='2' cellspacing='3' cellpadding='5' >";
$html .= "<tr>
            <th>ID BENEFICIARIES</th>
            <th>NOME DO BENEFICIARIES</th>
            <th>CODIGO NIS</th>
            <th>ID CIDADE</th>
            <th>NOME CIDADE</th>
            <th>CODIGO SIAFI</th>
            <th>ID ESTADO</th>
        </tr>";
foreach ($listObjs as $var):
    $html.= "<tr>
                <td>$var->id_beneficiaries</td>
                <td>$var->str_name_person</td>
                <td>$var->str_nis</td>
                <td>$var->id_city</td>
                <td>$var->str_name_city</td>
                <td>$var->str_cod_siafi_city</td>
                <td>$var->tb_state_id_state</td>
          </tr>";
endforeach;
$html .= "</table>";

$mpdf=new \Mpdf\Mpdf();
$mpdf->SetCreator('PDF_CREATOR');
$mpdf->SetAuthor('Tassio Sirqueira');
$mpdf->SetTitle('Relatório PDF com a lista de todos os beneficiários e a cidade a qual pertencem, com todos os dados do benefiário e da cidada, ordenados por cidade e posteriormente por nome do beneficário');
$mpdf->SetSubject('EconomiC Analyzer');
$mpdf->SetKeywords('TCPDF, ECA');
$mpdf->SetDisplayMode('fullpage');
$mpdf->nbpgPrefix = ' de ';
$mpdf->setFooter("Relatório gerado no dia {$dia} às {$hr} - Página {PAGENO}{nbpg}");
$mpdf->WriteHTML($html);
$mpdf->Output('economicAnalyzer.pdf','I');

exit;
        }